
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DRelatedIdentifierTypeAtt.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DRelatedIdentifierTypeAtt">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ARK"/>
 *     &lt;enumeration value="DOI"/>
 *     &lt;enumeration value="EAN13"/>
 *     &lt;enumeration value="EISSN"/>
 *     &lt;enumeration value="Handle"/>
 *     &lt;enumeration value="ISBN"/>
 *     &lt;enumeration value="ISSN"/>
 *     &lt;enumeration value="ISTC"/>
 *     &lt;enumeration value="LISSN"/>
 *     &lt;enumeration value="LSID"/>
 *     &lt;enumeration value="PURL"/>
 *     &lt;enumeration value="UPC"/>
 *     &lt;enumeration value="URL"/>
 *     &lt;enumeration value="URN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DRelatedIdentifierTypeAtt")
@XmlEnum
public enum DRelatedIdentifierTypeAtt {

    ARK("ARK"),
    DOI("DOI"),
    @XmlEnumValue("EAN13")
    EAN_13("EAN13"),
    EISSN("EISSN"),
    @XmlEnumValue("Handle")
    HANDLE("Handle"),
    ISBN("ISBN"),
    ISSN("ISSN"),
    ISTC("ISTC"),
    LISSN("LISSN"),
    LSID("LSID"),
    PURL("PURL"),
    UPC("UPC"),
    URL("URL"),
    URN("URN");
    private final String value;

    DRelatedIdentifierTypeAtt(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DRelatedIdentifierTypeAtt fromValue(String v) {
        for (DRelatedIdentifierTypeAtt c: DRelatedIdentifierTypeAtt.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
