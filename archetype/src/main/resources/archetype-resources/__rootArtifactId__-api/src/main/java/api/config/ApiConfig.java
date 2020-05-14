#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.config;

import java.util.ResourceBundle;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ${package}.api.common.util.RequestHeaderInterceptor;

@SuppressWarnings("deprecation")
@Configuration
public class ApiConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "apiMessageSource")
    public MessageSource getApiMessageSource() {
	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	messageSource.setBasename("classpath:api-messages");
	messageSource.setDefaultEncoding("UTF-8");

	return messageSource;
    }

    @Bean(name = "apiMessages")
    public ResourceBundle getApiMessages() {
	MessageSourceResourceBundle resourceBundle = new MessageSourceResourceBundle(getApiMessageSource(), null);

	return resourceBundle;
    }

    @Bean(name = "apiValidator")
    public LocalValidatorFactoryBean getApiValidator() {
	LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	validator.setValidationMessageSource(getApiMessageSource());

	return validator;
    }

    @Override
    public Validator getValidator() {
	return getApiValidator();
    }

    @Bean
    public HandlerInterceptorAdapter getRequestHeaderInterceptor() {
	return new RequestHeaderInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(getRequestHeaderInterceptor());
    }

}
