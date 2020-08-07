package basepackage.jmslisteners.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:jmslisteners.properties")
public class JmsListenersProperties {

    @Autowired
    private Environment env;

    public String getPropertyValue(String propertyKey) {
	return env.getProperty(propertyKey);
    }
    
}
