
package edu.monash.merc.eddy.modc.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSpatialType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WSpatialType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="gml"/>
 *     &lt;enumeration value="gmlKmlPolyCoords"/>
 *     &lt;enumeration value="gpx"/>
 *     &lt;enumeration value="iso31661"/>
 *     &lt;enumeration value="iso31662"/>
 *     &lt;enumeration value="iso19139dcmiBox"/>
 *     &lt;enumeration value="kml"/>
 *     &lt;enumeration value="kmlPolyCoords"/>
 *     &lt;enumeration value="dcmiPoint"/>
 *     &lt;enumeration value="text"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WSpatialType", namespace = "http://merc.monash.edu/ws/schema/mds")
@XmlEnum
public enum WSpatialType {

    @XmlEnumValue("gml")
    GML("gml"),
    @XmlEnumValue("gmlKmlPolyCoords")
    GML_KML_POLY_COORDS("gmlKmlPolyCoords"),
    @XmlEnumValue("gpx")
    GPX("gpx"),
    @XmlEnumValue("iso31661")
    ISO_31661("iso31661"),
    @XmlEnumValue("iso31662")
    ISO_31662("iso31662"),
    @XmlEnumValue("iso19139dcmiBox")
    ISO_19139_DCMI_BOX("iso19139dcmiBox"),
    @XmlEnumValue("kml")
    KML("kml"),
    @XmlEnumValue("kmlPolyCoords")
    KML_POLY_COORDS("kmlPolyCoords"),
    @XmlEnumValue("dcmiPoint")
    DCMI_POINT("dcmiPoint"),
    @XmlEnumValue("text")
    TEXT("text");
    private final String value;

    WSpatialType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WSpatialType fromValue(String v) {
        for (WSpatialType c: WSpatialType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
