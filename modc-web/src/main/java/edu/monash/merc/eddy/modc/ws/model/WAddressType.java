
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WAddressType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WAddressType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="email"/>
 *     &lt;enumeration value="url"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WAddressType", namespace = "http://merc.monash.edu/ws/schema/mds")
@XmlEnum
public enum WAddressType {

    @XmlEnumValue("email")
    EMAIL("email"),
    @XmlEnumValue("url")
    URL("url"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    WAddressType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WAddressType fromValue(String v) {
        for (WAddressType c: WAddressType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
