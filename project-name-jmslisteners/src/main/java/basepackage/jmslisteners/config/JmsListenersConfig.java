package basepackage.jmslisteners.config;

import java.util.ResourceBundle;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableAspectJAutoProxy
@EnableJms
public class JmsListenersConfig {

    @Autowired
    JmsListenersProperties jmsListenersProperties;
    
    @Bean(name = "jmsListenersMessageSource")
    public MessageSource getJmsListenersMessageSource() {
	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	messageSource.setBasename("classpath:jmslisteners-messages");
	messageSource.setDefaultEncoding("UTF-8");

	return messageSource;
    }

    @Bean(name = "jmsListenersMessages")
    public ResourceBundle getJmsListenersMessages() {
	MessageSourceResourceBundle resourceBundle = new MessageSourceResourceBundle(getJmsListenersMessageSource(), null);

	return resourceBundle;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws NamingException {
	JndiTemplate jndiTemplate = new JndiTemplate();

	JndiDestinationResolver jndiDestinationResolver = new JndiDestinationResolver();
	jndiDestinationResolver.setJndiTemplate(jndiTemplate);

	String jmsMessageBrokerJndiName = jmsListenersProperties.getPropertyValue("jms.messagebroker.jndiname");
	ConnectionFactory connectionFactory = (ConnectionFactory) jndiTemplate.lookup(jmsMessageBrokerJndiName);
	DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
	jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
	jmsListenerContainerFactory.setDestinationResolver(jndiDestinationResolver);
	jmsListenerContainerFactory.setTransactionManager(getJtaListenersTransactionManager());
	//jmsListenerContainerFactory.setErrorHandler(new MessageProcessingErrorHandler());
	//jmsListenerContainerFactory.setSessionTransacted(true);
	//jmsListenerContainerFactory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
	
	return jmsListenerContainerFactory;
    }
    
    @Bean(name = "jtaListenersTransactionManager")
    public JtaTransactionManager getJtaListenersTransactionManager() throws NamingException {
	String transactionManagerJndiName = jmsListenersProperties.getPropertyValue("jta.transactionmanager.jndiname");
	String userTransactionJndiName = jmsListenersProperties.getPropertyValue("jta.usertransaction.jndiname");
	
	JndiTemplate jndiTemplate = new JndiTemplate();
	TransactionManager transactionManager = (TransactionManager) jndiTemplate.lookup(transactionManagerJndiName);
	UserTransaction userTransaction = (UserTransaction) jndiTemplate.lookup(userTransactionJndiName);
	
	JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
	jtaTransactionManager.setTransactionManager(transactionManager);
	jtaTransactionManager.setUserTransaction(userTransaction);

	return jtaTransactionManager;
    }
    
}
