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

package edu.monash.merc.eddy.modc.service.ldap;

import edu.monash.merc.eddy.modc.common.config.SystemPropertyConts;
import edu.monash.merc.eddy.modc.common.config.SystemPropertySettings;
import edu.monash.merc.eddy.modc.common.ldap.LDAPUtil;
import edu.monash.merc.eddy.modc.common.ldap.LdapProperty;
import edu.monash.merc.eddy.modc.common.ldap.LdapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 13/10/2014
 */
@Scope("prototype")
@Service
public class LdapServiceImpl implements LdapService {

    @Autowired
    private LDAPUtil ldapUtil;

    @Autowired
    protected SystemPropertySettings systemPropertySettings;

    @PostConstruct
    public void initLdapEnv() {
        LdapProperty ldapProp = new LdapProperty();
        ldapProp.setLdapFactory(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_FACTORY));
        ldapProp.setLdapServer(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_SERVER_URL));
        ldapProp.setProtocol(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_SECURITY_PROTOCOL));
        ldapProp.setAuthentication(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_AUTHENTICATION));
        ldapProp.setBaseDN(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_BASE_DN));
        ldapProp.setBindDN(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_BIND_DN));
        ldapProp.setPassword(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_BIND_PASSWORD));
        ldapProp.setAttUID(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_UID_ATTR_NAME));
        ldapProp.setAttMail(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_MAIL_ATTR_NAME));
        ldapProp.setAttGender(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_GENDER_ATTR_NAME));
        ldapProp.setAttCN(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_CN_ATTR_NAME));
        ldapProp.setAttSn(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_SN_ATTR_NAME));
        ldapProp.setAttGivenname(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_GIVENNAME_ATTR_NAME));
        ldapProp.setAttDisplayName(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_DISPLAYNAME_ATTR_NAME));
        ldapProp.setAttPersonalTitle(systemPropertySettings.getPropValue(SystemPropertyConts.LDAP_PERSONAL_TITLE_ATTR_NAME));
        this.ldapUtil.initEnvironment(ldapProp);
    }

    public void setLdapUtil(LDAPUtil ldapUtil) {
        this.ldapUtil = ldapUtil;
    }

    @Override
    public LdapUser lookup(String cnOrEmail) {
        return this.ldapUtil.findUserInfo(cnOrEmail);
    }

    @Override
    public LdapUser verifyLdapUser(String authcatId, String password) {
        return this.ldapUtil.validateLdapUser(authcatId, password);
    }

    @Override
    public boolean login(String authcatId, String password) {
        return this.ldapUtil.login(authcatId, password);
    }
}
