/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.servletcontext;

import com.geomenum.config.rootcontext.StaticResourcesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Web Spring configuration (Spring MVC).
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.geomenum.web.controllers"})
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private StaticResourcesConfiguration staticResourcesConfiguration;

    /******************************************************
     * THYMELEAF-SPECIFIC ARTIFACTS                       *
     * TemplateResolver <- TemplateEngine <- ViewResolver *
     ******************************************************/

    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"*"});
        viewResolver.setCache(false);
        // UTF-8 encoding (http://forum.thymeleaf.org/UTF8-charset-problem-td3608879.html)
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("text/html; charset=UTF-8");
        return viewResolver;
    }

    /**********************************
     * RESOURCE FOLDERS CONFIGURATION *
     **********************************/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/resources/img/favicon.ico");
        registry.addResourceHandler("/robots.txt").addResourceLocations("/resources/robots.txt");
        staticResourcesConfiguration.addResourceHandlers(registry);
    }

    /**********************************************************************************
     *                  MESSAGE EXTERNALIZATION/INTERNATIONALIZATION                  *
     *                                                                                *
     * @see com.geomenum.config.rootcontext.CoreConfiguration#messageSource()         *
     * @see com.geomenum.config.rootcontext.WebSecurityConfiguration#localeResolver() *
     **********************************************************************************/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // localeChangeInterceptor
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        registry.addInterceptor(localeChangeInterceptor);
    }

    /***************
     * FILE UPLOAD *
     ***************/

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
