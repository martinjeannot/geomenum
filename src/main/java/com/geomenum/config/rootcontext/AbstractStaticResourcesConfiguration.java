/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.net.URI;

abstract class AbstractStaticResourcesConfiguration implements StaticResourcesConfiguration {

    private URI readRootURI;

    /**
     * This configuration field indicates the root path to read static resources from.
     *
     * @return a String
     */
    protected abstract String getReadRoot();

    @Override
    public URI getReadRootURI() {
        if (readRootURI == null) {
            readRootURI = URI.create(getReadRoot());
        }
        return readRootURI;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {}
}
