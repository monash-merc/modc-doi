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

package edu.monash.merc.eddy.modc.ws.endpoint;

import edu.monash.merc.eddy.modc.common.util.MDUtils;
import edu.monash.merc.eddy.modc.doi.DoiResponse;
import edu.monash.merc.eddy.modc.doi.HttpDOIService;
import edu.monash.merc.eddy.modc.domain.AuthorizedApp;
import edu.monash.merc.eddy.modc.domain.ServiceApp;
import edu.monash.merc.eddy.modc.domain.doi.*;
import edu.monash.merc.eddy.modc.service.ServiceAppService;
import edu.monash.merc.eddy.modc.ws.exception.DoiValidateException;
import edu.monash.merc.eddy.modc.ws.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by simonyu on 1/08/2014.
 */
@Endpoint
public class DOIServiceEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private final String SERVICE_NS = "http://merc.monash.edu/ws/schema/doi";

    private final ObjectFactory JAXB_OBJECT_FACTORY = new ObjectFactory();

    @Autowired
    private HttpDOIService doiService;

    @Autowired
    private ServiceAppService serviceAppService;

    @PayloadRoot(localPart = "MintDoiRequest", namespace = SERVICE_NS)
    @ResponsePayload
    public MintDoiResponse mintDoi(@RequestPayload MintDoiRequest request) {

        //get client request ip
//        TransportContext context = TransportContextHolder.getTransportContext();
//        HttpServletConnection connection = (HttpServletConnection) context.getConnection();
//        HttpServletRequest httpServletRequest = connection.getHttpServletRequest();
//        String ipAddress = MIPUtils.getRemoteAddr(httpServletRequest);
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("=== client ip address : " + ipAddress);
//        }
        String serviceId = request.getServiceId();
        if (StringUtils.isBlank(serviceId)) {
            throw new DoiValidateException("The serviceId is invalid");
        }

        //check the the ServiceApp and Authorized App
        ServiceApp serviceApp = serviceAppService.getServiceAppByUniqueId(serviceId);
        if (serviceApp == null) {
            logger.error("Unauthorized service id : " + serviceId);
            throw new DoiValidateException("This doi service is not authorized");
        }
        AuthorizedApp authorizedApp = serviceApp.getAuthorizedApp();
        String appId = authorizedApp.getAppId();

        DResource dResource = request.getResource();
        String url = request.getUrl();

        //convert to DoiResource
        DoiResource doiResource = convertToDoiResource(null, dResource, url, true);
        //call doi minting
        DoiResponse response = doiService.mintDoi(appId, doiResource);
        //return MintDoiResponse
        return generateMintResponse(response, serviceId);
    }

    @PayloadRoot(localPart = "UpdateDoiRequest", namespace = SERVICE_NS)
    @ResponsePayload
    public UpdateDoiResponse updateDoi(@RequestPayload UpdateDoiRequest request) {
        String serviceId = request.getServiceId();
        if (StringUtils.isBlank(serviceId)) {
            throw new DoiValidateException("The serviceId is invalid");
        }

        //check the the ServiceApp and Authorized App
        ServiceApp serviceApp = serviceAppService.getServiceAppByUniqueId(serviceId);
        if (serviceApp == null) {
            logger.error("Unauthorized service id : " + serviceId);
            throw new DoiValidateException("This doi service is not authorized");
        }
        AuthorizedApp authorizedApp = serviceApp.getAuthorizedApp();
        String appId = authorizedApp.getAppId();

        //check the doi for update
        String doi = request.getDoi();
        DResource dResource = request.getResource();
        String url = request.getUrl();
        //convert to DoiResource
        DoiResource doiResource = convertToDoiResource(doi, dResource, url, false);
        //call doi updating
        DoiResponse response = doiService.updateDoi(appId, doiResource);
        //return UpdateDoiResponse
        return generateUpdateResponse(response, serviceId);
    }

    @PayloadRoot(localPart = "ActivateDoiRequest", namespace = SERVICE_NS)
    @ResponsePayload
    public ActivateDoiResponse activateDoi(@RequestPayload ActivateDoiRequest request) {
        String serviceId = request.getServiceId();
        if (StringUtils.isBlank(serviceId)) {
            throw new DoiValidateException("The serviceId is invalid");
        }
        //check the the ServiceApp and Authorized App
        ServiceApp serviceApp = serviceAppService.getServiceAppByUniqueId(serviceId);
        if (serviceApp == null) {
            logger.error("Unauthorized service id : " + serviceId);
            throw new DoiValidateException("This doi service is not authorized");
        }
        AuthorizedApp authorizedApp = serviceApp.getAuthorizedApp();
        String appId = authorizedApp.getAppId();

        //check the doi for update
        String doi = request.getDoi();
        //call doi activate
        DoiResponse response = doiService.activateDoi(appId, doi);
        //return ActivateDoiResponse
        return generateActivateResponse(response, serviceId);
    }

    @PayloadRoot(localPart = "DeactivateDoiRequest", namespace = SERVICE_NS)
    @ResponsePayload
    public DeactivateDoiResponse deactivateDoi(@RequestPayload DeactivateDoiRequest request) {
        String serviceId = request.getServiceId();
        if (StringUtils.isBlank(serviceId)) {
            throw new DoiValidateException("The serviceId is invalid");
        }

        //check the the ServiceApp and Authorized App
        ServiceApp serviceApp = serviceAppService.getServiceAppByUniqueId(serviceId);
        if (serviceApp == null) {
            logger.error("Unauthorized service id : " + serviceId);
            throw new DoiValidateException("This doi service is not authorized");
        }
        AuthorizedApp authorizedApp = serviceApp.getAuthorizedApp();
        String appId = authorizedApp.getAppId();

        //check the doi
        String doi = request.getDoi();
        //call doi activate
        DoiResponse response = doiService.deactivateDoi(appId, doi);

        //return DeactivateDoiResponse
        return generateDeactivateResponse(response, serviceId);
    }


    private DoiResource convertToDoiResource(String doi, DResource dResource, String url, boolean isMint) {
        DoiResource doiResource = new DoiResource();
        if (!isMint) {
            if (StringUtils.isBlank(doi)) {
                throw new DoiValidateException("The doi is empty");
            } else {
                doiResource.setDoi(doi);
            }
        }

        //set url
        if (StringUtils.isBlank(url)) {
            if (isMint) {
                throw new DoiValidateException("The minting url is empty");
            }
        } else {
            if (!isValidUrl(url)) {
                throw new DoiValidateException("The url is invalid");
            }
            doiResource.setUrl(url);
        }

        //set publication year
        Date publicationYear = dResource.getPublicationYear();
        if (publicationYear == null) {
            throw new DoiValidateException("The publication year is empty");
        } else {
            if (isValidYYYYDate(publicationYear)) {
                doiResource.setPublicationYear(dResource.getPublicationYear());
            } else {
                throw new DoiValidateException("The publication year is invalid");
            }
        }

        //set creators
        List<DoiCreator> creators = genDoiCreators(dResource);
        if (creators != null && creators.size() > 0) {
            doiResource.setDoiCreators(creators);
        } else {
            throw new DoiValidateException("The creator is empty");
        }
        //set titles
        List<DoiTitle> doiTitles = genTitles(dResource);
        if (doiTitles != null && doiTitles.size() > 0) {
            doiResource.setTitles(genTitles(dResource));
        } else {
            throw new DoiValidateException("The title is empty");
        }

        //set publisher
        DoiPublisher publisher = genDoiPublisher(dResource.getPublisher());
        if (publisher != null) {
            doiResource.setPublisher(publisher);
        } else {
            throw new DoiValidateException("The publisher is empty");
        }
        //set subjects
        List<DoiSubject> doiSubjects = genSubjects(dResource);
        if (doiSubjects != null && doiSubjects.size() > 0) {
            doiResource.setSubjects(genSubjects(dResource));
        }

        //set Contributors
        List<DoiContributor> doiContributors = genDoiContributors(dResource);
        if (doiContributors != null && doiContributors.size() > 0) {
            doiResource.setContributors(doiContributors);
        }

        //set Relevant Dates
        List<DoiRelevantDate> doiRelevantDates = genRelevantDates(dResource);
        if (doiRelevantDates != null && doiRelevantDates.size() > 0) {
            doiResource.setRelevantDates(doiRelevantDates);
        }
        //set language
        String language = dResource.getLanguage();
        if (language != null) {
            if (StringUtils.isEmpty(language.trim())) {
                throw new DoiValidateException("The language is empty");
            } else {
                doiResource.setLanguage(language);
            }
        }

        //set resource type
        DoiResourceType doiResourceType = genDoiResourceType(dResource);
        if (doiResourceType != null) {
            doiResource.setResourceType(doiResourceType);
        }
        //set alternateIdentifiers
        List<DoiAlternateIdentifier> doiAlternateIdentifiers = genDoiAlternateIdentifiers(dResource);
        if (doiAlternateIdentifiers != null && doiAlternateIdentifiers.size() > 0) {
            doiResource.setAlternateIdentifiers(doiAlternateIdentifiers);
        }
        //set relatedIdentifiers
        List<DoiRelatedIdentifier> doiRelatedIdentifiers = genDoiRelatedIdentifiers(dResource);
        if (doiRelatedIdentifiers != null && doiAlternateIdentifiers.size() > 0) {
            doiResource.setRelatedIdentifiers(doiRelatedIdentifiers);
        }

        //set the resource sizes
        List<DoiResourceSize> doiResourceSizes = genDoiResourceSizes(dResource);
        if (doiResourceSizes != null && doiResourceSizes.size() > 0) {
            doiResource.setSizes(doiResourceSizes);
        }

        //set resource formats
        List<DoiResourceFormat> doiResourceFormats = genDoiResourceFormats(dResource);
        if (doiResourceFormats != null && doiResourceFormats.size() > 0) {
            doiResource.setFormats(doiResourceFormats);
        }

        //set resource version
        String resourceVersion = dResource.getVersion();
        if (resourceVersion != null) {
            if (StringUtils.isEmpty(resourceVersion.trim())) {
                throw new DoiValidateException("The resourceVersion is empty");
            } else {
                doiResource.setResourceVersion(resourceVersion);
            }
        }

        //set rights
        String rights = dResource.getRights();
        if (rights != null) {
            if (StringUtils.isEmpty(rights.trim())) {
                throw new DoiValidateException("The rights is empty");
            } else {
                doiResource.setRights(rights);
            }
        }

        //set descriptions
        List<DoiDescription> doiDescriptions = genDoiDescriptions(dResource);
        if (doiDescriptions != null && doiDescriptions.size() > 0) {
            doiResource.setDescriptions(doiDescriptions);
        }

        return doiResource;
    }

    private boolean isValidUrl(String url) {
        UrlValidator urlValidator = UrlValidator.getInstance();
        return urlValidator.isValid(url);
    }

    private boolean isValidYYYYDate(Date date) {
        String yyyyDate = MDUtils.yyyyDateFormat(date);
        DateValidator dateValidator = DateValidator.getInstance();
        return dateValidator.isValid(yyyyDate, "yyyy");
    }

    private boolean isValidYYYYOrYYYYMMDDDate(Date date) {
        //verify yyyy-MM-dd format first
        String yyyyMMddDate = MDUtils.yyyyMMddDateFormat(date);
        DateValidator dateValidator = DateValidator.getInstance();
        boolean valid = dateValidator.isValid(yyyyMMddDate, "yyyy-MM-dd");
        if (valid) {
            return valid;
        }
        //then very yyyy format
        String yyyyDate = MDUtils.yyyyDateFormat(date);
        return dateValidator.isValid(yyyyDate, "yyyy");
    }

    private List<DoiCreator> genDoiCreators(DResource dResource) {
        List<DoiCreator> doiCreators = new ArrayList<>();
        DCreators dReqCreator = dResource.getCreators();
        if (dReqCreator != null) {
            List<DCreator> dCreators = dReqCreator.getCreator();
            for (DCreator dc : dCreators) {
                DoiCreator doiCreator = new DoiCreator();
                String creatorName = dc.getCreatorName();
                if (StringUtils.isBlank(creatorName)) {
                    throw new DoiValidateException("The creator name is empty");
                }
                doiCreator.setCreatorName(creatorName);

                //nameIdentifier is optional
                //we have to check whether it's provided or not
                DNameIdentifier dNameIdentifier = dc.getNameIdentifier();

                if (dNameIdentifier != null) {
                    //new DoiNameIdentifier
                    DoiNameIdentifier doiNameIdentifier = new DoiNameIdentifier();
                    String nameIdentifier = dNameIdentifier.getValue();
                    if (StringUtils.isBlank(nameIdentifier)) {
                        throw new DoiValidateException("The creator nameIdentifier is empty");
                    }
                    doiNameIdentifier.setIdentifier(nameIdentifier);
                    String nameIdentifierScheme = dNameIdentifier.getNameIdentifierScheme();
                    if (StringUtils.isBlank(nameIdentifierScheme)) {
                        throw new DoiValidateException("The nameIdentifierScheme attribute is invalid");
                    }
                    doiNameIdentifier.setNameIdentifierScheme(nameIdentifierScheme);
                    doiCreator.setNameIdentifier(doiNameIdentifier);
                }
                doiCreators.add(doiCreator);
            }
        }
        return doiCreators;
    }

    private List<DoiTitle> genTitles(DResource dResource) {
        List<DoiTitle> doiTitles = new ArrayList<>();
        DTitles dTitle = dResource.getTitles();
        if (dTitle != null) {
            List<DTitle> titles = dTitle.getTitle();
            for (DTitle dt : titles) {
                DoiTitle doiTitle = new DoiTitle();
                String title = dt.getValue();
                doiTitle.setTitle(title);
                //TitleType attribute is optional
                DTitleTypeAtt dTitleTypeAtt = dt.getTitleType();
                if (dTitleTypeAtt != null) {
                    doiTitle.setTitleType(DoiTitleType.fromValue(dTitleTypeAtt.value()).value());
                }
                doiTitles.add(doiTitle);
            }
        }
        return doiTitles;
    }

    private DoiPublisher genDoiPublisher(String publisher) {
        if (StringUtils.isBlank(publisher)) {
            return null;
        }
        DoiPublisher doiPublisher = new DoiPublisher();
        doiPublisher.setPublisher(publisher);
        return doiPublisher;
    }

    private List<DoiSubject> genSubjects(DResource dResource) {
        List<DoiSubject> doiSubjects = new ArrayList<>();
        DSubjects dSubject = dResource.getSubjects();
        if (dSubject != null) {
            List<DSubject> dSubjects = dSubject.getSubject();
            for (DSubject ds : dSubjects) {
                String subject = ds.getValue();
                if (StringUtils.isBlank(subject)) {
                    throw new DoiValidateException("The subject is empty");
                }
                DoiSubject doiSubject = new DoiSubject();
                doiSubject.setSubject(subject);

                String subjectScheme = ds.getSubjectScheme();
                //subjectScheme is optional
                if (StringUtils.isNotBlank(subjectScheme)) {
                    doiSubject.setSubjectScheme(subjectScheme);
                }
                doiSubjects.add(doiSubject);
            }
        }
        return doiSubjects;
    }

    private List<DoiContributor> genDoiContributors(DResource dResource) {
        List<DoiContributor> doiContributors = new ArrayList<>();
        DContributors dContributor = dResource.getContributors();
        if (dContributor != null) {
            List<DContributor> dContributors = dContributor.getContributor();
            for (DContributor dc : dContributors) {
                DoiContributor doiContributor = new DoiContributor();
                String contributeName = dc.getContributorName();
                if (StringUtils.isBlank(contributeName)) {
                    throw new DoiValidateException("The contribute name is empty");
                }
                doiContributor.setContributorName(contributeName);

                DContributorTypeAtt contributorTypeAtt = dc.getContributorType();
                if (contributorTypeAtt == null) {
                    throw new DoiValidateException("The contributeType attribute invalid");
                }
                doiContributor.setContributorType(contributorTypeAtt.value());
                //NameIdentifier is optional
                DNameIdentifier dNameIdentifier = dc.getNameIdentifier();
                if (dNameIdentifier != null) {
                    String nameIdentifier = dNameIdentifier.getValue();
                    if (StringUtils.isBlank(nameIdentifier)) {
                        throw new DoiValidateException("The creator nameIdentifier is empty");
                    }

                    DoiNameIdentifier doiNameIdentifier = new DoiNameIdentifier();
                    doiNameIdentifier.setIdentifier(nameIdentifier);
                    //nameIdentifierScheme attribute
                    String nameIdentifierScheme = dNameIdentifier.getNameIdentifierScheme();
                    if (StringUtils.isBlank(nameIdentifierScheme)) {
                        throw new DoiValidateException("The nameIdentifierScheme attribute is invalid");
                    }
                    doiNameIdentifier.setNameIdentifierScheme(nameIdentifierScheme);
                    doiContributor.setNameIdentifier(doiNameIdentifier);
                }
                doiContributors.add(doiContributor);
            }
        }
        return doiContributors;
    }

    private List<DoiRelevantDate> genRelevantDates(DResource dResource) {
        List<DoiRelevantDate> doiRelevantDates = new ArrayList<>();
        DDates dDates = dResource.getDates();
        if (dDates != null) {
            List<DDate> dDateList = dDates.getDate();
            for (DDate dDate : dDateList) {
                DoiRelevantDate doiRelevantDate = new DoiRelevantDate();
                Date date = dDate.getValue();
                if (date == null) {
                    throw new DoiValidateException("The relevant date is empty");
                }
                if (isValidYYYYOrYYYYMMDDDate(date)) {
                    doiRelevantDate.setRelevantDate(date);
                } else {
                    throw new DoiValidateException("The relevant date is invalid");
                }
                DDateTypeAtt dDateTypeAtt = dDate.getDateType();
                if (dDateTypeAtt == null) {
                    throw new DoiValidateException("The dateType attribute is invalid");
                }
                doiRelevantDate.setDateType(dDateTypeAtt.value());
                doiRelevantDates.add(doiRelevantDate);
            }
        }
        return doiRelevantDates;
    }

    private DoiResourceType genDoiResourceType(DResource dResource) {
        DResourceType dResourceType = dResource.getResourceType();
        if (dResourceType != null) {

            String type = dResourceType.getValue();
            if (StringUtils.isBlank(type)) {
                throw new DoiValidateException("The resourceType is empty");
            }
            DoiResourceType doiResourceType = new DoiResourceType();
            doiResourceType.setType(dResourceType.getValue());

            DResourceTypeGeneralAtt dResourceTypeGeneralAtt = dResourceType.getResourceTypeGeneral();
            if (dResourceTypeGeneralAtt == null) {
                throw new DoiValidateException("The resourceTypeGeneral attribute is invalid");

            }
            doiResourceType.setResourceTypeGeneral(dResourceTypeGeneralAtt.value());

            return doiResourceType;
        }
        return null;
    }

    private List<DoiAlternateIdentifier> genDoiAlternateIdentifiers(DResource dResource) {
        List<DoiAlternateIdentifier> doiAlternateIdentifiers = new ArrayList<>();
        DAlternateIdentifiers dAlternateIdentifiers = dResource.getAlternateIdentifiers();
        if (dAlternateIdentifiers != null) {
            List<DAlternateIdentifier> dAlternateIdentifierList = dAlternateIdentifiers.getAlternateIdentifier();
            for (DAlternateIdentifier dAlId : dAlternateIdentifierList) {

                String alternateIdentifier = dAlId.getValue();
                if (StringUtils.isBlank(alternateIdentifier)) {
                    throw new DoiValidateException("The value of the alternateIdentifier is empty");
                }
                String alternateIdentifierType = dAlId.getAlternateIdentifierType();
                if (StringUtils.isBlank(alternateIdentifierType)) {
                    throw new DoiValidateException("The alternateIdentifierType attribute is invalid");
                }
                DoiAlternateIdentifier doiAlternateIdentifier = new DoiAlternateIdentifier();
                doiAlternateIdentifier.setAlternateIdentifier(alternateIdentifier);
                doiAlternateIdentifier.setAlternateIdentifierType(alternateIdentifierType);
                doiAlternateIdentifiers.add(doiAlternateIdentifier);
            }
        }
        return doiAlternateIdentifiers;
    }

    private List<DoiRelatedIdentifier> genDoiRelatedIdentifiers(DResource dResource) {
        List<DoiRelatedIdentifier> doiRelatedIdentifiers = new ArrayList<>();
        DRelatedIdentifiers dRelatedIdentifiers = dResource.getRelatedIdentifiers();
        if (dRelatedIdentifiers != null) {
            List<DRelatedIdentifier> dRelatedIdentifierList = dRelatedIdentifiers.getRelatedIdentifier();
            for (DRelatedIdentifier drId : dRelatedIdentifierList) {
                String relatedId = drId.getValue();
                DRelatedIdentifierTypeAtt dRelatedIdentifierTypeAtt = drId.getRelatedIdentifierType();
                DRelationTypeAtt dRelationTypeAtt = drId.getRelationType();

                if (StringUtils.isBlank(relatedId)) {
                    throw new DoiValidateException("The value of the relatedIdentifier is empty");
                }
                if (drId != null) {
                    if (dRelatedIdentifierTypeAtt == null) {
                        throw new DoiValidateException("The relatedIdentifierType attribute is invalid");
                    }
                    if (dRelationTypeAtt == null) {
                        throw new DoiValidateException("The relationType attribute is invalid");
                    }
                }

                DoiRelatedIdentifier doiRelatedIdentifier = new DoiRelatedIdentifier();
                doiRelatedIdentifier.setRelatedIdentifier(relatedId);
                doiRelatedIdentifier.setRelatedIdentifierType(dRelatedIdentifierTypeAtt.value());
                doiRelatedIdentifier.setRelationType(dRelationTypeAtt.value());
                doiRelatedIdentifiers.add(doiRelatedIdentifier);
            }
        }
        return doiRelatedIdentifiers;
    }

    private List<DoiResourceSize> genDoiResourceSizes(DResource dResource) {
        List<DoiResourceSize> doiResourceSizes = new ArrayList<>();
        DSizes dSizes = dResource.getSizes();
        if (dSizes != null) {
            List<String> dsizeList = dSizes.getSize();
            for (String size : dsizeList) {
                if (StringUtils.isBlank(size)) {
                    throw new DoiValidateException("The size is empty");
                }
                DoiResourceSize doiResourceSize = new DoiResourceSize();
                doiResourceSize.setSize(size);
                doiResourceSizes.add(doiResourceSize);
            }
        }
        return doiResourceSizes;
    }

    private List<DoiResourceFormat> genDoiResourceFormats(DResource dResource) {
        List<DoiResourceFormat> doiResourceFormats = new ArrayList<>();
        DFormats dFormats = dResource.getFormats();
        if (dFormats != null) {
            List<String> dFormatList = dFormats.getFormat();
            for (String format : dFormatList) {
                if (StringUtils.isBlank(format)) {
                    throw new DoiValidateException("The format is empty");
                }
                DoiResourceFormat doiResourceFormat = new DoiResourceFormat();
                doiResourceFormat.setFormat(format);
                doiResourceFormats.add(doiResourceFormat);
            }
        }
        return doiResourceFormats;
    }

    private List<DoiDescription> genDoiDescriptions(DResource dResource) {
        List<DoiDescription> doiDescriptions = new ArrayList<>();
        DDescriptions dDescriptions = dResource.getDescriptions();
        if (dDescriptions != null) {
            List<DDescription> dDescriptionList = dDescriptions.getDescription();
            for (DDescription ddesc : dDescriptionList) {
                String desc = ddesc.getValue();
                if (StringUtils.isBlank(desc)) {
                    throw new DoiValidateException("The description is empty");
                }
                DoiDescription doiDescription = new DoiDescription();
                doiDescription.setDescription(desc);

                //attribute
                DDescriptionTypeAtt dDescriptionTypeAtt = ddesc.getDescriptionType();
                if (dDescriptionTypeAtt == null) {
                    throw new DoiValidateException("The descriptionType attribute  is invalid");
                }
                doiDescription.setDescriptionType(dDescriptionTypeAtt.value());
                doiDescriptions.add(doiDescription);
            }
        }
        return doiDescriptions;
    }

    private MintDoiResponse generateMintResponse(DoiResponse response, String serviceId) {
        MintDoiResponse mintDoiResponse = JAXB_OBJECT_FACTORY.createMintDoiResponse();
        mintDoiResponse.setResponsecode(DResponseCode.fromValue(response.getResponseCode()));
        mintDoiResponse.setType(DResponseTypeAtt.fromValue(response.getType()));
        mintDoiResponse.setServiceId(serviceId);
        mintDoiResponse.setDoi(response.getDoi());
        mintDoiResponse.setUrl(response.getUrl());
        mintDoiResponse.setMessage(response.getMessage());
        mintDoiResponse.setVerbosemessage(response.getVerboseMessage());
        return mintDoiResponse;
    }

    private UpdateDoiResponse generateUpdateResponse(DoiResponse response, String serviceId) {
        UpdateDoiResponse updateDoiResponse = JAXB_OBJECT_FACTORY.createUpdateDoiResponse();
        updateDoiResponse.setResponsecode(DResponseCode.fromValue(response.getResponseCode()));
        updateDoiResponse.setType(DResponseTypeAtt.fromValue(response.getType()));
        updateDoiResponse.setServiceId(serviceId);
        updateDoiResponse.setDoi(response.getDoi());
        updateDoiResponse.setUrl(response.getUrl());
        updateDoiResponse.setMessage(response.getMessage());
        updateDoiResponse.setVerbosemessage(response.getVerboseMessage());
        return updateDoiResponse;
    }

    private ActivateDoiResponse generateActivateResponse(DoiResponse response, String serviceId) {
        ActivateDoiResponse activateDoiResponse = JAXB_OBJECT_FACTORY.createActivateDoiResponse();
        activateDoiResponse.setResponsecode(DResponseCode.fromValue(response.getResponseCode()));
        activateDoiResponse.setType(DResponseTypeAtt.fromValue(response.getType()));
        activateDoiResponse.setServiceId(serviceId);
        activateDoiResponse.setDoi(response.getDoi());
        activateDoiResponse.setUrl(response.getUrl());
        activateDoiResponse.setMessage(response.getMessage());
        activateDoiResponse.setVerbosemessage(response.getVerboseMessage());
        return activateDoiResponse;
    }

    private DeactivateDoiResponse generateDeactivateResponse(DoiResponse response, String serviceId) {
        DeactivateDoiResponse deactivateDoiResponse = JAXB_OBJECT_FACTORY.createDeactivateDoiResponse();
        deactivateDoiResponse.setResponsecode(DResponseCode.fromValue(response.getResponseCode()));
        deactivateDoiResponse.setType(DResponseTypeAtt.fromValue(response.getType()));
        deactivateDoiResponse.setServiceId(serviceId);
        deactivateDoiResponse.setDoi(response.getDoi());
        deactivateDoiResponse.setUrl(response.getUrl());
        deactivateDoiResponse.setMessage(response.getMessage());
        deactivateDoiResponse.setVerbosemessage(response.getVerboseMessage());
        return deactivateDoiResponse;
    }
}
