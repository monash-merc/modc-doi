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

package edu.monash.merc.eddy.modc.web.controller;

import edu.monash.merc.eddy.modc.common.util.MDUtils;
import edu.monash.merc.eddy.modc.doi.DOIServiceHelper;
import edu.monash.merc.eddy.modc.doi.DoiResponse;
import edu.monash.merc.eddy.modc.doi.HttpDOIService;
import edu.monash.merc.eddy.modc.domain.doi.*;
import edu.monash.merc.eddy.modc.web.validation.MDValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 23/09/2014
 */
@Controller
@RequestMapping("/doi")
public class DoiController extends BaseController {

    private Map<String, String> publicationYears;

    @Autowired
    private HttpDOIService doiService;

    @Autowired
    private DOIServiceHelper doiServiceHelper;

    @ModelAttribute("publicationYears")
    public Map<String, String> initYears() {
        publicationYears = new LinkedHashMap<>();
        for (int i = 2050; i >= 1900; i--) {
            publicationYears.put(String.valueOf(i), String.valueOf(i));
        }
        return publicationYears;
    }

    @RequestMapping(value = "/show_mint", method = RequestMethod.GET)
    public String mintDoi(HttpServletRequest request, Model model) {
        DoiResource doiResource = new DoiResource();
        model.addAttribute("doiResource", doiResource);
        doiResource.setPublicationYear(MDUtils.getToday());
        DoiPublisher publisher = new DoiPublisher();
        publisher.setPublisher("Monash University");
        doiResource.setPublisher(publisher);
        return "doi/doi_mint";
    }

    @RequestMapping(value = "/mint")
    public String mint(@ModelAttribute("doiResource") DoiResource doiResource, HttpServletRequest request, Model model) {
        try {
            //add action support
            actionSupport(request, model);
            //validate the request
            validate(doiResource, true);

            if (hasActionErrors()) {
                logger.error("Validation failed");
                makeErrorAware();
                return "doi/doi_mint";
            }

            String defaultAuthorizedAppId = doiServiceHelper.getDefaultAuthorizedAppId();
            DoiResponse doiResponse = doiService.mintDoi(defaultAuthorizedAppId, doiResource);
            model.addAttribute("doiResponse", doiResponse);
            String responseType = doiResponse.getType();
            if (StringUtils.equalsIgnoreCase(responseType, "success")) {
                String message = doiResponse.getMessage();
                addActionMessage("doi.mint.success", null, message);
                makeMessageAware();
                return "doi/doi_success";
            } else {
                String message = doiResponse.getMessage();
                String verbMessage = doiResponse.getVerboseMessage();
                if (StringUtils.isNotBlank(verbMessage)) {
                    addActionError("doi.mint.failure.verbmsg", null, verbMessage);
                }
                if (StringUtils.isNotBlank(message)) {
                    addActionError("doi.mint.failure.msg", null, message);
                }
                makeErrorAware();
                return "doi/doi_mint";
            }
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("doi.mint.doi.failed.msg");
            makeErrorAware();
            return "doi/doi_mint";
        }
    }

    private void validate(DoiResource doiResource, boolean isMinting) {
        if (doiResource == null) {
            addActionError("doi.param.empty");
            return;
        }

        if (isMinting) {
            String url = doiResource.getUrl();
            if (StringUtils.isBlank(url)) {
                addActionError("doi.param.url.required");
            } else {
                if (!MDValidator.validateURL(url)) {
                    addActionError("doi.param.url.invalid");
                }
            }
        }

        List<DoiCreator> creators = doiResource.getDoiCreators();
        if (creators != null && creators.size() >= 0) {
            for (DoiCreator creator : creators) {
                String creatorName = creator.getCreatorName();
                if (StringUtils.isBlank(creatorName)) {
                    addActionError("doi.param.creator.name.required");
                }
                DoiNameIdentifier nameIdentifier = creator.getNameIdentifier();
                String identifier = nameIdentifier.getIdentifier();
                String scheme = nameIdentifier.getNameIdentifierScheme();
                if (StringUtils.isNotBlank(identifier) && StringUtils.isBlank(scheme)) {
                    addActionError("doi.param.creator.name.identifier.scheme.required");
                }
                if (StringUtils.isBlank(identifier)) {
                    creator.setNameIdentifier(null);
                }
            }
        } else {
            addActionError("doi.param.creator.at.least.one.required");
        }

        List<DoiTitle> titles = doiResource.getTitles();
        if (titles != null && titles.size() >= 0) {
            for (DoiTitle doiTitle : titles) {
                String title = doiTitle.getTitle();
                if (StringUtils.isBlank(title)) {
                    addActionError("doi.param.title.required");
                }
                String titleType = doiTitle.getTitleType();
                if (StringUtils.equalsIgnoreCase("none", titleType)) {
                    doiTitle.setTitleType(null);
                }
            }
        } else {
            addActionError("doi.param.title.at.least.one.required");
        }

        DoiPublisher publisher = doiResource.getPublisher();
        String publisherName = publisher.getPublisher();
        if (StringUtils.isBlank(publisherName)) {
            addActionError("doi.param.publisher.required");
        }

        Date publicationYear = doiResource.getPublicationYear();
        if (publicationYear == null) {
            addActionError("doi.param.publication.year.required");
        }
    }
}
