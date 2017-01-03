/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import com.geomenum.config.profiles.Development;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@Development
public class LocalStaticResourcesConfiguration extends AbstractStaticResourcesConfiguration {

    private static final String READ_ROOT = "http://localhost:8080/static/"; // trailing slash is mandatory

    public static final String FILE_UPLOAD_ROOT = "/home/phnx/dev/java/workspaces/prototypes/geomenum/data/";

    @Override
    protected String getReadRoot() {
        return READ_ROOT;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:" + FILE_UPLOAD_ROOT);
    }
}
