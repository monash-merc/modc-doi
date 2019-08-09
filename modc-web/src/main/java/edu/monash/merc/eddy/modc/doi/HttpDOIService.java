/*
 * Copyright (c) 2014, Monash e-Research Centre
 *  (Monash University, Australia)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  	* Neither the name of the Monash University nor the names of its
 *  	  contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.eddy.modc.doi;

import edu.monash.merc.eddy.modc.common.util.MDUtils;
import edu.monash.merc.eddy.modc.domain.doi.*;
import edu.monash.merc.eddy.modc.ws.exception.DoiServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 11/09/2014
 */
@Component
public class HttpDOIService {

    private static Logger logger = Logger.getLogger(HttpDOIService.class.getName());

    @Autowired
    private FreeMarkerDoiTempLoader freeMarkerDoiTempLoader;

    @Autowired
    private DOIServiceHelper doiServiceHelper;

    public void setFreeMarkerDoiTempLoader(FreeMarkerDoiTempLoader freeMarkerDoiTempLoader) {
        this.freeMarkerDoiTempLoader = freeMarkerDoiTempLoader;
    }

    /**
     * Mint DOI service
     * @param appId an authorized app id
     * @param doiResource A DoiResource object
     * @return a DoiResponse object
     */
    public DoiResponse mintDoi(String appId, DoiResource doiResource) {
        CloseableHttpClient client = null;
        try {
            String doiServicePoint = this.doiServiceHelper.getDoiServicePoint();
            String doiVersion = this.doiServiceHelper.getDoiVersion();
            String doiMintSuffix = this.doiServiceHelper.getDoiMintSuffix();
            //String appId = this.doiServiceHelper.getDefaultAuthorizedAppId();

            String url = doiResource.getUrl();

            if (StringUtils.isBlank(url)) {
                throw new DoiServiceException("The url must be provided");
            }

            String mint_service_url = doiServicePoint + "/" + doiVersion + "/" + doiMintSuffix + "/?app_id=" + appId + "&url=" + MDUtils.pathEncode(url);

            if (logger.isDebugEnabled()) {
                logger.debug("The url of minting doi is: " + mint_service_url);
            }
            //create a client
            client = this.doiServiceHelper.createClient();
            HttpPost post = new HttpPost(mint_service_url);
            byte[] doiXmls = loadDoiResource(doiResource, this.doiServiceHelper.getDataCiteSchemaVersion());

            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(doiXmls);
            if (logger.isDebugEnabled()) {
                logger.debug("The Doi XML file: " + new String(doiXmls));
            }
//            System.out.println("The Doi XML file: " + new String(doiXmls));
            post.setEntity(byteArrayEntity);

            HttpResponse httpResponse = client.execute(post);

            if (logger.isDebugEnabled()) {
                logger.debug("The minting Doi response status code: " + httpResponse.getStatusLine().getStatusCode());
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("The minting Doi response : " + result.toString());
            }
            DoiResponse doiResponse = DoiResponseParser.parseDOIXML(result.toString());

            String respType = doiResponse.getType();

            if (StringUtils.equalsIgnoreCase(respType, "success")) {
                logger.info("The mint doi : " + doiResponse.getDoi());
            }
            return doiResponse;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new DoiServiceException(ex);
        } finally {
            //close the client if client not null;
            if (client != null) {
                this.doiServiceHelper.close(client);
            }
        }
    }

    /**
     * Update DOI Service
     *
     * @param appId an authorized app id
     * @param doiResource A DoiResource object
     * @return a DoiResponse object
     */
    public DoiResponse updateDoi(String appId, DoiResource doiResource) {
        CloseableHttpClient client = null;
        try {
            String doiServicePoint = this.doiServiceHelper.getDoiServicePoint();
            String doiVersion = this.doiServiceHelper.getDoiVersion();
            String doiUpdateSuffix = this.doiServiceHelper.getDoiUpdateSuffix();
            //String appId = this.doiServiceHelper.getDefaultAuthorizedAppId();
            String url = doiResource.getUrl();
            String doi = doiResource.getDoi();
            String update_service_url = doiServicePoint + "/" + doiVersion + "/" + doiUpdateSuffix + "/?app_id=" + appId;

            if (StringUtils.isNotBlank(url)) {
                update_service_url += "&url=" + MDUtils.pathEncode(url) + "&doi=" + doi;
            } else {
                update_service_url += "&doi=" + doi;
            }

            if (logger.isDebugEnabled()) {
                logger.debug("The url of updating doi is: " + update_service_url);
            }
            //create a client
            client = this.doiServiceHelper.createClient();
            HttpPost post = new HttpPost(update_service_url);
            byte[] doiXmls = loadDoiResource(doiResource, this.doiServiceHelper.getDataCiteSchemaVersion());
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(doiXmls);

            if (logger.isDebugEnabled()) {
                logger.debug("The Doi XML file: " + new String(doiXmls));
            }
            post.setEntity(byteArrayEntity);

            HttpResponse httpResponse = client.execute(post);

            if (logger.isDebugEnabled()) {
                logger.debug("The updating Doi response status code: " + httpResponse.getStatusLine().getStatusCode());
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("The updating Doi response : " + result.toString());
            }
            return DoiResponseParser.parseDOIXML(result.toString());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new DoiServiceException(ex);
        } finally {
            //close the client if client not null;
            if (client != null) {
                this.doiServiceHelper.close(client);
            }
        }
    }

