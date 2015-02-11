
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WCitationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WCitationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Harvard"/>
 *     &lt;enumeration value="APA"/>
 *     &lt;enumeration value="MLA"/>
 *     &lt;enumeration value="Vancouver"/>
 *     &lt;enumeration value="IEEE"/>
 *     &lt;enumeration value="CSE"/>
 *     &lt;enumeration value="Chicago"/>
 *     &lt;enumeration value="AMA"/>
 *     &lt;enumeration value="AGPS-AGIMO"/>
 *     &lt;enumeration value="AGLC"/>
 *     &lt;enumeration value="ACS"/>
 *     &lt;enumeration value="Datacite"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WCitationType", namespace = "http://merc.monash.edu/ws/schema/mds")
@XmlEnum
public enum WCitationType {

    @XmlEnumValue("Harvard")
    HARVARD("Harvard"),
    APA("APA"),
    MLA("MLA"),
    @XmlEnumValue("Vancouver")
    VANCOUVER("Vancouver"),
    IEEE("IEEE"),
    CSE("CSE"),
    @XmlEnumValue("Chicago")
    CHICAGO("Chicago"),
    AMA("AMA"),
    @XmlEnumValue("AGPS-AGIMO")
    AGPS_AGIMO("AGPS-AGIMO"),
    AGLC("AGLC"),
    ACS("ACS"),
    @XmlEnumValue("Datacite")
    DATACITE("Datacite");
    private final String value;

    WCitationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WCitationType fromValue(String v) {
        for (WCitationType c: WCitationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
