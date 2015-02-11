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

package edu.monash.merc.eddy.modc.web.security;

import edu.monash.merc.eddy.modc.web.conts.MConts;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 15/10/2014
 */
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("Pre-handle");
//        System.out.println("========= in security interceptor the getRequestURI : " + request.getRequestURI());
//        System.out.println("==========> request getRequestURL : " + request.getRequestURL());
//        System.out.println("==========> request context path : " + request.getContextPath());

        String requestURL = request.getServletPath();
//        System.out.println("==========> request servlet path : " + requestURL);
//        HandlerMethod hm = (HandlerMethod) handler;
//        String actionName = hm.getMethod().getName();
//        System.out.println("================== method name : " + actionName);

        //action path
        String actionPath = requestURL.substring(0, requestURL.indexOf("/", 1));
        @SuppressWarnings("unchecked")
        Map<String, Object> parameters = request.getParameterMap();
        if (parameters != null) {
            requestURL += "?";
            for (String paramKey : parameters.keySet()) {
                Object obj = parameters.get(paramKey);
                if (obj instanceof Object[]) {
                    Object[] objArray = (Object[]) obj;
                    if (objArray.length > 0) {
                        for (int index = 0; index < objArray.length; index++) {
                            Object valueAtIndex = objArray[index];
                            requestURL = requestURL + paramKey + "=" + String.valueOf(valueAtIndex) + "&";
                        }
                    }
                } else {
                    requestURL = requestURL + paramKey + "=" + String.valueOf(obj) + "&";
                }
            }
        }
        request.getSession().setAttribute(MConts.REQUESTED_URL, requestURL);

        String authenticated = (String) request.getSession().getAttribute(MConts.SE_AUTHEN_FLAG);
        if (!StringUtils.equals(authenticated, MConts.SE_LOGIN)) {
            response.sendRedirect(request.getContextPath() + "/user/user_login.htm");
            return true;
        }

        if (StringUtils.equalsIgnoreCase(actionPath, "/admin") || StringUtils.equalsIgnoreCase(actionPath, "/service")) {
            int userType = (Integer) request.getSession().getAttribute(MConts.SE_USER_TYPE);
            //check the user permission. it's super admin 1 or admin 2
            if (userType != 1 && userType != 2) {
                response.sendRedirect(request.getContextPath() + "/perm/perm_denied.htm");
                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
