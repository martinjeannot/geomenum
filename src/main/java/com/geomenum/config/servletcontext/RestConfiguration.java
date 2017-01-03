/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.servletcontext;

import com.geomenum.rest.RestApiUri;
import com.geomenum.rest.controllers.JsonPCallbackHandlerInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Rest Spring configuration (Spring MVC).
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.geomenum.rest.controllers"})
public class RestConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // jsonPCallbackHandlerInterceptor
        JsonPCallbackHandlerInterceptor jsonPCallbackHandlerInterceptor = new JsonPCallbackHandlerInterceptor();
        registry.addInterceptor(jsonPCallbackHandlerInterceptor).addPathPatterns(RestApiUri.API_ROOT_V1 + "/**");
    }
}
