package edu.monash.merc.eddy.modc.domain;

/**
 * Created by simonyu on 25/08/2014.
 */
public enum MIdentifierType {
    ABN("abn"), ARC("arc"), ARK("ark"), DOI("doi"), HANDLE("handle"),
    INFOURI("infouri"), ISIL("isil"), LOCAL("local"), PURL("purl"), URI("uri");

    private final String type;

    MIdentifierType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }

    public static MIdentifierType fromType(String type) {
        for (MIdentifierType identifierType : MIdentifierType.values()) {
            if (identifierType.type.equalsIgnoreCase(type)) {
                return identifierType;
            }
        }
        //if not found, we just say: the type is 'LOCAL'
        return LOCAL;
    }

}
