/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence;

/**
 * This class stores constants representing all our mongoDB collection names.
 */
public final class DBCollection {

    /**
     * Stores the root level of any menu.
     */
    public static final String MENUS = "menus";

    /**
     * Stores our users (!= our users's customers).
     */
    public static final String USERS = "users";

    /**
     * Stores the establishments enabled with our services.
     */
    public static final String RESTAURANTS = "restaurants";
}
