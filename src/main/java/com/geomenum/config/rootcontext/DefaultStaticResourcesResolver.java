/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
class DefaultStaticResourcesResolver implements StaticResourcesResolver {

    private static final Logger logger = LoggerFactory.getLogger(DefaultStaticResourcesResolver.class);

    private final StaticResourcesConfiguration staticResourcesConfiguration;

    @Autowired
    public DefaultStaticResourcesResolver(StaticResourcesConfiguration staticResourcesConfiguration) {
        this.staticResourcesConfiguration = staticResourcesConfiguration;
    }

    @Override
    public URI resolve(URI resourceURI) {
        return staticResourcesConfiguration.getReadRootURI().resolve(resourceURI);
    }
}
