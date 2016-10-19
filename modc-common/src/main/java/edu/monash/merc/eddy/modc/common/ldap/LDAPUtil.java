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

package edu.monash.merc.eddy.modc.common.ldap;

import edu.monash.merc.eddy.modc.common.exception.MSecurityException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 13/10/2014
 */
@Scope("prototype")
@Component
public class LDAPUtil {

    private String ldapFactory;

    private String serverUrl;

    private String protocol;

    private String authentication;

    private String baseDn;

    private String bindDn;

    private String password;

    private String uidAttrName;

    private String mailAttrName;

    private String cnAttrName;

    private String genderAttrName;

    private String personalTitleAttrName;

    private String givennameAttrName;

    private String snAttrName;

    private String displayNameAttName;

    private Hashtable<String, Object> environment;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private void prepareLdapEnvironment() {
        environment = new Hashtable<String, Object>();

        if (ldapFactory != null && !"".equals(ldapFactory.trim())) {
            environment.put(Context.INITIAL_CONTEXT_FACTORY, ldapFactory);
        } else {
            throw new MSecurityException("no ldap factory class specified");
        }

        if (serverUrl != null && !"".equals(serverUrl.trim())) {
            environment.put(Context.PROVIDER_URL, serverUrl);
        } else {
            throw new MSecurityException("no ldap server url specified");
        }

        if (bindDn != null && !"".equals(bindDn.trim())) {
            environment.put(Context.SECURITY_PRINCIPAL, bindDn);
        }

        if (password != null && !"".equals(password.trim()))
            environment.put(Context.SECURITY_CREDENTIALS, password);

        if (protocol != null && !"".equals(protocol.trim())) {
            environment.put(Context.SECURITY_PROTOCOL, protocol);
        }

        if (authentication != null && !"".equals(authentication.trim())) {
            environment.put(Context.SECURITY_AUTHENTICATION, authentication);
        }
    }


    public void initEnvironment(LdapProperty ldapProp) {
        this.ldapFactory = ldapProp.getLdapFactory();
        this.serverUrl = ldapProp.getLdapServer();
        this.protocol = ldapProp.getProtocol();
        this.authentication = ldapProp.getAuthentication();
        this.baseDn = ldapProp.getBaseDN();
        this.bindDn = ldapProp.getBindDN();
        this.password = ldapProp.getPassword();
        this.uidAttrName = ldapProp.getAttUID();
        this.mailAttrName = ldapProp.getAttMail();
        this.cnAttrName = ldapProp.getAttCN();
        this.genderAttrName = ldapProp.getAttGender();
        this.personalTitleAttrName = ldapProp.getAttPersonalTitle();
        this.snAttrName = ldapProp.getAttSn();
        this.givennameAttrName = ldapProp.getAttGivenname();
        this.displayNameAttName = ldapProp.getAttDisplayName();
    }


    public boolean login(String uid, String password) {
        prepareLdapEnvironment();
        StringBuffer principal = new StringBuffer(256);
        principal.append("uid=").append(uid).append(",").append("ou=users").append(",").append(baseDn);
        environment.put(Context.SECURITY_PRINCIPAL, principal.toString());
        environment.put(Context.SECURITY_CREDENTIALS, password);
        DirContext dir = null;
        try {
            dir = new InitialDirContext(environment);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        } finally {
            if (dir != null) {
                try {
                    dir.close();
                } catch (NamingException e) {
                    // ignore whatever error
                }
            }
        }
    }

