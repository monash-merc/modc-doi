package edu.monash.merc.eddy.modc.service.rif;

import java.util.Map;

/**
 * Created by simonyu on 12/08/2014.
 */
public interface RIFCSService {
    /**
     * Create a RIFCS file and save in a store location
     *
     * @param rifcsStoreLocaton a rifcs file store location
     * @param identifier        a rifcs identifier
     * @param templateValues    a rifcs template data
     * @param rifcsTemplate     a rifcs file template
     */
    void createRifcsFile(String rifcsStoreLocaton, String identifier, Map<String, String> templateValues, String rifcsTemplate);
}