    /**
     * Deactivate DOI Service
     *
     * @param appId an authorized app id
     * @param doi a DOI identifier
     * @return a DoiResponse object
     */
    public DoiResponse deactivateDoi(String appId, String doi) {
        CloseableHttpClient client = null;
        try {
            String doiServicePoint = this.doiServiceHelper.getDoiServicePoint();
            String doiVersion = this.doiServiceHelper.getDoiVersion();
            String doiDeactivateSuffix = this.doiServiceHelper.getDoiDeactivateSuffix();
            //String appId = this.doiServiceHelper.getDefaultAuthorizedAppId();

            String deactivateDoiUrl = doiServicePoint + "/" + doiVersion + "/" + doiDeactivateSuffix + "/?app_id=" + appId + "&doi=" + doi;

            //create a client
            client = this.doiServiceHelper.createClient();

            HttpGet get = new HttpGet(deactivateDoiUrl);

            HttpResponse httpResponse = client.execute(get);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("The deactivate doi response : " + result.toString());
            }
            return DoiResponseParser.parseDOIXML(result.toString());

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new DoiServiceException(ex);
        } finally {
            //close the client if client not null;
            if (client != null) {
                this.doiServiceHelper.close(client);
            }
        }
    }

    /**
     * Activate a  deactivated DOI
     * @param appId an authorized app id
     * @param doi a deactivated doi identifier
     * @return a DoiResponse object
     */
    public DoiResponse activateDoi(String appId, String doi) {
        CloseableHttpClient client = null;
        try {
            String doiServicePoint = this.doiServiceHelper.getDoiServicePoint();
            String doiVersion = this.doiServiceHelper.getDoiVersion();
            String doiActivateSuffix = this.doiServiceHelper.getDoiActivateSuffix();
            //String appId = this.doiServiceHelper.getDefaultAuthorizedAppId();

            String activateDoiUrl = doiServicePoint + "/" + doiVersion + "/" + doiActivateSuffix + "/?app_id=" + appId + "&doi=" + doi;

            //create a client
            client = this.doiServiceHelper.createClient();

            HttpGet get = new HttpGet(activateDoiUrl);

            HttpResponse httpResponse = client.execute(get);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("The activate doi response : " + result.toString());
            }
            return DoiResponseParser.parseDOIXML(result.toString());

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new DoiServiceException(ex);
        } finally {
            //close the client if client not null;
            if (client != null) {
                this.doiServiceHelper.close(client);
            }
        }
    }

    /**
     * Load resource xml from the freemarker template
     *
     * @param doiResource a DoiResource object
     * @return a byte array for doi resource xml file
     */
    private byte[] loadDoiResource(DoiResource doiResource, String schemaVersion) {
        Map<String, Object> doiTemplateValues = new HashMap<>();

        String doi = doiResource.getDoi();
        List<DoiCreator> doiCreators = doiResource.getDoiCreators();
        List<DoiTitle> doiTitles = doiResource.getTitles();
        DoiPublisher doiPublisher = doiResource.getPublisher();
        Date doiPublicationYear = doiResource.getPublicationYear();
        List<DoiSubject> doiSubjects = doiResource.getSubjects();
        List<DoiContributor> doiContributors = doiResource.getContributors();
        List<DoiRelevantDate> doiRelevantDates = doiResource.getRelevantDates();
        String doiLanguage = doiResource.getLanguage();
        DoiResourceType doiResourceType = doiResource.getResourceType();
        List<DoiAlternateIdentifier> doiAlternateIdentifiers = doiResource.getAlternateIdentifiers();
        List<DoiRelatedIdentifier> doiRelatedIdentifiers = doiResource.getRelatedIdentifiers();
        List<DoiResourceSize> doiResourceSizes = doiResource.getSizes();
        List<DoiResourceFormat> doiResourceFormats = doiResource.getFormats();
        String resourceVersion = doiResource.getResourceVersion();
        String resourceRights = doiResource.getRights();
        List<DoiDescription> doiDescriptions = doiResource.getDescriptions();

        //set the resource template values
        if (StringUtils.isNotBlank(schemaVersion)){
            doiTemplateValues.put("schemaVersion", schemaVersion);
        }
        //set doi if any
        if (StringUtils.isNotBlank(doi)) {
            doiTemplateValues.put("doi", doi);
        }
        //set the creators
        if (doiCreators != null && doiCreators.size() > 0) {
            doiTemplateValues.put("doiCreators", doiCreators);
        }
        //set the publisher
        doiTemplateValues.put("doiPublisher", doiPublisher);
        //set the publication year
        doiTemplateValues.put("doiPublicationYear", doiPublicationYear);

        //set the titles if any
        if (doiTitles != null && doiTitles.size() > 0) {
            doiTemplateValues.put("doiTitles", doiTitles);
        }

        //set the subjects if any
        if (doiSubjects != null && doiSubjects.size() > 0) {
            doiTemplateValues.put("doiSubjects", doiSubjects);
        }
        //set the contributors if any
        if (doiContributors != null && doiContributors.size() > 0) {
            doiTemplateValues.put("doiContributors", doiContributors);
        }
        //set the relevant dates if any
        if (doiRelevantDates != null && doiRelevantDates.size() > 0) {
            doiTemplateValues.put("doiRelevantDates", doiRelevantDates);
        }
        //set the language if any
        if (StringUtils.isNotBlank(doiLanguage)) {
            doiTemplateValues.put("doiLanguage", doiLanguage);
        }
        //set the resource type if any
        if (doiResourceType != null) {
            doiTemplateValues.put("doiResourceType", doiResourceType);
        }
        //set alternateIdentifiers
        if (doiAlternateIdentifiers != null && doiAlternateIdentifiers.size() > 0) {
            doiTemplateValues.put("doiAlternateIdentifiers", doiAlternateIdentifiers);
        }
        //set relatedIdentifiers
        if (doiRelatedIdentifiers != null && doiRelatedIdentifiers.size() > 0) {
            for (DoiRelatedIdentifier doiRelatedIdentifier : doiRelatedIdentifiers) {
                String relatedIdentifierType = doiRelatedIdentifier.getRelatedIdentifierType();
                String relatedId = doiRelatedIdentifier.getRelatedIdentifier();
                if (StringUtils.equalsIgnoreCase(relatedIdentifierType, DoiRelatedIdentifierType.PURL.value())
                        || StringUtils.equalsIgnoreCase(relatedIdentifierType, DoiRelatedIdentifierType.URL.value())
                        || StringUtils.equalsIgnoreCase(relatedIdentifierType, DoiRelatedIdentifierType.URN.value())) {
                    doiRelatedIdentifier.setRelatedIdentifier(MDUtils.pathEncode(relatedId));
                }
            }
            doiTemplateValues.put("doiRelatedIdentifiers", doiRelatedIdentifiers);
        }
        //set resource sizes
        if (doiResourceSizes != null && doiResourceSizes.size() > 0) {
            doiTemplateValues.put("doiResourceSizes", doiResourceSizes);
        }
        //set resource formats
        if (doiResourceFormats != null && doiResourceFormats.size() > 0) {
            doiTemplateValues.put("doiResourceFormats", doiResourceFormats);
        }
        //set resource version
        if (StringUtils.isNotBlank(resourceVersion)) {
            doiTemplateValues.put("resourceVersion", resourceVersion);
        }
        //set resource rights
        if (StringUtils.isNotBlank(resourceRights)) {
            doiTemplateValues.put("resourceRights", resourceRights);
        }
        //set resource descriptions
        if (doiDescriptions != null && doiDescriptions.size() > 0) {
            doiTemplateValues.put("doiDescriptions", doiDescriptions);
        }
        return this.freeMarkerDoiTempLoader.loadDoiXML(doiTemplateValues, this.doiServiceHelper.getDoiTemplate());
    }
}
