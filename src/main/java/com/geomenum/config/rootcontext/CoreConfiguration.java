/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import com.geomenum.r2d2.spring.config.AbstractSpringRRSLConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Core Spring configuration.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.geomenum.core.services",
        "com.geomenum.core.domainmodel",
        "com.geomenum.core.requesthandlers",
        "com.geomenum.core.security.components"})
public class CoreConfiguration extends AbstractSpringRRSLConfiguration {

    /**
     * A typical Spring MVC application has two application contexts:
     * - The "root" (middle-tier) context loaded via ContextLoaderListener
     * - The servlet context (web-tier) loaded by the DispatcherServlet
     *
     * The middle-tier context is set up as a parent to the servlet context, which means all beans in the middle-tier
     * context are visible to the servlet context but not the other way around. In Spring MVC applications it's a good
     * idea to define a "messageSource" bean in the middle-tier context so that messages are accessible in all tiers.
     *
     * See http://forum.spring.io/forum/spring-projects/web/web-flow/26002-nosuchmessageexception-in-propertyeditorregistrar?p=315370#post315370
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:i18n/messages"
        );
        // if true, the key of the message will be displayed if the key is not
        // found, instead of throwing a NoSuchMessageException
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        // # -1 : never reload, 0 always reload
        messageSource.setCacheSeconds(-1);
        return messageSource;
    }
}
