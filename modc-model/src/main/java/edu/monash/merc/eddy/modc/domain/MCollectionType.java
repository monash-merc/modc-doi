
package edu.monash.merc.eddy.modc.domain;


public enum MCollectionType {

    CATALOGUE_OR_INDEX("catalogueOrIndex"),
    COLLECTION("collection"),
    REGISTRY("registry"),
    REPOSITORY("repository"),
    DATASET("dataset");
    private final String type;

    MCollectionType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static MCollectionType fromType(String type) {
        for (MCollectionType collectionType : MCollectionType.values()) {

            if (collectionType.type.equalsIgnoreCase(type)) {
                return collectionType;
            }
        }
        //If not found, we just say it's COLLECTION
        return COLLECTION;
    }
}
