/*
 * Copyright (c) 2014, Monash e-Research Centre
 *  (Monash University, Australia)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  	* Neither the name of the Monash University nor the names of its
 *  	  contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.eddy.modc.common.config;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 23/09/2014
 */
public class MFreeMarkerConfiguration implements InitializingBean, ResourceLoaderAware {
    protected final Logger logger = Logger.getLogger(this.getClass().getName());

    private Configuration configuration;

    private Resource configLocation;

    private Properties freemarkerSettings;

    private Map<String, Object> freemarkerVariables;

    private String defaultEncoding;

    private final List<TemplateLoader> templateLoaders = new ArrayList<TemplateLoader>();

    private List<TemplateLoader> preTemplateLoaders;

    private List<TemplateLoader> postTemplateLoaders;

    private String[] templateLoaderPaths;

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    private boolean preferFileSystemAccess = true;


    /**
     * Set the location of the FreeMarker config file.
     * Alternatively, you can specify all setting locally.
     *
     * @see #setFreemarkerSettings
     * @see #setTemplateLoaderPath
     */
    public void setConfigLocation(Resource resource) {
        configLocation = resource;
    }

    /**
     * Set properties that contain well-known FreeMarker keys which will be
     * passed to FreeMarker's {@code Configuration.setSettings} method.
     *
     * @see freemarker.template.Configuration#setSettings
     */
    public void setFreemarkerSettings(Properties settings) {
        this.freemarkerSettings = settings;
    }

    /**
     * Set a Map that contains well-known FreeMarker objects which will be passed
     * to FreeMarker's {@code Configuration.setAllSharedVariables()} method.
     *
     * @see freemarker.template.Configuration#setAllSharedVariables
     */
    public void setFreemarkerVariables(Map<String, Object> variables) {
        this.freemarkerVariables = variables;
    }

    /**
     * Set the default encoding for the FreeMarker configuration.
     * If not specified, FreeMarker will use the platform file encoding.
     * <p>Used for template rendering unless there is an explicit encoding specified
     * for the rendering process (for example, on Spring's FreeMarkerView).
     *
     * @see freemarker.template.Configuration#setDefaultEncoding
     */
    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    /**
     * Set a List of {@code TemplateLoader}s that will be used to search
     * for templates. For example, one or more custom loaders such as database
     * loaders could be configured and injected here.
     * <p>The {@link TemplateLoader TemplateLoaders} specified here will be
     * registered <i>before</i> the default template loaders that this factory
     * registers (such as loaders for specified "templateLoaderPaths" or any
     * loaders registered in {@link #postProcessTemplateLoaders}).
     *
     * @see #setTemplateLoaderPaths
     * @see #postProcessTemplateLoaders
     */
    public void setPreTemplateLoaders(TemplateLoader... preTemplateLoaders) {
        this.preTemplateLoaders = Arrays.asList(preTemplateLoaders);
    }

    /**
     * Set a List of {@code TemplateLoader}s that will be used to search
     * for templates. For example, one or more custom loaders such as database
     * loaders can be configured.
     * <p>The {@link TemplateLoader TemplateLoaders} specified here will be
     * registered <i>after</i> the default template loaders that this factory
     * registers (such as loaders for specified "templateLoaderPaths" or any
     * loaders registered in {@link #postProcessTemplateLoaders}).
     *
     * @see #setTemplateLoaderPaths
     * @see #postProcessTemplateLoaders
     */
    public void setPostTemplateLoaders(TemplateLoader... postTemplateLoaders) {
        this.postTemplateLoaders = Arrays.asList(postTemplateLoaders);
    }

    /**
     * Set the Freemarker template loader path via a Spring resource location.
     * See the "templateLoaderPaths" property for details on path handling.
     *
     * @see #setTemplateLoaderPaths
     */
    public void setTemplateLoaderPath(String templateLoaderPath) {
        this.templateLoaderPaths = new String[]{templateLoaderPath};
    }

