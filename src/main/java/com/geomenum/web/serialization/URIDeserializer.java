/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.geomenum.config.rootcontext.StaticResourcesResolver;

import java.io.IOException;
import java.net.URI;

public class URIDeserializer extends JsonDeserializer<URI> {

    private final StaticResourcesResolver staticResourcesResolver;

    public URIDeserializer(StaticResourcesResolver staticResourcesResolver) {
        this.staticResourcesResolver = staticResourcesResolver;
    }

    @Override
    public URI deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        URI uri = URI.create(jp.getValueAsString());
        // currently, any non-absolute URI is considered a static resource. This may change in the future.
        if(!uri.isAbsolute()) {
            uri = staticResourcesResolver.resolve(uri);
        }
        return uri;
    }
}
