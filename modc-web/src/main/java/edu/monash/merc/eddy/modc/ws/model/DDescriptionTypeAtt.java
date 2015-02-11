
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DDescriptionTypeAtt.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DDescriptionTypeAtt">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abstract"/>
 *     &lt;enumeration value="SeriesInformation"/>
 *     &lt;enumeration value="TableOfContents"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DDescriptionTypeAtt")
@XmlEnum
public enum DDescriptionTypeAtt {

    @XmlEnumValue("Abstract")
    ABSTRACT("Abstract"),
    @XmlEnumValue("SeriesInformation")
    SERIES_INFORMATION("SeriesInformation"),
    @XmlEnumValue("TableOfContents")
    TABLE_OF_CONTENTS("TableOfContents"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    DDescriptionTypeAtt(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DDescriptionTypeAtt fromValue(String v) {
        for (DDescriptionTypeAtt c: DDescriptionTypeAtt.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
