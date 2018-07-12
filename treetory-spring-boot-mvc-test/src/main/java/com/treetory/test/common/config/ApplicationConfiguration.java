package com.treetory.test.common.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@ComponentScan(basePackages = {"com.treetory.test.mvc", "com.treetory.test.common.util"}, 
useDefaultFilters = false,
includeFilters = {
            @ComponentScan.Filter(value = Controller.class),
            @ComponentScan.Filter(value = Service.class),
            @ComponentScan.Filter(value = Repository.class),
            @ComponentScan.Filter(value = Component.class)
            }
)
@Configuration
@EnableAsync
@EnableWebMvc
@Import(value={BaseDatabaseConfig.class, BaseMyBatisConfig.class})
public class ApplicationConfiguration implements InitializingBean, ApplicationListener<ApplicationEvent>, WebMvcConfigurer {

    @Autowired
    private ApplicationContext appContext;
    
    @Bean(name = "templateResolver")
    @Description("Thymeleaf template resolver serving HTML")
    public ITemplateResolver templateResolver() {
    	// SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
    	ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    
    @Bean(name = "templateEngine")
    @Description("Thymeleaf template engine with Spring integration")
    public ISpringTemplateEngine templateEngine() {
    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    	templateEngine.setTemplateResolver((ITemplateResolver)appContext.getBean("templateResolver"));
    	templateEngine.addDialect(new LayoutDialect());
    	return templateEngine;
    }
    
    @Bean(name = "thymeleafViewResolver")
    @Description("Thymeleaf view resolver")
    public ViewResolver thymeleafViewResolver() {
    	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    	viewResolver.setCharacterEncoding("UTF-8");
    	viewResolver.setTemplateEngine((ISpringTemplateEngine)appContext.getBean("templateEngine"));
    	return viewResolver;
    }
    
    /**
     * REST 요청 시, 한글로 된 body 를 받을 때 한글 깨짐 방지
     */
    @Bean
    @Description("Prevent the broken euckr character set.")
    public Filter charactertEncodingFilter() {
    	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    	characterEncodingFilter.setEncoding("UTF-8");
    	characterEncodingFilter.setForceEncoding(true);
    	return characterEncodingFilter;
    }
    
    @Override
    @Description("Every URI for requesting view is registerd in here.")
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/").setViewName("bbs");
    }
    
    @Override
    @Description("Every resources for requesting from view is registerd in here.")
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**", "/css/**", "/images/**", "/js/**", "/lib/**", "/fonts/**")
    	.addResourceLocations(
    			"classpath:/resources/",
    			"classpath:/static/css/",
    			"classpath:/static/images/",
    			"classpath:/static/js/",
    			"classpath:/static/lib/",
    			"classpath:/static/fonts/"
    			)
    	.setCachePeriod(600).resourceChain(true).addResolver(new PathResourceResolver());
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
    	
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
           	
    	switch (event.getClass().getSimpleName()) {
    	
    	case "ContextRefreshedEvent" :
    		break;
    	case "ServletWebServerInitializedEvent" :
    		break;
    	case "ApplicationStartedEvent" :
    		break;
    	case "ApplicationReadyEvent":
    		break;
    	case "ContextClosedEvent" :
    		break;
    	}
    	
    }
    
}
