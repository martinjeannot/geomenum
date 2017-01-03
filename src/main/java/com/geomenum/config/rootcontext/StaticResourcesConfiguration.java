/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.net.URI;

/**
 * Interface for profile-specific static resources configuration classes.
 */
public interface StaticResourcesConfiguration {

    URI getReadRootURI();

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
     */
    void addResourceHandlers(ResourceHandlerRegistry registry);
}
