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

import java.util.List;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 26/09/2014
 */
public interface ActionMessageAware {
    /**
     * Get the List of Action-level error messages for this action. Error messages should not
     * be added directly here, as implementations are free to return a new Collection or an
     * Unmodifiable Collection.
     *
     * @return List of String error messages
     */
    List<String> getActionErrors();

    /**
     * Add an Action-level error message to this Action.
     *
     * @param errorMessage the error message
     */
    void addActionError(String errorMessage);

    /**
     * Get the List of Action-level messages for this action. Messages should not be added
     * directly here, as implementations are free to return a new Collection or an Unmodifiable
     * Collection.
     *
     * @return List of String messages
     */
    List<String> getActionMessages();

    /**
     * Add an Action-level message to this Action.
     *
     * @param message the message
     */
    void addActionMessage(String message);

    /**
     * Check whether there are any Action-level error messages.
     *
     * @return true if any Action-level error messages have been registered
     */
    boolean hasActionErrors();

    /**
     * Checks whether there are any Action-level messages.
     *
     * @return true if any Action-level messages have been registered
     */
    boolean hasActionMessages();

    /**
     * Clears action errors list.
     * <p/>
     * Will clear the list that contains action errors.
     */
    void clearActionErrors();

    /**
     * Clears messages list.
     * <p/>
     * Will clear the list that contains action messages.
     */
    void clearMessages();

}
