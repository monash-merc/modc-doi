package edu.monash.merc.eddy.modc.web.controller;

import edu.monash.merc.eddy.modc.common.config.SystemPropertyConts;
import edu.monash.merc.eddy.modc.common.ldap.LdapUser;
import edu.monash.merc.eddy.modc.common.util.MD5;
import edu.monash.merc.eddy.modc.domain.Avatar;
import edu.monash.merc.eddy.modc.domain.Profile;
import edu.monash.merc.eddy.modc.domain.User;
import edu.monash.merc.eddy.modc.domain.UserType;
import edu.monash.merc.eddy.modc.service.UserService;
import edu.monash.merc.eddy.modc.service.ldap.LdapService;
import edu.monash.merc.eddy.modc.service.mail.MailService;
import edu.monash.merc.eddy.modc.web.conts.MConts;
import edu.monash.merc.eddy.modc.web.form.LoginBean;
import edu.monash.merc.eddy.modc.web.form.RegistrationBean;
import edu.monash.merc.eddy.modc.web.form.ResetPasswordBean;
import edu.monash.merc.eddy.modc.web.validation.MDValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by simonyu on 6/08/2014.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private LdapService ldapService;


    private Logger logger = Logger.getLogger(this.getClass().getName());

    @RequestMapping("/registration_options")
    public String regoptions() {

        return "user/registration_options";
    }

    @RequestMapping(value = "/ldap_register", method = RequestMethod.GET)
    public String ldapRegister(Model model) {
        RegistrationBean registration = new RegistrationBean();
        model.addAttribute("registration", registration);
        return "user/ldap_register";
    }

    @RequestMapping(value = "/ldap_register", method = RequestMethod.POST)
    public String ldapRegister(@ModelAttribute("registration") RegistrationBean registration, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        User user = registration.getUser();
        validateLdapReg(registration, request);
        if (hasActionErrors()) {
            makeErrorAware();
            return "user/ldap_register";
        } else {
            try {
                //check ldap user
                LdapUser ldapUser = this.ldapService.verifyLdapUser(user.getUniqueId(), user.getPassword());
                if (ldapUser == null) {
                    addActionError("user.invalid.authcateId.or.password");
                    makeErrorAware();
                    return "user/ldap_register";
                }

                //check authcate id
                boolean idExisted = this.userService.checkExistedUniqueId(user.getUniqueId());
                if (idExisted) {
                    addActionError("user.authcate.id.already.registed");
                    makeErrorAware();
                    return "user/ldap_register";
                }

                //check email
                boolean emailRegistered = this.userService.checkExistedEmail(ldapUser.getMail());
                if (emailRegistered) {
                    addActionError("user.email.already.registered");
                    makeErrorAware();
                    return "user/ldap_register";
                }

                // try to register ldap user in the database
                user.setDisplayName(ldapUser.getDisplayName());
                user.setFirstName(ldapUser.getFirstName());
                user.setLastName(ldapUser.getLastName());
                // set ldap user password as ldap
                user.setPassword("ldap");
                user.setRegisteredDate(GregorianCalendar.getInstance().getTime());
                user.setUidHashCode(generateSecurityHash(user.getUniqueId()));
                // set user email which get from ldap server
                user.setEmail(ldapUser.getMail());
                user.setActivateHashCode(generateSecurityHash(user.getUniqueId()));

                user.setActivated(false);
                user.setUserType(UserType.USER.code());

                // create a default user profile.
                Profile p = registration.getProfile();
                if (p == null) {
                    p = new Profile();
                }
                p.setOrganization("Monash University");

                p.setGender(ldapUser.getGender());
                user.setProfile(p);
                p.setUser(user);
                // create an avatar
                Avatar avatar = genAvatar(p.getGender());
                avatar.setUser(user);
                user.setAvatar(avatar);

                this.userService.saveUser(user);
                // site name
                String serverQName = getServerQName(request);
                String appContext = getAppContextPath(request);

                // start to send register email to admin for approval
                String activateURL = constructActivationURL(serverQName, appContext, user.getId(), user.getActivateHashCode());
                sendRegMailToAdmin(serverQName, user.getDisplayName(), user.getEmail(), p.getOrganization(), activateURL);
            } catch (Exception ex) {
                logger.error(ex);
                addActionError("user.registration.failed");
                makeErrorAware();
                return "user/ldap_register";
            }
        }
        //set success message
        addActionMessage("user.registration.finished.msg", new String[]{user.getDisplayName()});
        makeMessageAware();
        return "user/user_register_finished";
    }


    @RequestMapping(value = "/self_register", method = RequestMethod.GET)
    public String selfRegister(Model model) {
        RegistrationBean registration = new RegistrationBean();
        model.addAttribute("registration", registration);
        return "user/self_register";
    }

    @RequestMapping(value = "/self_register", method = RequestMethod.POST)
    public String selfRegister(@ModelAttribute("registration") RegistrationBean registration, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        validateReg(registration, request);
        if (hasActionErrors()) {
            makeErrorAware();
            return "user/self_register";
        }
        User user = registration.getUser();
        try {
            // encrypt the user password
            user.setPassword(MD5.hash(user.getPassword()));
            user.setRegisteredDate(GregorianCalendar.getInstance().getTime());
            // set the user email as a unique id
            user.setUniqueId(user.getEmail());
            // set the unique id hash code.
            user.setUidHashCode(generateSecurityHash(user.getEmail()));
            // set the activate hash code
            user.setActivateHashCode(generateSecurityHash(user.getEmail()));
            // set the user active into false
            user.setActivated(false);
            // set user type as a registered user.
            user.setUserType(UserType.USER.code());

            // create a default user profile.
            Profile p = registration.getProfile();
            p.setGender("Male");
            user.setProfile(p);
            p.setUser(user);

            // create an avatar
            Avatar avatar = genAvatar(p.getGender());
            user.setAvatar(avatar);
            avatar.setUser(user);

            // save user
            this.userService.saveUser(user);

            // site name
            String serverQName = getServerQName(request);
            String appContext = getAppContextPath(request);

            // start to send register email to admin for approval
            String activateURL = constructActivationURL(serverQName, appContext, user.getId(), user.getActivateHashCode());
            sendRegMailToAdmin(serverQName, user.getDisplayName(), user.getEmail(), p.getOrganization(), activateURL);
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("user.registration.failed");
            makeErrorAware();
            return "user/self_register";
        }
        //set success message
        addActionMessage("user.registration.finished.msg", new String[]{user.getDisplayName()});
        makeMessageAware();
        return "user/user_register_finished";
    }

    @RequestMapping(value = "/user_login", method = RequestMethod.GET)
    public String userLogin(Model model, HttpServletRequest request) {
        LoginBean loginBean = new LoginBean();
        model.addAttribute("userLogin", loginBean);
        cleanAuthenInSession(request);
        return "user/user_login";
    }

    @RequestMapping(value = "/user_login", method = RequestMethod.POST)
    public String userLogin(@ModelAttribute("userLogin") LoginBean login, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        validateLogin(login, request);
        if (hasActionErrors()) {
            makeErrorAware();
            return "user/user_login";
        }

        try {
            String ldapStr = systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_AUTH_SUPPORTED);
            boolean ldapsupported = Boolean.valueOf(ldapStr);
            User user = login.getUser();
            User loginUser = this.userService.login(user.getUniqueId(), user.getPassword(), ldapsupported);
            if (loginUser == null) {
                addActionError("user.login.credentials.invalid");
                makeErrorAware();
                return "user/user_login";
            } else {
                if (!loginUser.isActivated()) {
                    addActionError("user.login.account.inactive.error");
                    makeErrorAware();
                    return "user/user_login";
                }
                storeInSession(request, MConts.SE_AUTHEN_FLAG, MConts.SE_LOGIN);
                storeInSession(request, MConts.SE_AUTHEN_USER_ID, loginUser.getId());
                storeInSession(request, MConts.SE_AUTHEN_USER_NAME, loginUser.getDisplayName());
                storeInSession(request, MConts.SE_USER_TYPE, loginUser.getUserType());
                Avatar avatar = loginUser.getAvatar();
                String avatarName = avatar.getFileName();
                if (StringUtils.isNotBlank(avatarName)) {
                    storeInSession(request, MConts.SE_USER_AVATAR, avatarName);
                }
            }
            //set the application name
            String applicationName = systemPropertySettings.getPropValue(SystemPropertyConts.APPLICATION_NAME);
            model.addAttribute("applicationName", applicationName);
            //get requestedUrl
            String requestedUrl = (String) getFromSession(request, MConts.REQUESTED_URL);
            if (StringUtils.isBlank(requestedUrl)) {
                requestedUrl = "/home.htm";
            }
            //set the requested url
            model.addAttribute("requestedUrl", requestedUrl);
            return "user/login_success";
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("user.login.failed");
            makeErrorAware();
            return "user/user_login";
        }
    }

    private void validateLogin(LoginBean loginBean, HttpServletRequest request) {
        try {
            User user = loginBean.getUser();

            //uniqueId
            String uniqueId = user.getUniqueId();
            if (StringUtils.isBlank(uniqueId)) {
                addActionError("user.login.uniqueId.required");
            }
            //password
            String password = user.getPassword();
            if (StringUtils.isBlank(password)) {
                addActionError("user.login.password.required");
            }

            //security code
            String securityCode = loginBean.getSecurityCode();
            if (StringUtils.isBlank(securityCode)) {
                addActionError("security.code.required");
            } else {
                String captchaCode = (String) getFromSession(request, MConts.CAPTCHA_CODE_KEY);
                if (!StringUtils.equalsIgnoreCase(securityCode, captchaCode)) {
                    addActionError("security.code.invalid");
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("user.login.check.user.account.failed");
        }

    }

    @RequestMapping(value = "/request_pwd_reset", method = RequestMethod.GET)
    public String requestResetPassword(Model model) {
        ResetPasswordBean passwordReset = new ResetPasswordBean();
        model.addAttribute("passwordReset", passwordReset);
        return "user/request_pwd_reset";
    }

    @RequestMapping(value = "/request_pwd_reset", method = RequestMethod.POST)
    public String requestResetPassword(@ModelAttribute("passwordReset") ResetPasswordBean passwordBean, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        validateRequestResetPwd(passwordBean, request);
        if (hasActionErrors()) {
            makeErrorAware();
            return "user/request_pwd_reset";
        }

        try {
            User submittedUser = passwordBean.getUser();
            User foundUser = this.userService.getUserByEmail(submittedUser.getEmail());
            if (foundUser == null) {
                addActionError("user.forgot.password.account.invalid");
                makeErrorAware();
                return "user/request_pwd_reset";
            }

            // user account is inactive
            if (!foundUser.isActivated()) {
                addActionError("user.forgot.password.account.inactive");
                makeErrorAware();
                return "user/request_pwd_reset";
            }
            if (foundUser.getPassword().equals("ldap")) {
                addActionError("user.forgot.password.cannot.reset.ldap.account");
                makeErrorAware();
                return "user/request_pwd_reset";
            }
            String displayName = foundUser.getDisplayName();
            String submittedName = submittedUser.getFirstName() + " " + submittedUser.getLastName();
            if (!StringUtils.equals(displayName, submittedName)) {
                addActionError("user.forgot.password.account.invalid");
                makeErrorAware();
                return "user/request_pwd_reset";
            }
            String resetPasswdCode = generateSecurityHash(foundUser.getEmail());
            foundUser.setResetPasswdHashCode(resetPasswdCode);
            this.userService.updateUser(foundUser);

            // site name
            String serverQName = getServerQName(request);
            String appContext = getAppContextPath(request);

            // construct a reset password url
            String resetPwdUrl = constructResetPwdUrl(serverQName, appContext, foundUser.getId(), foundUser.getResetPasswdHashCode());

            sendResetPasswdEmailToUser(serverQName, foundUser.getEmail(), resetPwdUrl);

            // set action finished messsage
            addActionMessage(getText("user.forgot.password.request.finished.msg", new String[]{displayName}));
            makeMessageAware();
            return "user/request_pwd_finished";
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("user.forgot.password.request.failed");
            makeErrorAware();
            return "user/request_pwd_reset";
        }
    }

    @RequestMapping(value = "/verify_resetpwd", method = RequestMethod.GET)
    public String resetPassword(@RequestParam("id") long id, @RequestParam("code") String code, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        ResetPasswordBean passwordReset = new ResetPasswordBean();
        model.addAttribute("passwordReset", passwordReset);
        try {
            User user = this.userService.getUserById(id);
            if (user == null) {
                addActionError("user.reset.password.confirmation.link.invalid");
                makeErrorAware();
                return "user/verify_reset_pwd_error";
            }

            if (user.getResetPasswdHashCode() == null) {
                addActionError("user.reset.password.confirmation.link.expired");
                makeErrorAware();
                return "user/verify_reset_pwd_error";
            }

            if (user.getResetPasswdHashCode() != null && (!user.getResetPasswdHashCode().equals(code))) {
                addActionError("user.reset.password.confirmation.link.invalid");
                makeErrorAware();
                return "user/verify_reset_pwd_error";
            }

            if (!user.isActivated()) {
                addActionError("user.reset.password.confirmation.link.invalid");
                makeErrorAware();
                return "user/verify_reset_pwd_error";
            }
            //finally set the user into password reset bean
            passwordReset.setUser(user);
            return "user/reset_password";
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("user.reset.password.validate.confirmation.failed");
            return "user/verify_reset_pwd_error";
        }
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public String resetPassword(@ModelAttribute("passwordReset") ResetPasswordBean passwordBean, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        validateResetPassword(passwordBean, request);
        if (hasActionErrors()) {
            makeErrorAware();
            return "user/reset_password";
        }

        try {
            User user = passwordBean.getUser();
            User foundUser = this.userService.getUserById(user.getId());
            if (foundUser == null) {
                addActionError("user.reset.password.account.invalid");
                makeErrorAware();
                return "user/reset_password";
            }

            // reset password hash code is null, means the reset password link has been expired
            if (foundUser.getResetPasswdHashCode() == null) {
                // The reset password link has been expired
                addActionError("user.reset.password.confirmation.link.expired");
                makeErrorAware();
                return "user/reset_password";
            }

            if (user.getResetPasswdHashCode() != null && (!user.getResetPasswdHashCode().equals(user.getResetPasswdHashCode()))) {
                addActionError("user.reset.password.confirmation.link.invalid");
                makeErrorAware();
                return "user/reset_password";
            }

            foundUser.setPassword(MD5.hash(user.getPassword()));
            foundUser.setResetPasswdHashCode(null);
            this.userService.updateUser(foundUser);

            addActionMessage("user.reset.password.success.msg", new String[]{foundUser.getDisplayName()});
            makeMessageAware();
            return "user/reset_password_finished";

        } catch (Exception ex) {
            addActionError("user.reset.password.failed");
            makeErrorAware();
            return "user/reset_password";
        }
    }

    @RequestMapping(value = "user_logout")
    public String logout(HttpServletRequest request) {
        cleanAllInSession(request);
        return "home";
    }

    private void cleanAuthenInSession(HttpServletRequest request) {
        removeFromSession(request, MConts.SE_AUTHEN_FLAG);
        removeFromSession(request, MConts.SE_AUTHEN_USER_ID);
        removeFromSession(request, MConts.SE_AUTHEN_USER_NAME);
        removeFromSession(request, MConts.SE_USER_TYPE);
    }

    private void cleanAllInSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    private void validateLdapReg(RegistrationBean registration, HttpServletRequest request) {
        try {
            User user = registration.getUser();

            //uniqueId
            String uniqueId = user.getUniqueId();
            if (StringUtils.isBlank(uniqueId)) {
                addActionError("user.authcate.id.required");
            }
            //password
            String password = user.getPassword();
            if (StringUtils.isBlank(password)) {
                addActionError("user.authcate.password.required");
            }

            //security code
            String securityCode = registration.getSecurityCode();
            if (StringUtils.isBlank(securityCode)) {
                addActionError("security.code.required");
            } else {
                String captchaCode = (String) getFromSession(request, MConts.CAPTCHA_CODE_KEY);
                if (!StringUtils.equalsIgnoreCase(securityCode, captchaCode)) {
                    addActionError("security.code.invalid");
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("user.check.user.ldap.account.failed");
        }
    }

    private void validateReg(RegistrationBean registration, HttpServletRequest request) {
        try {
            User user = registration.getUser();
            //first name
            String firstName = user.getFirstName();
            if (StringUtils.isBlank(firstName)) {
                addActionError("user.first.name.empty");
            } else {
                if (!MDValidator.validateName(firstName, 10)) {
                    addActionError("user.first.name.invalid");
                }
            }

            //last name
            String lastName = user.getLastName();
            if (StringUtils.isBlank(lastName)) {
                addActionError("user.last.name.empty");
            } else {
                if (!MDValidator.validateName(lastName, 10)) {
                    addActionError("user.last.name.invalid");
                }
            }

            //email
            String email = user.getEmail();
            if (StringUtils.isBlank(email)) {
                addActionError("user.email.empty");
            } else {
                if (!MDValidator.validateEmail(email)) {
                    addActionError("user.email.invalid");
                }
            }

            //password
            String password = user.getPassword();
            if (StringUtils.isBlank(password)) {
                addActionError("user.password.required");
            } else {
                if (!MDValidator.validatePassword(password, 6, 10)) {
                    addActionError("user.password.invalid");
                }
            }

            //organization
            Profile p = registration.getProfile();
            String organization = p.getOrganization();
            if (StringUtils.isBlank(organization)) {
                addActionError("user.organization.required");
            }

            //security code
            String securityCode = registration.getSecurityCode();
            if (StringUtils.isBlank(securityCode)) {
                addActionError("security.code.required");
            } else {
                String captchaCode = (String) getFromSession(request, MConts.CAPTCHA_CODE_KEY);
                if (!StringUtils.equalsIgnoreCase(securityCode, captchaCode)) {
                    addActionError("security.code.invalid");
                }
            }

            //if has inputs errors, just return
            if (hasActionErrors()) {
                return;
            }

            boolean emailExisted = this.userService.checkExistedEmail(user.getEmail());
            if (emailExisted) {
                addActionError("user.email.already.registered");
            }
            boolean displayNameExisted = this.userService.checkExistedName(user.getDisplayName());
            if (displayNameExisted) {
                addActionError("user.display.name.already.registered");
            }
        } catch (Exception ex) {
            addActionError("user.check.registration.failed");
        }
    }

    private void validateRequestResetPwd(ResetPasswordBean resetPasswordBean, HttpServletRequest request) {
        User user = resetPasswordBean.getUser();
        //first name
        String firstName = user.getFirstName();
        if (StringUtils.isBlank(firstName)) {
            addActionError("user.first.name.empty");
        }

        //last name
        String lastName = user.getLastName();
        if (StringUtils.isBlank(lastName)) {
            addActionError("user.last.name.empty");
        }

        //email
        String email = user.getEmail();
        if (StringUtils.isBlank(email)) {
            addActionError("user.email.empty");
        } else {
            if (!MDValidator.validateEmail(email)) {
                addActionError("user.email.invalid");
            }
        }

        //security code
        String securityCode = resetPasswordBean.getSecurityCode();
        if (StringUtils.isBlank(securityCode)) {
            addActionError("security.code.required");
        } else {
            String captchaCode = (String) getFromSession(request, MConts.CAPTCHA_CODE_KEY);
            if (!StringUtils.equalsIgnoreCase(securityCode, captchaCode)) {
                addActionError("security.code.invalid");
            }
        }
    }

    private void validateResetPassword(ResetPasswordBean resetPasswordBean, HttpServletRequest request) {
        User user = resetPasswordBean.getUser();

        String rePassword = resetPasswordBean.getRePassword();

        //password
        if (StringUtils.isBlank(user.getPassword())) {
            addActionError("user.reset.password.required");
        }

        //repassword
        if (StringUtils.isBlank(rePassword)) {
            addActionError("user.reset.password.rePassword.required");
        }
        //compare two passwords
        if (StringUtils.isNotBlank(user.getPassword()) && StringUtils.isNotBlank(rePassword)) {
            if (!user.getPassword().equals(rePassword)) {
                addActionError("user.reset.password.two.passwords.not.same");
            }
        }
        //security code
        String securityCode = resetPasswordBean.getSecurityCode();
        if (StringUtils.isBlank(securityCode)) {
            addActionError("security.code.required");
        } else {
            String captchaCode = (String) getFromSession(request, MConts.CAPTCHA_CODE_KEY);
            if (!StringUtils.equalsIgnoreCase(securityCode, captchaCode)) {
                addActionError("security.code.invalid");
            }
        }
    }

    private Avatar genAvatar(String gender) {
        Avatar avatar = new Avatar();
        String avatarFile = null;
        if (StringUtils.isBlank(gender)) {
            avatarFile = "avatar" + File.separator + "male.png";
        } else {
            if (StringUtils.equalsIgnoreCase(gender, "male")) {
                avatarFile = "avatar" + File.separator + "male.png";
            } else if (StringUtils.equalsIgnoreCase(gender, "female")) {
                avatarFile = "avatar" + File.separator + "female.png";
            } else {
                avatarFile = "avatar" + File.separator + "male.png";
            }
        }
        avatar.setFileName(avatarFile);
        avatar.setFileType("png");
        return avatar;
    }

    private String constructActivationURL(String serverQName, String appContext, long userId, String activationCode) {
        String pkName = "admin";
        String actionName = "verify.htm?";
        String regUidPair = "id=" + userId;

        String hashCodePair = "&code=" + activationCode;

        StringBuffer activationURL = new StringBuffer();
        // application root url
        activationURL.append(serverQName).append(appContext).append(MConts.URL_PATH_DEIM);
        // action name
        activationURL.append(pkName).append(MConts.URL_PATH_DEIM).append(actionName);
        // actId, idcode, act name and hash code
        activationURL.append(regUidPair).append(hashCodePair);

        return new String(activationURL).trim();
    }

    private void sendRegMailToAdmin(String serverQName, String userName, String userEmail, String organization, String activationURL) {

        String activateEmailTemplateFile = "activateAccountEmailTemplate.ftl";
        String appName = systemPropertySettings.getPropValue(SystemPropertyConts.APPLICATION_NAME);
        // prepare to send email.
        String adminEmail = systemPropertySettings.getPropValue(SystemPropertyConts.SYSTEM_SERVICE_EMAIL);

        String subject = getText("user.account.activation.request.title");

        Map<String, String> templateMap = new HashMap<String, String>();
        templateMap.put("RegisteredUser", userName);
        templateMap.put("UserEmail", userEmail);
        templateMap.put("Organization", organization);
        templateMap.put("ActivationURL", activationURL);
        templateMap.put("SiteName", serverQName);
        templateMap.put("AppName", appName);

        this.mailService.sendMail(adminEmail, adminEmail, subject, templateMap, activateEmailTemplateFile, true);
    }

    private String constructResetPwdUrl(String serverQName, String appContext, long id, String hashCode) {

        String pkName = "user";
        String actionName = "verify_resetpwd.htm?";
        String actIdPair = "id=" + id;
        String hashCodePair = "&code=" + hashCode;

        StringBuffer resetPwdUrl = new StringBuffer();
        // application root url
        resetPwdUrl.append(serverQName).append(appContext).append(MConts.URL_PATH_DEIM);
        // action name
        resetPwdUrl.append(pkName).append(MConts.URL_PATH_DEIM).append(actionName);
        // actId and hash code
        resetPwdUrl.append(actIdPair).append(hashCodePair);
        return new String(resetPwdUrl).trim();
    }

    private void sendResetPasswdEmailToUser(String serverQName, String userEmail, String resetPasswdURL) {
        String resetPasswdMailTemplateFile = "resetPasswordEmailTemplate.ftl";

        // prepare to send email.
        String appName = systemPropertySettings.getPropValue(SystemPropertyConts.APPLICATION_NAME);
        String adminEmail = systemPropertySettings.getPropValue(SystemPropertyConts.SYSTEM_SERVICE_EMAIL);
        String subject = getText("user.forgot.password.mail.title");

        Map<String, String> templateMap = new HashMap<String, String>();
        templateMap.put("userEmail", userEmail);
        templateMap.put("resetPasswdURL", resetPasswdURL);
        templateMap.put("SiteName", serverQName);
        templateMap.put("AppName", appName);

        // send an email to user
        this.mailService.sendMail(adminEmail, userEmail, subject, templateMap, resetPasswdMailTemplateFile, true);
    }

}