    public String findUserDn(String uid) {
        // prepare the ldap connection environment variables.
        prepareLdapEnvironment();
        // connect to ldap to find user dn, the user dn should not contain the base dn part.
        String findDn = null;
        DirContext dir = null;
        try {
            dir = new InitialDirContext(environment);
            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String filter = uidAttrName + "=" + uid;
            NamingEnumeration<SearchResult> answer = dir.search(baseDn, filter, ctls);
            while (answer.hasMore()) {
                SearchResult st = (SearchResult) answer.next();
                findDn = st.getName();
                if (logger.isDebugEnabled()) {
                    logger.debug("found user dn, " + findDn);
                }
            }
        } catch (NamingException nex) {
            logger.error(nex.getMessage());
            throw new MSecurityException(nex);
        } finally {
            if (dir != null) {
                try {
                    dir.close();
                } catch (NamingException e) {
                    // close jndi context
                }
            }
        }
        return findDn;
    }

    private LdapUser searchLdapUser(String seachfor, boolean searchByAuthcateId) {
        // prepare the ldap connection environment variables.
        prepareLdapEnvironment();
        DirContext dir = null;
        try {
            dir = new InitialDirContext(environment);
            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String filter = cnAttrName + "=" + seachfor;
            if (searchByAuthcateId) {
                filter = "uid=" + seachfor;
            } else if (StringUtils.contains(seachfor, "@")) {
                filter = mailAttrName + "=" + seachfor;
            }
            NamingEnumeration<SearchResult> answer = dir.search(baseDn, filter, ctls);
            while (answer.hasMore()) {
                SearchResult st = (SearchResult) answer.next();
                LdapUser usr = new LdapUser();

                Attributes atts = st.getAttributes();

                Attribute mailAtt = atts.get(mailAttrName);
                if (mailAtt != null) {
                    usr.setMail((String) mailAtt.get());
                }

                Attribute cnAtt = atts.get(displayNameAttName);
                if (cnAtt != null) {
                    usr.setDisplayName((String) cnAtt.get());
                }

                Attribute uidAtt = atts.get(uidAttrName);
                if (uidAtt != null) {
                    usr.setUid((String) uidAtt.get());
                }

                Attribute ptitleAtt = atts.get(personalTitleAttrName);
                if (ptitleAtt != null) {
                    usr.setTitle((String) ptitleAtt.get());
                }

                Attribute genderAtt = atts.get(genderAttrName);
                if (genderAtt != null) {
                    usr.setGender((String) genderAtt.get());
                }

                Attribute surNameAtt = atts.get(snAttrName);
                if (surNameAtt != null) {
                    usr.setLastName((String) surNameAtt.get());
                }

                Attribute firstNameAtt = atts.get(givennameAttrName);
                if (firstNameAtt != null) {
                    usr.setFirstName((String) firstNameAtt.get());
                }
                return usr;
            }
        } catch (NamingException nex) {
            logger.error(nex.getMessage());
            throw new MSecurityException(nex);
        } finally {
            if (dir != null) {
                try {
                    dir.close();
                } catch (NamingException e) {
                    // close jndi context
                }
            }
        }
        return null;
    }

    public LdapUser findUserInfo(String mailorcn) {
        return searchLdapUser(mailorcn, false);
    }

    public LdapUser validateLdapUser(String uid, String password) {
        LdapUser usr = searchLdapUser(uid, true);
        if (usr != null) {
            boolean loginSucceed = login(uid, password);
            if (loginSucceed) {
                return usr;
            } else {
                return null;
            }
        }
        return null;
    }

    public String getLdapFactory() {
        return ldapFactory;
    }

