/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import java.net.URI;

/**
 * This profile-specific component is used to resolve our static resources.
 */
public interface StaticResourcesResolver {

    URI resolve(URI resourceURI);
}
