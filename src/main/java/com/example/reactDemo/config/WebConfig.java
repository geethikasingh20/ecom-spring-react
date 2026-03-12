package com.example.reactDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * To configure global CORS in a Spring Boot application 
 * (instead of using @CrossOrigin on each controller),
 *  you can create a configuration class that applies CORS settings 
 *  to all endpoints.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer  {

	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**") // apply to all paths
	                .allowedOrigins("http://localhost:3000","https://ecom-spring-react-1.onrender.com") // React dev server
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                .allowedHeaders("*");
	    }	
}