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

import edu.monash.merc.eddy.modc.common.config.SystemPropertyConts;
import edu.monash.merc.eddy.modc.common.exception.MException;
import edu.monash.merc.eddy.modc.domain.Profile;
import edu.monash.merc.eddy.modc.domain.User;
import edu.monash.merc.eddy.modc.domain.UserType;
import edu.monash.merc.eddy.modc.service.UserService;
import edu.monash.merc.eddy.modc.service.mail.MailService;
import edu.monash.merc.eddy.modc.web.conts.MConts;
import edu.monash.merc.eddy.modc.web.form.ManagedUserBean;
import edu.monash.merc.eddy.modc.web.form.RegistrationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 14/10/2014
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String activate(@RequestParam("id") long id, @RequestParam("code") String code, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        RegistrationBean registration = new RegistrationBean();
        model.addAttribute("registration", registration);
        try {
            User user = this.userService.getUserById(id);
            if (user == null) {
                addActionError("admin.activate.account.link.invalid");
                makeErrorAware();
                return "admin/activate_error";
            }

            if (user.getActivateHashCode() != null && !user.getActivateHashCode().equals(code)) {
                addActionError("admin.activate.account.link.invalid");
                makeErrorAware();
                return "admin/activate_error";
            }

            if (user.isActivated() || user.isRejected()) {
                addActionError("admin.activate.account.link.expired");
                makeErrorAware();
                return "admin/activate_error";
            }

            registration.setUser(user);
            Profile profile = user.getProfile();
            registration.setProfile(profile);
            return "admin/activate_account";
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("admin.validate.activation.link.failed");
            return "admin/activate_error";
        }
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public String activate(@ModelAttribute("registration") RegistrationBean registrationBean, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        try {
            User user = registrationBean.getUser();
            User checkedUser = this.userService.getUserById(user.getId());
            if (checkedUser == null) {
                // User doesn't existed
                addActionError("admin.activate.account.link.invalid");
                makeErrorAware();
                return "admin/activate_account";
            }

            if (checkedUser.isActivated() || checkedUser.isRejected()) {
                addActionError(getText("admin.activate.account.link.expired"));
                makeErrorAware();
                return "admin/activate_account";
            }

            if (checkedUser.getActivateHashCode() != null && (!checkedUser.getActivateHashCode().equals(user.getActivateHashCode()))) {
                addActionError(getText("admin.activate.account.link.invalid"));
                makeErrorAware();
                return "admin/activate_account";
            }

            //set the activated date
            checkedUser.setActivatedDate(GregorianCalendar.getInstance().getTime());
            //set the activated status to true
            checkedUser.setActivated(true);
            //update the user
            this.userService.updateUser(checkedUser);
            // site name
            String serverQName = getServerQName(request);
            // set action finished messsage
            Profile profile = registrationBean.getProfile();
            sendApprovalAccountEmail(serverQName, checkedUser.getDisplayName(), checkedUser.getEmail(), profile.getOrganization());
            addActionMessage("admin.activate.account.success.msg", new String[]{checkedUser.getEmail()});
            registrationBean.setUser(checkedUser);
            makeMessageAware();
            return "admin/activate_finished";
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("admin.activate.account.failed");
            makeErrorAware();
            return "admin/activate_account";
        }
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public String reject(@ModelAttribute("registration") RegistrationBean registrationBean, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        try {
            User user = registrationBean.getUser();
            User checkedUser = this.userService.getUserById(user.getId());
            if (checkedUser == null) {
                // User doesn't existed
                addActionError("admin.activate.account.link.invalid");
                makeErrorAware();
                return "admin/activate_account";
            }

            if (checkedUser.isActivated() || checkedUser.isRejected()) {
                addActionError(getText("admin.activate.account.link.expired"));
                makeErrorAware();
                return "admin/activate_account";
            }

            if (checkedUser.getActivateHashCode() != null && (!checkedUser.getActivateHashCode().equals(user.getActivateHashCode()))) {
                addActionError(getText("admin.activate.account.link.invalid"));
                makeErrorAware();
                return "admin/activate_account";
            }


            if (true) {
                throw new MException("some error");
            }

            //set the activated date
            checkedUser.setActivatedDate(GregorianCalendar.getInstance().getTime());
            //set the reject status to true
            checkedUser.setRejected(true);
            //update the user
            this.userService.updateUser(checkedUser);
            // site name
            String serverQName = getServerQName(request);
            // set action finished messsage
            Profile profile = registrationBean.getProfile();
            sendRejectAccountEmail(serverQName, checkedUser.getDisplayName(), checkedUser.getEmail(), profile.getOrganization());
            addActionMessage("admin.reject.account.success.msg", new String[]{checkedUser.getEmail()});
            registrationBean.setUser(checkedUser);
            makeMessageAware();
            return "admin/activate_finished";
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("admin.activate.account.failed");
            makeErrorAware();
            return "admin/activate_account";
        }
    }

    @RequestMapping(value = "/manage_user", method = RequestMethod.GET)
    public String manageUser(@RequestParam("id") long id, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        ManagedUserBean userBean = new ManagedUserBean();
        model.addAttribute("userBean", userBean);
        try {
            User user = this.userService.getUserById(id);
            if (user == null) {
                addActionError("admin.manage.user.not.found");
                makeErrorAware();
                return "admin/manage_user_error";
            }
            userBean.setUser(user);
            userBean.setProfile(user.getProfile());
            userBean.setAvatar(user.getAvatar());
            return "admin/manage_user";
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("admin.manage.user.check.user.details.error");
            makeErrorAware();
            return "admin/manage_user_error";
        }
    }

    @RequestMapping(value = "/manage_user", method = RequestMethod.POST)
    public String manageUser(@ModelAttribute("userBean") ManagedUserBean userBean, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        try {
            User user = userBean.getUser();
            String managedType = userBean.getManagedType();

            User regUser = this.userService.getUserById(user.getId());

            if (regUser == null) {
                addActionError("admin.manage.user.not.found");
                makeErrorAware();
                return "admin/manage_user_error";
            }

            if (managedType.equals(MConts.ACTIVATE)) {
                if (!regUser.isActivated()) {
                    regUser.setActivatedDate(GregorianCalendar.getInstance().getTime());
                    regUser.setActivated(true);
                    regUser.setRejected(false);
                    this.userService.updateUser(regUser);

                    // site name
                    String serverQName = getServerQName(request);
                    // set action finished messsage
                    Profile profile = regUser.getProfile();
                    sendApprovalAccountEmail(serverQName, regUser.getDisplayName(), regUser.getEmail(), profile.getOrganization());
                    addActionMessage("admin.manage.user.success", new String[]{("Activated " + regUser.getDisplayName() + " user account")});
                }
            }
            if (managedType.equals(MConts.DEACTIVATE)) {
                if (regUser.isActivated()) {
                    //set the activated date
                    regUser.setActivatedDate(GregorianCalendar.getInstance().getTime());
                    regUser.setActivated(false);
                    regUser.setRejected(false);
                    this.userService.updateUser(regUser);

                    // site name
                    String serverQName = getServerQName(request);
                    // set action finished messsage
                    Profile profile = regUser.getProfile();
                    sendRejectAccountEmail(serverQName, regUser.getDisplayName(), regUser.getEmail(), profile.getOrganization());
                    // set action successful message
                    addActionMessage("admin.manage.user.success", new String[]{("Deactivated " + regUser.getDisplayName() + " user account")});
                }
            }

            if (managedType.equals(MConts.SETASADMIN)) {
                if (regUser.getUserType() != UserType.ADMIN.code()) {
                    regUser.setUserType(UserType.ADMIN.code());
                    this.userService.updateUser(regUser);
                    // set action successful message
                    addActionMessage("admin.manage.user.success", new String[]{("Set " + regUser.getDisplayName() + " user as an admin")});
                }

            }
            if (managedType.equals(MConts.SETASUSER)) {
                if (regUser.getUserType() == UserType.ADMIN.code()) {
                    regUser.setUserType(UserType.USER.code());
                    this.userService.updateUser(regUser);
                    // set action successful message
                    addActionMessage("admin.manage.user.success", new String[]{("Set " + regUser.getDisplayName() + " user as a normal user")});
                }
            }

            makeMessageAware();
            userBean.setUser(regUser);
            userBean.setProfile(regUser.getProfile());
            userBean.setAvatar(regUser.getAvatar());
            return "admin/manage_user";
        } catch (Exception ex) {
            logger.error(ex);
            addActionMessage("admin.manage.user.failed");
            makeErrorAware();
            return "admin/manage_user_error";
        }
    }

    private void sendApprovalAccountEmail(String serverQName, String userFullName, String userEmail, String organization) {

        String approveAccountMailTemplateFile = "approveUserRegistrationEmailTemplate.ftl";

        // prepare to send email.
        String appName = systemPropertySettings.getPropValue(SystemPropertyConts.APPLICATION_NAME);
        String adminEmail = systemPropertySettings.getPropValue(SystemPropertyConts.SYSTEM_SERVICE_EMAIL);
        String subject = getText("admin.activate.account.notification.mail.title");

        Map<String, String> templateMap = new HashMap<String, String>();
        templateMap.put("RegisteredUser", userFullName);
        templateMap.put("UserEmail", userEmail);
        // Organization
        templateMap.put("Organization", organization);
        templateMap.put("SiteName", serverQName);
        templateMap.put("AppName", appName);

        // send an email to user
        this.mailService.sendMail(adminEmail, userEmail, subject, templateMap, approveAccountMailTemplateFile, true);
    }

    private void sendRejectAccountEmail(String serverQName, String userFullName, String userEmail, String organization) {

        String rejectAccountMailTemplateFile = "rejectUserRegistrationEmailTemplate.ftl";
        // prepare to send email.
        String appName = systemPropertySettings.getPropValue(SystemPropertyConts.APPLICATION_NAME);
        String adminEmail = systemPropertySettings.getPropValue(SystemPropertyConts.SYSTEM_SERVICE_EMAIL);
        String subject = getText("admin.reject.account.notification.mail.title");

        Map<String, String> templateMap = new HashMap<String, String>();
        templateMap.put("RegisteredUser", userFullName);
        templateMap.put("UserEmail", userEmail);
        // Organization
        templateMap.put("Organization", organization);
        templateMap.put("SiteName", serverQName);
        templateMap.put("AppName", appName);

        // send an email to user
        this.mailService.sendMail(adminEmail, userEmail, subject, templateMap, rejectAccountMailTemplateFile, true);
    }

}
