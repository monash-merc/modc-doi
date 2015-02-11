package edu.monash.merc.eddy.modc.domain;

/**
 * Created by simonyu on 25/08/2014.
 */
public enum MRelationType {
    HAS_ASSOCIATION_WITH("hasAssociationWith"), HAS_MEMBER("hasMember"), HAS_PART("hasPart"),

    IS_COLLECTOR_OF("isCollectorOf"), IS_FUNDED_BY("isFundedBy"), IS_FUNDER_OF("isFunderOf"),

    IS_MANAGED_BY("isManagedBy"), IS_MANAGER_OF("isManagerOf"), IS_MEMBER_OF("isMemberOf"),

    IS_OWNED_BY("isOwnedBy"), IS_OWNER_OF("isOwnerOf"), IS_PART_OF("isPartOf"),

    IS_PARTICIPANT_IN("isParticipantIn"), ENRICHES("enriches");
    private final String type;

    MRelationType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static MRelationType fromType(String type) {
        for (MRelationType relationType : MRelationType.values()) {
            if (relationType.type().equalsIgnoreCase(type)) {
                return relationType;
            }
        }
        //if not found, we just say the relation is 'isMemberOf'
        return IS_MEMBER_OF;
    }
}
