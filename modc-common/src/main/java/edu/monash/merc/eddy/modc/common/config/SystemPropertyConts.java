package edu.monash.merc.eddy.modc.common.config;

/**
 * Created by simonyu on 12/08/2014.
 */
public interface SystemPropertyConts {
    static String APPLICATION_NAME = "application.name";

    public static String SYSTEM_ADMIN_EMAIL = "admin.user.email";

    public static String SYSTEM_ADMIN_NAME = "admin.user.displayName";

    public static String SYSTEM_ADMIN_PWD = "admin.user.password";

    public static String SYSTEM_SERVICE_EMAIL = "system.service.email";

    public static String USER_HASH_SEQUENCE = "user.security.hash.sequence";

    // LDAP Configuration
    static String LDAP_AUTH_SUPPORTED = "ldap.authentication.supported";

    static String LDAP_FACTORY = "ldap.factory";

    static String LDAP_SERVER_URL = "ldap.server.url";

    static String LDAP_BASE_DN = "ldap.base.dn";

    static String LDAP_BIND_BASE_DN_REQUIRED = "ldap.bind.base.dn.required";

    static String LDAP_SECURITY_PROTOCOL = "ldap.security.protocol";

    static String LDAP_AUTHENTICATION = "ldap.authentication";

    static String LDAP_UID_ATTR_NAME = "ldap.uid.attrName";

    static String LDAP_MAIL_ATTR_NAME = "ldap.mail.attrName";

    static String LDAP_CN_ATTR_NAME = "ldap.cn.attrName";

    static String LDAP_GENDER_ATTR_NAME = "ldap.gender.attrName";

    static String LDAP_SN_ATTR_NAME = "ldap.sn.attrName";

    static String LDAP_GIVENNAME_ATTR_NAME = "ldap.givenname.attrName";

    static String LDAP_PERSONAL_TITLE_ATTR_NAME = "ldap.personaltitle.attrName";
}
