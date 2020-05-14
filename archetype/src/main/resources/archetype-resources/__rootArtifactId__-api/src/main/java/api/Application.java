#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import ${package}.api.config.ApiProperties;

@Configuration
@ComponentScan("${package}")
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class Application extends SpringBootServletInitializer {

    private static Class<Application> applicationClass = Application.class;

    @Autowired
    private ApiProperties apiProperties;

    @PostConstruct
    public void init() {
	String timeZone = apiProperties.getPropertyValue("timeZone");
	TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }

    public static void main(String[] args) {
	SpringApplication.run(applicationClass, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(applicationClass);
    }

    @Override
    public void onStartup(ServletContext container) throws ServletException {
	AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	context.setConfigLocation(Application.class.getName());

	Dynamic registration = container.addServlet("dispatcherServlet", new DispatcherServlet(context));
	registration.setLoadOnStartup(1);
	registration.addMapping("/rest/1.0/*");

	super.onStartup(container);
    }

}