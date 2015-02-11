package edu.monash.merc.eddy.modc.domain;

/**
 * Created by simonyu on 25/08/2014.
 */
public enum MCitationType {
    HARVARD("Harvard"), APA("APA"), MLA("MLA"), VANCOUVER("Vancouver"), IEEE("IEEE"), CSE("CSE"),
    CHICAGO("Chicago"), AMA("AMA"), AGPS_AGIMO("AGPS-AGIMO"), AGLC("AGLC"), ACS("ACS"), DATACITE("Datacite");
    private final String type;

    MCitationType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static MCitationType fromType(String type) {
        for (MCitationType mCitationType : MCitationType.values()) {
            if (mCitationType.type.equalsIgnoreCase(type)) {
                return mCitationType;
            }
        }
        //we just say it is 'Harvard'
        return HARVARD;
    }

}
