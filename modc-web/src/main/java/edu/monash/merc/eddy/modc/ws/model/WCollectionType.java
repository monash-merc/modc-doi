
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WCollectionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WCollectionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="catalogueOrIndex"/>
 *     &lt;enumeration value="collection"/>
 *     &lt;enumeration value="registry"/>
 *     &lt;enumeration value="repository"/>
 *     &lt;enumeration value="dataset"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WCollectionType", namespace = "http://merc.monash.edu/ws/schema/mds")
@XmlEnum
public enum WCollectionType {

    @XmlEnumValue("catalogueOrIndex")
    CATALOGUE_OR_INDEX("catalogueOrIndex"),
    @XmlEnumValue("collection")
    COLLECTION("collection"),
    @XmlEnumValue("registry")
    REGISTRY("registry"),
    @XmlEnumValue("repository")
    REPOSITORY("repository"),
    @XmlEnumValue("dataset")
    DATASET("dataset");
    private final String value;

    WCollectionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WCollectionType fromValue(String v) {
        for (WCollectionType c: WCollectionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
