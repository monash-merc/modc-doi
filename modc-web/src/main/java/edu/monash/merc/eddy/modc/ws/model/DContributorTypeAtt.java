
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DContributorTypeAtt.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DContributorTypeAtt">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ContactPerson"/>
 *     &lt;enumeration value="DataCollector"/>
 *     &lt;enumeration value="DataManager"/>
 *     &lt;enumeration value="Distributor"/>
 *     &lt;enumeration value="Editor"/>
 *     &lt;enumeration value="Funder"/>
 *     &lt;enumeration value="HostingInstitution"/>
 *     &lt;enumeration value="Producer"/>
 *     &lt;enumeration value="ProjectLeader"/>
 *     &lt;enumeration value="ProjectMember"/>
 *     &lt;enumeration value="RegistrationAgency"/>
 *     &lt;enumeration value="RegistrationAuthority"/>
 *     &lt;enumeration value="RelatedPerson"/>
 *     &lt;enumeration value="RightsHolder"/>
 *     &lt;enumeration value="Researcher"/>
 *     &lt;enumeration value="Sponsor"/>
 *     &lt;enumeration value="Supervisor"/>
 *     &lt;enumeration value="WorkPackageLeader"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DContributorTypeAtt")
@XmlEnum
public enum DContributorTypeAtt {

    @XmlEnumValue("ContactPerson")
    CONTACT_PERSON("ContactPerson"),
    @XmlEnumValue("DataCollector")
    DATA_COLLECTOR("DataCollector"),
    @XmlEnumValue("DataManager")
    DATA_MANAGER("DataManager"),
    @XmlEnumValue("Distributor")
    DISTRIBUTOR("Distributor"),
    @XmlEnumValue("Editor")
    EDITOR("Editor"),
    @XmlEnumValue("Funder")
    FUNDER("Funder"),
    @XmlEnumValue("HostingInstitution")
    HOSTING_INSTITUTION("HostingInstitution"),
    @XmlEnumValue("Producer")
    PRODUCER("Producer"),
    @XmlEnumValue("ProjectLeader")
    PROJECT_LEADER("ProjectLeader"),
    @XmlEnumValue("ProjectMember")
    PROJECT_MEMBER("ProjectMember"),
    @XmlEnumValue("RegistrationAgency")
    REGISTRATION_AGENCY("RegistrationAgency"),
    @XmlEnumValue("RegistrationAuthority")
    REGISTRATION_AUTHORITY("RegistrationAuthority"),
    @XmlEnumValue("RelatedPerson")
    RELATED_PERSON("RelatedPerson"),
    @XmlEnumValue("RightsHolder")
    RIGHTS_HOLDER("RightsHolder"),
    @XmlEnumValue("Researcher")
    RESEARCHER("Researcher"),
    @XmlEnumValue("Sponsor")
    SPONSOR("Sponsor"),
    @XmlEnumValue("Supervisor")
    SUPERVISOR("Supervisor"),
    @XmlEnumValue("WorkPackageLeader")
    WORK_PACKAGE_LEADER("WorkPackageLeader");
    private final String value;

    DContributorTypeAtt(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DContributorTypeAtt fromValue(String v) {
        for (DContributorTypeAtt c: DContributorTypeAtt.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
