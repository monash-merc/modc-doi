package edu.monash.merc.eddy.modc.common.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemPropertiesConfigurer extends PropertyPlaceholderConfigurer {

    private Map<String, String> resolvedProps;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory confListBeanFactory, Properties props) throws BeansException {
        Map<String, String> tmpProps = new HashMap<String, String>(props.size());
        super.processProperties(confListBeanFactory, props);
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            tmpProps.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        this.resolvedProps = Collections.unmodifiableMap(tmpProps);
    }

    public Map<String, String> getResolvedProps() {
        return resolvedProps;
    }

    public String getPropValue(String propKey) {
        return resolvedProps.get(propKey);
    }
}
