package edu.monash.merc.eddy.modc.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by simonyu on 12/08/2014.
 */

@Scope(BeanDefinition.SCOPE_SINGLETON)
@Component
public class SystemPropertySettings {

    @Autowired
    @Qualifier("systemPropertiesConfigurer")
    private SystemPropertiesConfigurer systemPropertiesConfigurer;

    public void setSystemPropertiesConfigurer(SystemPropertiesConfigurer systemPropertiesConfigurer) {
        this.systemPropertiesConfigurer = systemPropertiesConfigurer;
    }

    public String getPropValue(String propKey) {
        String propValue = this.systemPropertiesConfigurer.getPropValue(propKey);
        if (propValue != null) {
            propValue = propValue.trim();
        }
        return propValue;
    }

    public Map<String, String> getResolvedProps() {
        return this.systemPropertiesConfigurer.getResolvedProps();
    }
}
