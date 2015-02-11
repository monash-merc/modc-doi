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

package edu.monash.merc.eddy.modc.web.validation;

import edu.monash.merc.eddy.modc.common.exception.MIllegalArgumentException;
import edu.monash.merc.eddy.modc.web.exception.MActionSupportException;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 26/09/2014
 */
public class ActionSupport {

    private static String ACTION_ERRORS = "actionErrors";

    private static String ACTION_MESSAGES = "actionMessages";

    private HttpServletRequest request;

    private Model model;

    private WebApplicationContext wac;

    private Locale locale;

    private ActionMessageAwareSupport messageAwareSupport;

    private ActionSupport(HttpServletRequest request, Model model) {
        if (request == null) {
            throw new MIllegalArgumentException("Request can't be null");
        }
        if (model == null) {
            throw new MIllegalArgumentException("Model can't be null");
        }

        this.request = request;
        WebApplicationContext webApplicationContext = RequestContextUtils.getWebApplicationContext(request);
        if (webApplicationContext != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(this.request);
            if (localeResolver != null) {
                this.locale = localeResolver.resolveLocale(request);
            }
            this.wac = webApplicationContext;
        }
        this.model = model;
        messageAwareSupport = new ActionMessageAwareSupport();
    }

    public static ActionSupport actionSupport(HttpServletRequest request, Model model) {
        return new ActionSupport(request, model);
    }

    public void addActionError(String code) {
        String error = wac.getMessage(code, null, locale);
        messageAwareSupport.addActionError(error);
    }

    public void addActionError(String code, String[] errorMessages) {
        String error = wac.getMessage(code, errorMessages, locale);
        messageAwareSupport.addActionError(error);
    }

    public void addActionError(String code, String[] errorMessages, String defaultErrorMessage) {
        String error = wac.getMessage(code, errorMessages, defaultErrorMessage, locale);
        messageAwareSupport.addActionError(error);
    }

    public void addActionMessage(String code) {
        String message = wac.getMessage(code, null, locale);
        messageAwareSupport.addActionMessage(message);
    }

    public void addActionMessage(String code, String[] actionMessages) {
        String message = wac.getMessage(code, actionMessages, locale);
        messageAwareSupport.addActionMessage(message);
    }

    public void addActionMessage(String code, String[] actionMessages, String defaultActionMessage) {
        String message = wac.getMessage(code, actionMessages, defaultActionMessage, locale);
        messageAwareSupport.addActionMessage(message);
    }

    public boolean hasActionMessages() {
        return this.messageAwareSupport.hasActionMessages();
    }

    public void makeMessageAware() {
        if (messageAwareSupport.hasActionMessages()) {
            List<String> messages = messageAwareSupport.getActionMessages();
            model.addAttribute(ACTION_MESSAGES, messages);
        }
    }

    public boolean hasActionErrors() {
        return messageAwareSupport.hasActionErrors();
    }

    public void makeErrorAware() {
        if (messageAwareSupport.hasActionErrors()) {
            List<String> errors = messageAwareSupport.getActionErrors();
            model.addAttribute(ACTION_ERRORS, errors);
        } else {
            throw new MActionSupportException("No action error");
        }
    }

    public String getText(String code) {
        return wac.getMessage(code, null, locale);
    }

    public String getText(String code, String[] actionMessages) {
        return wac.getMessage(code, actionMessages, locale);
    }

    public String getText(String code, String[] actionMessages, String defaultActionMessage) {
        return wac.getMessage(code, actionMessages, defaultActionMessage, locale);
    }
}
