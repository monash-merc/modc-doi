package edu.monash.merc.eddy.modc.web.controller;

import edu.monash.merc.eddy.modc.common.config.SystemPropertyConts;
import edu.monash.merc.eddy.modc.common.config.SystemPropertySettings;
import edu.monash.merc.eddy.modc.common.util.MD5;
import edu.monash.merc.eddy.modc.web.conts.MConts;
import edu.monash.merc.eddy.modc.web.validation.ActionSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by simonyu on 21/08/2014.
 */
public class BaseController {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    protected SystemPropertySettings systemPropertySettings;

    private ActionSupport actionSupport;

    public void setSystemPropertySettings(SystemPropertySettings systemPropertySettings) {
        this.systemPropertySettings = systemPropertySettings;
    }

    @PostConstruct
    public void init() {
        String applicationName = this.systemPropertySettings.getPropValue(SystemPropertyConts.APPLICATION_NAME);
    }

    public void actionSupport(HttpServletRequest request, Model model) {
        actionSupport = ActionSupport.actionSupport(request, model);
    }

    public void addActionError(String code) {
        actionSupport.addActionError(code);
    }

    public void addActionError(String code, String[] errorMessages) {
        actionSupport.addActionError(code, errorMessages);
    }

    public void addActionError(String code, String[] errorMessages, String defaultErrorMessage) {
        actionSupport.addActionError(code, errorMessages, defaultErrorMessage);
    }

    public void addActionMessage(String code) {
        actionSupport.addActionMessage(code);
    }

    public void addActionMessage(String code, String[] actionMessages) {
        actionSupport.addActionMessage(code, actionMessages);
    }

    public void addActionMessage(String code, String[] actionMessages, String defaultActionMessage) {
        actionSupport.addActionMessage(code, actionMessages, defaultActionMessage);
    }

    public boolean hasActionMessages() {
        return actionSupport.hasActionMessages();
    }

    public void makeMessageAware() {
        actionSupport.makeMessageAware();
    }

    public boolean hasActionErrors() {
        return actionSupport.hasActionErrors();
    }

    public void makeErrorAware() {
        actionSupport.makeErrorAware();
    }

    public String getText(String code) {
        return actionSupport.getText(code);
    }

    public String getText(String code, String[] actionMessages) {
        return actionSupport.getText(code, actionMessages);
    }

    public String getText(String code, String[] actionMessages, String defaultActionMessage) {
        return actionSupport.getText(code, actionMessages, defaultActionMessage);
    }

    protected String getAppContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }


    protected String generateSecurityHash(String value) {
        String systemHash = MD5.hash(systemPropertySettings.getPropValue(SystemPropertyConts.USER_HASH_SEQUENCE) + value);
        return MD5.hash(System.currentTimeMillis() + systemHash);
    }

    protected String getServerQName(HttpServletRequest request) {

        String scheme = request.getScheme();
        String hostName = request.getServerName();
        int port = request.getServerPort();

        StringBuffer buf = new StringBuffer();
        if (scheme.equals(MConts.HTTP_SCHEME)) {
            buf.append(MConts.HTTP_SCHEME).append(MConts.HTTP_SCHEME_DELIM);
        } else {
            buf.append(MConts.HTTPS_SCHEME).append(MConts.HTTP_SCHEME_DELIM);
        }
        buf.append(hostName);
        if (port == 80 || port == 443) {
            return new String(buf);
        }
        buf.append(MConts.COLON_DEIM).append(port);
        return new String(buf);
    }

    protected void storeInSession(HttpServletRequest request, String sessionKey, Object object) {
        HttpSession session = request.getSession();
        session.setAttribute(sessionKey, object);
    }

    protected Object getFromSession(HttpServletRequest request, String sessionKey) {
        HttpSession session = request.getSession();
        return session.getAttribute(sessionKey);
    }

    protected void removeFromSession(HttpServletRequest request, String sessionKey) {
        HttpSession session = request.getSession();
        session.removeAttribute(sessionKey);
    }

}
