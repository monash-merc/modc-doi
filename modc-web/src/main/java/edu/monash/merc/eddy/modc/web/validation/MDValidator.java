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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 14/10/2014
 */
public class MDValidator {

    private static Pattern invalidCharsPattern = Pattern.compile("[^a-zA-Z]");

    static Pattern digitPattern = Pattern.compile("[0-9]");

    static Pattern letterPattern = Pattern.compile("[a-zA-Z]");

    static Pattern specialCharsDefaultPattern = Pattern.compile("!@#$");


    public static boolean validateEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public static boolean validateIp(String ipAddress) {
        InetAddressValidator ipValidator = InetAddressValidator.getInstance();
        return ipValidator.isValid(ipAddress);
    }

    public static boolean validateURL(String url){
        UrlValidator urlValidator = UrlValidator.getInstance();
        return urlValidator.isValid(url);
    }

    public static boolean validateName(String name, int maxLen) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        name = name.trim();

        if (name.length() > maxLen) {
            return false;
        }

        Matcher letterMatcher = invalidCharsPattern.matcher(name);
        if (letterMatcher.find()) {
            return false;
        }

        return true;
    }

    public static boolean validatePassword(String password, int minLen, int maxLen) {
        if (StringUtils.isBlank(password)) {
            return false;
        }

        password = password.trim();
        if (password.length() < minLen || password.length() > maxLen) {
            return false;
        }

        Matcher digitMatcher = digitPattern.matcher(password);
        Matcher letterMatcher = letterPattern.matcher(password);
        if (!digitMatcher.find()) {
            return false;
        }
        if (!letterMatcher.find()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String password = "ml2qwesdafsfdsafsd";

        System.out.println("valid password? : " + MDValidator.validatePassword(password, 6, 10));
        String name = "Simonfdsaf";
        System.out.println("valid name? : " + MDValidator.validateName(name, 10));
    }

}
