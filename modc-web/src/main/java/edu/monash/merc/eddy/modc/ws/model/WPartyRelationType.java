
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WPartyRelationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WPartyRelationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="hasAssociationWith"/>
 *     &lt;enumeration value="hasMember"/>
 *     &lt;enumeration value="hasPart"/>
 *     &lt;enumeration value="isCollectorOf"/>
 *     &lt;enumeration value="isFundedBy"/>
 *     &lt;enumeration value="isFunderOf"/>
 *     &lt;enumeration value="isManagedBy"/>
 *     &lt;enumeration value="isManagerOf"/>
 *     &lt;enumeration value="isMemberOf"/>
 *     &lt;enumeration value="isOwnedBy"/>
 *     &lt;enumeration value="isOwnerOf"/>
 *     &lt;enumeration value="isPartOf"/>
 *     &lt;enumeration value="isParticipantIn"/>
 *     &lt;enumeration value="enriches"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WPartyRelationType", namespace = "http://merc.monash.edu/ws/schema/mds")
@XmlEnum
public enum WPartyRelationType {

    @XmlEnumValue("hasAssociationWith")
    HAS_ASSOCIATION_WITH("hasAssociationWith"),
    @XmlEnumValue("hasMember")
    HAS_MEMBER("hasMember"),
    @XmlEnumValue("hasPart")
    HAS_PART("hasPart"),
    @XmlEnumValue("isCollectorOf")
    IS_COLLECTOR_OF("isCollectorOf"),
    @XmlEnumValue("isFundedBy")
    IS_FUNDED_BY("isFundedBy"),
    @XmlEnumValue("isFunderOf")
    IS_FUNDER_OF("isFunderOf"),
    @XmlEnumValue("isManagedBy")
    IS_MANAGED_BY("isManagedBy"),
    @XmlEnumValue("isManagerOf")
    IS_MANAGER_OF("isManagerOf"),
    @XmlEnumValue("isMemberOf")
    IS_MEMBER_OF("isMemberOf"),
    @XmlEnumValue("isOwnedBy")
    IS_OWNED_BY("isOwnedBy"),
    @XmlEnumValue("isOwnerOf")
    IS_OWNER_OF("isOwnerOf"),
    @XmlEnumValue("isPartOf")
    IS_PART_OF("isPartOf"),
    @XmlEnumValue("isParticipantIn")
    IS_PARTICIPANT_IN("isParticipantIn"),
    @XmlEnumValue("enriches")
    ENRICHES("enriches");
    private final String value;

    WPartyRelationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WPartyRelationType fromValue(String v) {
        for (WPartyRelationType c: WPartyRelationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
