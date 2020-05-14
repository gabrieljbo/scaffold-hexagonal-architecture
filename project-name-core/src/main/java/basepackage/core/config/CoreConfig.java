package basepackage.core.config;

import java.util.ResourceBundle;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories("basepackage.core")
@EntityScan("basepackage.core")
public class CoreConfig {

    @Bean(name = "coreMessageSource")
    public MessageSource getCoreMessageSource() {
	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	messageSource.setBasename("classpath:core-messages");
	messageSource.setDefaultEncoding("UTF-8");

	return messageSource;
    }

    @Bean(name = "coreMessages")
    public ResourceBundle getCoreMessages() {
	MessageSourceResourceBundle resourceBundle = new MessageSourceResourceBundle(getCoreMessageSource(), null);

	return resourceBundle;
    }

    @Bean(name = "coreValidator")
    public LocalValidatorFactoryBean getCoreValidator() {
	LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	validator.setValidationMessageSource(getCoreMessageSource());

	return validator;
    }

    @Bean
    public MethodValidationPostProcessor getMethodValidationPostProcessor() {
	MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
	processor.setValidator(getCoreValidator());

	return processor;
    }
}