    public void setLdapFactory(String ldapFactory) {
        this.ldapFactory = ldapFactory;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getBaseDn() {
        return baseDn;
    }

    public void setBaseDn(String baseDn) {
        this.baseDn = baseDn;
    }

    public String getBindDn() {
        return bindDn;
    }

    public void setBindDn(String bindDn) {
        this.bindDn = bindDn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUidAttrName() {
        return uidAttrName;
    }

    public void setUidAttrName(String uidAttrName) {
        this.uidAttrName = uidAttrName;
    }

    public String getMailAttrName() {
        return mailAttrName;
    }

    public void setMailAttrName(String mailAttrName) {
        this.mailAttrName = mailAttrName;
    }

    public String getCnAttrName() {
        return cnAttrName;
    }

    public void setCnAttrName(String cnAttrName) {
        this.cnAttrName = cnAttrName;
    }

    public String getGenderAttrName() {
        return genderAttrName;
    }

    public void setGenderAttrName(String genderAttrName) {
        this.genderAttrName = genderAttrName;
    }

    public String getPersonalTitleAttrName() {
        return personalTitleAttrName;
    }

    public void setPersonalTitleAttrName(String personalTitleAttrName) {
        this.personalTitleAttrName = personalTitleAttrName;
    }

    public String getSnAttrName() {
        return snAttrName;
    }

    public void setSnAttrName(String snAttrName) {
        this.snAttrName = snAttrName;
    }

    public String getGivennameAttrName() {
        return givennameAttrName;
    }

    public void setGivennameAttrName(String givennameAttrName) {
        this.givennameAttrName = givennameAttrName;
    }

    public String getDisplayNameAttName() {
        return displayNameAttName;
    }

    public void setDisplayNameAttName(String displayNameAttName) {
        this.displayNameAttName = displayNameAttName;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Hashtable<String, Object> getEnvironment() {
        return environment;
    }

    public void setEnvironment(Hashtable<String, Object> environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {

        String factory = "com.sun.jndi.ldap.LdapCtxFactory";
        String baseDn = "dc=monash,dc=edu";
        String serverUrl = "ldaps://lds.monash.edu.au:636";
        String bindDn = "uid=service_account,ou=users,dc=monash,dc=edu";
        String password = "service_account_password";
        String protocol = "";
        String authentication = "";
        String uidAttrName = "uid";
        String mailAttrName = "mail";
        String cnAttrName = "cn";
        String genderAttrName = "gender";
        String personalTitleAttrName = "personalTitle";
        String displayNameAttName = "displayName";
        String givennameAttrName = "givenname";

        String snAttrName = "sn";
        LDAPUtil ldap = new LDAPUtil();
        LdapProperty ldapProperty = new LdapProperty();

        ldapProperty.setLdapFactory(factory);
        ldapProperty.setLdapServer(serverUrl);
        ldapProperty.setBaseDN(baseDn);
        ldapProperty.setBindDN(bindDn);
        ldapProperty.setPassword(password);
        ldapProperty.setProtocol(protocol);
        ldapProperty.setAuthentication(authentication);
        ldapProperty.setAttUID(uidAttrName);
        ldapProperty.setAttMail(mailAttrName);
        ldapProperty.setAttCN(cnAttrName);
        ldapProperty.setAttGender(genderAttrName);
        ldapProperty.setAttPersonalTitle(personalTitleAttrName);
        ldapProperty.setAttGivenname(givennameAttrName);
        ldapProperty.setAttSn(snAttrName);
        ldapProperty.setAttDisplayName(displayNameAttName);
        ldap.initEnvironment(ldapProperty);

        String usrdn = ldap.findUserDn("xiyu");
        System.out.println("user dn: " + usrdn);

        LdapUser luser = ldap.findUserInfo("Anthony Beitz");
        if (luser != null) {
            System.out.println("title: " + luser.getTitle());
            System.out.println("display name: " + luser.getDisplayName());
            System.out.println("authencate id: " + luser.getUid());
            System.out.println("last name: " + luser.getLastName());
            System.out.println("frist name: " + luser.getFirstName());
            System.out.println("gender : " + luser.getGender());
            System.out.println("mail: " + luser.getMail());
        } else {
            System.out.println("ldap user info is not found");
        }

        LdapUser usr = ldap.validateLdapUser("xiyu", "xxxx");
        if (usr == null) {
            System.out.println("user is not found");
        } else {
            System.out.println("display name: " + usr.getDisplayName());
            System.out.println("frist name: " + usr.getFirstName());
            System.out.println("last name: " + usr.getLastName());
            System.out.println("gender : " + usr.getGender());
            System.out.println("mail: " + usr.getMail());
        }
    }
}
