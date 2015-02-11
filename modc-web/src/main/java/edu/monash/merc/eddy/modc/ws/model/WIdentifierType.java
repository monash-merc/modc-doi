
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WIdentifierType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WIdentifierType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="abn"/>
 *     &lt;enumeration value="arc"/>
 *     &lt;enumeration value="ark"/>
 *     &lt;enumeration value="doi"/>
 *     &lt;enumeration value="handle"/>
 *     &lt;enumeration value="infouri"/>
 *     &lt;enumeration value="isil"/>
 *     &lt;enumeration value="local"/>
 *     &lt;enumeration value="purl"/>
 *     &lt;enumeration value="uri"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WIdentifierType", namespace = "http://merc.monash.edu/ws/schema/mds")
@XmlEnum
public enum WIdentifierType {

    @XmlEnumValue("abn")
    ABN("abn"),
    @XmlEnumValue("arc")
    ARC("arc"),
    @XmlEnumValue("ark")
    ARK("ark"),
    @XmlEnumValue("doi")
    DOI("doi"),
    @XmlEnumValue("handle")
    HANDLE("handle"),
    @XmlEnumValue("infouri")
    INFOURI("infouri"),
    @XmlEnumValue("isil")
    ISIL("isil"),
    @XmlEnumValue("local")
    LOCAL("local"),
    @XmlEnumValue("purl")
    PURL("purl"),
    @XmlEnumValue("uri")
    URI("uri");
    private final String value;

    WIdentifierType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WIdentifierType fromValue(String v) {
        for (WIdentifierType c: WIdentifierType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
