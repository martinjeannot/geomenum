/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config;

import com.geomenum.config.rootcontext.MongoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * {@link com.geomenum.config.rootcontext.MongoConfiguration} for testing.
 */
@Configuration
public class MongoTestConfiguration extends MongoConfiguration {

    private static final String DATABASE_NAME = "geomenumtest";

    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }
}
