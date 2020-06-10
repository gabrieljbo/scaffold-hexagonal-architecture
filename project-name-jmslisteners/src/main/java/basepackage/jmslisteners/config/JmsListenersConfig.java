package basepackage.jmslisteners.config;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiTemplate;

@Configuration
@EnableJms
public class JmsListenersConfig {

    @Autowired
    JmsListenersProperties jmsListenersProperties;

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws NamingException {
	JndiTemplate jndiTemplate = new JndiTemplate();

	JndiDestinationResolver jndiDestinationResolver = new JndiDestinationResolver();
	jndiDestinationResolver.setJndiTemplate(jndiTemplate);

	String jmsMessageBrokerJndiName = jmsListenersProperties.getPropertyValue("jms.messagebroker.jndiName");
	ConnectionFactory connectionFactory = (ConnectionFactory) jndiTemplate.lookup(jmsMessageBrokerJndiName);
	DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
	jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
	jmsListenerContainerFactory.setDestinationResolver(jndiDestinationResolver);
	
	return jmsListenerContainerFactory;
    }

}
