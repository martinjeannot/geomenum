/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.serialization;

import com.geomenum.config.rootcontext.StaticResourcesResolver;

/**
 * Currently, the URIDeserializer does the exact same job regarding the WEB domain or the REST domain hence this
 * inheritance relationship. This may change in the future.
 *
 * @see com.geomenum.web.serialization.URIDeserializer
 */
public class URIDeserializer extends com.geomenum.web.serialization.URIDeserializer {

    public URIDeserializer(StaticResourcesResolver staticResourcesResolver) {
        super(staticResourcesResolver);
    }
}
