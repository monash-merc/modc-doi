
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DResponseCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DResponseCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MT001"/>
 *     &lt;enumeration value="MT002"/>
 *     &lt;enumeration value="MT003"/>
 *     &lt;enumeration value="MT004"/>
 *     &lt;enumeration value="MT005"/>
 *     &lt;enumeration value="MT006"/>
 *     &lt;enumeration value="MT007"/>
 *     &lt;enumeration value="MT008"/>
 *     &lt;enumeration value="MT009"/>
 *     &lt;enumeration value="MT010"/>
 *     &lt;enumeration value="MT011"/>
 *     &lt;enumeration value="MT012"/>
 *     &lt;enumeration value="MT013"/>
 *     &lt;enumeration value="MT090"/>
 *     &lt;enumeration value="MT091"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DResponseCode")
@XmlEnum
public enum DResponseCode {

    @XmlEnumValue("MT001")
    MT_001("MT001"),
    @XmlEnumValue("MT002")
    MT_002("MT002"),
    @XmlEnumValue("MT003")
    MT_003("MT003"),
    @XmlEnumValue("MT004")
    MT_004("MT004"),
    @XmlEnumValue("MT005")
    MT_005("MT005"),
    @XmlEnumValue("MT006")
    MT_006("MT006"),
    @XmlEnumValue("MT007")
    MT_007("MT007"),
    @XmlEnumValue("MT008")
    MT_008("MT008"),
    @XmlEnumValue("MT009")
    MT_009("MT009"),
    @XmlEnumValue("MT010")
    MT_010("MT010"),
    @XmlEnumValue("MT011")
    MT_011("MT011"),
    @XmlEnumValue("MT012")
    MT_012("MT012"),
    @XmlEnumValue("MT013")
    MT_013("MT013"),
    @XmlEnumValue("MT090")
    MT_090("MT090"),
    @XmlEnumValue("MT091")
    MT_091("MT091");
    private final String value;

    DResponseCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DResponseCode fromValue(String v) {
        for (DResponseCode c: DResponseCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
