/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config;

/**
 * Constants representing the different testing parameters our tests can run with.
 */
public final class TestingParameters {

    private TestingParameters() {}

    /**
     * TESTING ENVIRONMENTS
     */
    public class Environments {
        /**
         * Local environment (PC or laptop). Limited amount of resources.
         */
        public static final String LOCAL = "local";

        /**
         * Server environment (in-house or cloud). Huge amount of resources.
         */
        public static final String SERVER = "server";
    }
}
