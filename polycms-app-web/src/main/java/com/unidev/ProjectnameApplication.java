package com.unidev;

import ch.qos.logback.classic.ViewStatusMessagesServlet;
import com.unidev.platform.j2ee.common.WebUtils;
import com.unidev.polyembeddedcms.PolyCore;
import org.jminix.console.servlet.MiniConsoleServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;

@SpringBootApplication
@ComponentScan("com.unidev")
@EnableCaching
@EnableWebSecurity
public class ProjectnameApplication extends SpringBootServletInitializer implements ServletContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProjectnameApplication.class, args);
	}

	// -----------------------------------------------------------------------------------------------


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProjectnameApplication.class);
	}

	@Value("${storage.root}")
	private String storageRoot;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addServlet("ViewStatusMessages", ViewStatusMessagesServlet.class).addMapping("/logs");
		servletContext.addServlet("JmxMiniConsoleServlet", MiniConsoleServlet.class).addMapping("/jmx/*");
	}

	@Bean
	public PolyCore polyCore() {
		PolyCore polyCore = new PolyCore();
		polyCore.setStorageRoot(new File(storageRoot));
		return polyCore;
	}

	@Bean
	public WebUtils webUtils() {
		return new WebUtils();
	}

}
