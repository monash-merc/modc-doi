package edu.monash.merc.eddy.modc.domain;

/**
 * Created by simonyu on 25/08/2014.
 */
public enum MAddressType {
    EMAIL("email"), URL("url"), OTHER("other");

    private final String type;

    MAddressType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }

    public static MAddressType fromType(String type) {
        for (MAddressType addressType : MAddressType.values()) {
            if (addressType.type.equals(type)) {
                return addressType;
            }
        }
        //if not found, we just it's OTHER
        return OTHER;
    }
}
