
package com.payment.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:/static/";
    
    @Override
	protected void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**")
		.allowedOrigins("*");
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"assets/").setCachePeriod(31536000);
        registry.addResourceHandler("/dist/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"dist/").setCachePeriod(31536000);
        registry.addResourceHandler("/css/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"css/").setCachePeriod(31536000);
        registry.addResourceHandler("/js/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"js/").setCachePeriod(31536000);
        registry.addResourceHandler("/img/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"img/").setCachePeriod(31536000);
        registry.addResourceHandler("/favicon.ico").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"img/").setCachePeriod(31536000);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/defaultLayout").setViewName("defaultLayout");
    }

}