    /**
     * Set multiple Freemarker template loader paths via Spring resource locations.
     * <p>When populated via a String, standard URLs like "file:" and "classpath:"
     * pseudo URLs are supported, as understood by ResourceEditor. Allows for
     * relative paths when running in an ApplicationContext.
     * <p>Will define a path for the default FreeMarker template loader.
     * If a specified resource cannot be resolved to a {@code java.io.File},
     * a generic SpringTemplateLoader will be used, without modification detection.
     * <p>To enforce the use of SpringTemplateLoader, i.e. to not resolve a path
     * as file system resource in any case, turn off the "preferFileSystemAccess"
     * flag. See the latter's javadoc for details.
     * <p>If you wish to specify your own list of TemplateLoaders, do not set this
     * property and instead use {@code setTemplateLoaders(List templateLoaders)}
     *
     * @see org.springframework.core.io.ResourceEditor
     * @see org.springframework.context.ApplicationContext#getResource
     * @see freemarker.template.Configuration#setDirectoryForTemplateLoading
     * @see org.springframework.ui.freemarker.SpringTemplateLoader
     */
    public void setTemplateLoaderPaths(String... templateLoaderPaths) {
        this.templateLoaderPaths = templateLoaderPaths;
    }

    /**
     * Set the Spring ResourceLoader to use for loading FreeMarker template files.
     * The default is DefaultResourceLoader. Will get overridden by the
     * ApplicationContext if running in a context.
     *
     * @see org.springframework.core.io.DefaultResourceLoader
     */
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Return the Spring ResourceLoader to use for loading FreeMarker template files.
     */
    protected ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    /**
     * Set whether to prefer file system access for template loading.
     * File system access enables hot detection of template changes.
     * <p>If this is enabled, FreeMarkerConfigurationFactory will try to resolve
     * the specified "templateLoaderPath" as file system resource (which will work
     * for expanded class path resources and ServletContext resources too).
     * <p>Default is "true". Turn this off to always load via SpringTemplateLoader
     * (i.e. as stream, without hot detection of template changes), which might
     * be necessary if some of your templates reside in an expanded classes
     * directory while others reside in jar files.
     *
     * @see #setTemplateLoaderPath
     */
    public void setPreferFileSystemAccess(boolean preferFileSystemAccess) {
        this.preferFileSystemAccess = preferFileSystemAccess;
    }

    /**
     * Return whether to prefer file system access for template loading.
     */
    protected boolean isPreferFileSystemAccess() {
        return this.preferFileSystemAccess;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Initialize FreeMarkerConfigurationFactory's Configuration
     * if not overridden by a preconfigured FreeMarker Configuation.
     * <p>Sets up a ClassTemplateLoader to use for loading Spring macros.
     *
     * @see #createConfiguration
     * @see #setConfiguration
     */
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        if (this.configuration == null) {
            this.configuration = createConfiguration();
        }
    }

    /**
     * Prepare the FreeMarker Configuration and return it.
     *
     * @return the FreeMarker Configuration object
     * @throws java.io.IOException                   if the config file wasn't found
     * @throws freemarker.template.TemplateException on FreeMarker initialization failure
     */
    public Configuration createConfiguration() throws IOException, TemplateException {
        Configuration config = newConfiguration();
        Properties props = new Properties();

        // Load config file if specified.
        if (this.configLocation != null) {
            if (logger.isInfoEnabled()) {
                logger.info("Loading FreeMarker configuration from " + this.configLocation);
            }
            PropertiesLoaderUtils.fillProperties(props, this.configLocation);
        }

        // Merge local properties if specified.
        if (this.freemarkerSettings != null) {
            props.putAll(this.freemarkerSettings);
        }

        // FreeMarker will only accept known keys in its setSettings and
        // setAllSharedVariables methods.
        if (!props.isEmpty()) {
            config.setSettings(props);
        }

        if (!CollectionUtils.isEmpty(this.freemarkerVariables)) {
            config.setAllSharedVariables(new SimpleHash(this.freemarkerVariables, config.getObjectWrapper()));
        }

        if (this.defaultEncoding != null) {
            config.setDefaultEncoding(this.defaultEncoding);
        }

        List<TemplateLoader> templateLoaders = new LinkedList<TemplateLoader>(this.templateLoaders);

        // Register template loaders that are supposed to kick in early.
        if (this.preTemplateLoaders != null) {
            templateLoaders.addAll(this.preTemplateLoaders);
        }

        // Register default template loaders.
        if (this.templateLoaderPaths != null) {
            for (String path : this.templateLoaderPaths) {
                templateLoaders.add(getTemplateLoaderForPath(path));
            }
        }
        postProcessTemplateLoaders(templateLoaders);

        // Register template loaders that are supposed to kick in late.
        if (this.postTemplateLoaders != null) {
            templateLoaders.addAll(this.postTemplateLoaders);
        }

        TemplateLoader loader = getAggregateTemplateLoader(templateLoaders);
        if (loader != null) {
            config.setTemplateLoader(loader);
        }

        postProcessConfiguration(config);
        return config;
    }

