
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DTitleTypeAtt.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DTitleTypeAtt">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AlternativeTitle"/>
 *     &lt;enumeration value="Subtitle"/>
 *     &lt;enumeration value="TranslatedTitle"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DTitleTypeAtt")
@XmlEnum
public enum DTitleTypeAtt {

    @XmlEnumValue("AlternativeTitle")
    ALTERNATIVE_TITLE("AlternativeTitle"),
    @XmlEnumValue("Subtitle")
    SUBTITLE("Subtitle"),
    @XmlEnumValue("TranslatedTitle")
    TRANSLATED_TITLE("TranslatedTitle");
    private final String value;

    DTitleTypeAtt(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DTitleTypeAtt fromValue(String v) {
        for (DTitleTypeAtt c: DTitleTypeAtt.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
