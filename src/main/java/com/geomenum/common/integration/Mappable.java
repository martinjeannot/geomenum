/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.integration;

import java.util.Map;

/**
 * Any object implementing this interface have the ability to be represented as a {@link Map}.<br/>
 * Objects must implement this interface to be able to be sent from one domain to another.
 */
public interface Mappable {

    /**
     * Returns a {@link Map} of this object's properties.
     *
     * @return a {@link Map}
     */
    Map<Object, Object> toMap();
}