    /**
     * Return a new Configuration object. Subclasses can override this for
     * custom initialization, or for using a mock object for testing.
     * <p>Called by {@code createConfiguration()}.
     *
     * @return the Configuration object
     * @throws IOException       if a config file wasn't found
     * @throws TemplateException on FreeMarker initialization failure
     * @see #createConfiguration()
     */
    protected Configuration newConfiguration() throws IOException, TemplateException {
        return new Configuration();
    }

    /**
     * Determine a FreeMarker TemplateLoader for the given path.
     * <p>Default implementation creates either a FileTemplateLoader or
     * a SpringTemplateLoader.
     *
     * @param templateLoaderPath the path to load templates from
     * @return an appropriate TemplateLoader
     * @see freemarker.cache.FileTemplateLoader
     * @see org.springframework.ui.freemarker.SpringTemplateLoader
     */
    protected TemplateLoader getTemplateLoaderForPath(String templateLoaderPath) {
        if (isPreferFileSystemAccess()) {
            // Try to load via the file system, fall back to SpringTemplateLoader
            // (for hot detection of template changes, if possible).
            try {
                Resource path = getResourceLoader().getResource(templateLoaderPath);
                File file = path.getFile();  // will fail if not resolvable in the file system
                if (logger.isDebugEnabled()) {
                    logger.debug(
                            "Template loader path [" + path + "] resolved to file path [" + file.getAbsolutePath() + "]");
                }
                return new FileTemplateLoader(file);
            } catch (IOException ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Cannot resolve template loader path [" + templateLoaderPath +
                            "] to [java.io.File]: using SpringTemplateLoader as fallback", ex);
                }
                return new SpringTemplateLoader(getResourceLoader(), templateLoaderPath);
            }
        } else {
            // Always load via SpringTemplateLoader (without hot detection of template changes).
            logger.debug("File system access not preferred: using SpringTemplateLoader");
            return new SpringTemplateLoader(getResourceLoader(), templateLoaderPath);
        }
    }

    /**
     * To be overridden by subclasses that want to to register custom
     * TemplateLoader instances after this factory created its default
     * template loaders.
     * <p>Called by {@code createConfiguration()}. Note that specified
     * "postTemplateLoaders" will be registered <i>after</i> any loaders
     * registered by this callback; as a consequence, they are are <i>not</i>
     * included in the given List.
     *
     * @param templateLoaders the current List of TemplateLoader instances,
     *                        to be modified by a subclass
     * @see #createConfiguration()
     * @see #setPostTemplateLoaders
     */
    protected void postProcessTemplateLoaders(List<TemplateLoader> templateLoaders) {
    }

    /**
     * Return a TemplateLoader based on the given TemplateLoader list.
     * If more than one TemplateLoader has been registered, a FreeMarker
     * MultiTemplateLoader needs to be created.
     *
     * @param templateLoaders the final List of TemplateLoader instances
     * @return the aggregate TemplateLoader
     */
    protected TemplateLoader getAggregateTemplateLoader(List<TemplateLoader> templateLoaders) {
        int loaderCount = templateLoaders.size();
        switch (loaderCount) {
            case 0:
                logger.info("No FreeMarker TemplateLoaders specified");
                return null;
            case 1:
                return templateLoaders.get(0);
            default:
                TemplateLoader[] loaders = templateLoaders.toArray(new TemplateLoader[loaderCount]);
                return new MultiTemplateLoader(loaders);
        }
    }

    /**
     * To be overridden by subclasses that want to to perform custom
     * post-processing of the Configuration object after this factory
     * performed its default initialization.
     * <p>Called by {@code createConfiguration()}.
     *
     * @param config the current Configuration object
     * @throws IOException       if a config file wasn't found
     * @throws TemplateException on FreeMarker initialization failure
     * @see #createConfiguration()
     */
    protected void postProcessConfiguration(Configuration config) throws IOException, TemplateException {
    }

}
