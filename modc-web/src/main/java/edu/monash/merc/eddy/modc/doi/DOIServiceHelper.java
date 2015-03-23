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

import edu.monash.merc.eddy.modc.http.HttpConnectionAliveStrategy;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 12/09/2014
 */
public class DOIServiceHelper {

    private String doiServicePoint;

    private String authorizedAppId;

    private String doiVersion;

    private String doiMintSuffix;

    private String doiUpdateSuffix;

    private String doiActivateSuffix;

    private String doiDeactivateSuffix;

    private String doiTemplate;

    private int maxTotalConnections;

    private int maxPerRouteConnections;

    private int keepAliveInSeconds;

    private static final String DOI_SERVICE_POINT = "https://services.ands.org.au/doi";

    private static final String DEFAULT_APP_ID = "4fb08353943adf1f733c528c14293db711a5b03d";

    private static final String DOI_VERSION = "1.1";

    private static final String MINT_SUFFIX = "mint.xml";

    private static final String UPDATE_SUFFIX = "update.xml";

    private static final String ACTIVATE_SUFFIX = "activate.xml";

    private static final String DEACTIVATE_SUFFIX = "deactivate.xml";

    private static final String DOI_METADATA_TEMPLATE = "doi.ftl";

    private static int DEFAULT_MAX_TOTAL_CONNECTIONS = 10;

    private static int DEFAULT_MAX_PER_ROUTE = 5;

    private static int DEFAULT_KEEP_ALIVE = 10;

    private static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

    private static Logger logger = Logger.getLogger(DOIServiceHelper.class.getName());

    public void init() {
        logger.info("===== start to init HttpDOIClient");

        if (maxTotalConnections <= 0) {
            maxTotalConnections = DEFAULT_MAX_TOTAL_CONNECTIONS;
        }
        httpClientBuilder.setMaxConnTotal(maxTotalConnections);

        if (maxPerRouteConnections <= 0) {
            maxPerRouteConnections = DEFAULT_MAX_PER_ROUTE;
        }
        httpClientBuilder.setMaxConnPerRoute(maxPerRouteConnections);

        logger.info("===== maxTotalConnections : " + maxTotalConnections);
        logger.info("===== maxPerRouteConnections : " + maxPerRouteConnections);

        HttpConnectionAliveStrategy connectionAliveStrategy = new HttpConnectionAliveStrategy();
        //set keep alive in seconds
        if (keepAliveInSeconds <= 0) {
            keepAliveInSeconds = DEFAULT_KEEP_ALIVE;
        }

        logger.info("===== keepAliveInSeconds : " + keepAliveInSeconds);
        connectionAliveStrategy.setKeepAliveInSeconds(keepAliveInSeconds);

        httpClientBuilder.setKeepAliveStrategy(connectionAliveStrategy);

        logger.info("===== Finished to init HttpDOIClient");

        if (StringUtils.isBlank(doiServicePoint)) {
            doiServicePoint = DOI_SERVICE_POINT;
        }

        if (StringUtils.isBlank(doiVersion)) {
            doiVersion = DOI_VERSION;
        }

        if (StringUtils.isBlank(doiMintSuffix)) {
            doiMintSuffix = MINT_SUFFIX;
        }

        if (StringUtils.isBlank(doiUpdateSuffix)) {
            doiUpdateSuffix = UPDATE_SUFFIX;
        }

        if (StringUtils.isBlank(doiActivateSuffix)) {
            doiActivateSuffix = ACTIVATE_SUFFIX;
        }

        if (StringUtils.isBlank(doiDeactivateSuffix)) {
            doiDeactivateSuffix = DEACTIVATE_SUFFIX;
        }

        if (StringUtils.isBlank(authorizedAppId)) {
            authorizedAppId = DEFAULT_APP_ID;
        }

        if (StringUtils.isBlank(doiTemplate)) {
            doiTemplate = DOI_METADATA_TEMPLATE;
        }
    }

    public CloseableHttpClient createClient() {
        return httpClientBuilder.build();
    }

    public void close(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (Exception ex) {
                logger.warn("Failed to close the http client.");
            }
        }
    }

    public String getDoiServicePoint() {
        return doiServicePoint;
    }

    public void setDoiServicePoint(String doiServicePoint) {
        this.doiServicePoint = doiServicePoint;
    }

    public String getAuthorizedAppId() {
        return authorizedAppId;
    }

    public void setAuthorizedAppId(String authorizedAppId) {
        this.authorizedAppId = authorizedAppId;
    }

    public String getDoiVersion() {
        return doiVersion;
    }

    public void setDoiVersion(String doiVersion) {
        this.doiVersion = doiVersion;
    }

    public String getDoiMintSuffix() {
        return doiMintSuffix;
    }

    public void setDoiMintSuffix(String doiMintSuffix) {
        this.doiMintSuffix = doiMintSuffix;
    }

    public String getDoiUpdateSuffix() {
        return doiUpdateSuffix;
    }

    public void setDoiUpdateSuffix(String doiUpdateSuffix) {
        this.doiUpdateSuffix = doiUpdateSuffix;
    }

    public String getDoiDeactivateSuffix() {
        return doiDeactivateSuffix;
    }

    public void setDoiDeactivateSuffix(String doiDeactivateSuffix) {
        this.doiDeactivateSuffix = doiDeactivateSuffix;
    }

    public String getDoiActivateSuffix() {
        return doiActivateSuffix;
    }

    public void setDoiActivateSuffix(String doiActivateSuffix) {
        this.doiActivateSuffix = doiActivateSuffix;
    }

    public String getDoiTemplate() {
        return doiTemplate;
    }

    public void setDoiTemplate(String doiTemplate) {
        this.doiTemplate = doiTemplate;
    }

    public int getMaxTotalConnections() {
        return maxTotalConnections;
    }

    public void setMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    public int getMaxPerRouteConnections() {
        return maxPerRouteConnections;
    }

    public void setMaxPerRouteConnections(int maxPerRouteConnections) {
        this.maxPerRouteConnections = maxPerRouteConnections;
    }

    public int getKeepAliveInSeconds() {
        return keepAliveInSeconds;
    }

    public void setKeepAliveInSeconds(int keepAliveInSeconds) {
        this.keepAliveInSeconds = keepAliveInSeconds;
    }
}
