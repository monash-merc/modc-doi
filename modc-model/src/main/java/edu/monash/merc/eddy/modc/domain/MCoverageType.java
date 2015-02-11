
package edu.monash.merc.eddy.modc.domain;

public enum MCoverageType {

    GML("gml"), GML_KML_POLY_COORDS("gmlKmlPolyCoords"), GPX("gpx"),
    ISO_31661("iso31661"), ISO_31662("iso31662"), ISO_19139_DCMI_BOX("iso19139dcmiBox"),
    KML("kml"), KML_POLY_COORDS("kmlPolyCoords"), DCMI_POINT("dcmiPoint"),
    TEXT("text");

    private final String type;

    MCoverageType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static MCoverageType fromType(String type) {
        for (MCoverageType coverageType : MCoverageType.values()) {
            if (coverageType.type.equalsIgnoreCase(type)) {
                return coverageType;
            }
        }
        //if not found, we just it's TEXT;
        return TEXT;
    }

}
