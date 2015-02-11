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

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 26/09/2014
 */
public class ActionMessageAwareSupport implements ActionMessageAware {

    private List<String> actionErrors;

    private List<String> actionMessages;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public List<String> getActionErrors() {
        return internalGetActionErrors();
    }

    @Override
    public List<String> getActionMessages() {
        return internalGetActionMessages();
    }

    @Override
    public void addActionError(String errorMessage) {
        internalGetActionErrors().add(errorMessage);
    }


    @Override
    public void addActionMessage(String message) {
        internalGetActionMessages().add(message);
    }

    @Override
    public boolean hasActionMessages() {
        return (actionMessages != null) && !actionMessages.isEmpty();
    }

    @Override
    public boolean hasActionErrors() {
        return (actionErrors != null) && !actionErrors.isEmpty();
    }

    @Override
    public void clearMessages() {
        internalGetActionMessages().clear();
    }

    @Override
    public void clearActionErrors() {
        internalGetActionErrors().clear();
    }

    private List<String> internalGetActionErrors() {
        if (actionErrors == null) {
            actionErrors = new LinkedList<>();
        }
        return actionErrors;
    }


    private List<String> internalGetActionMessages() {
        if (actionMessages == null) {
            actionMessages = new LinkedList<>();
        }
        return actionMessages;
    }
}
