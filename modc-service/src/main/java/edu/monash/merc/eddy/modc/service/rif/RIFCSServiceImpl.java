package edu.monash.merc.eddy.modc.service.rif;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import java.util.Map;

/**
 * Created by simonyu on 12/08/2014.
 */
public class RIFCSServiceImpl implements RIFCSService, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void createRifcsFile(String rifcsStoreLocaton, String identifier, Map<String, String> templateValues, String rifcsTemplate) {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
