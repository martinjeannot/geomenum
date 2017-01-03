/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@link com.geomenum.config.rootcontext.PersistenceConfiguration} for testing.
 */
@Configuration
@Import(MongoTestConfiguration.class)
@ComponentScan(basePackages = {"com.geomenum.persistence.services", "com.geomenum.persistence.servicefacades"})
public class PersistenceTestConfiguration { // cannot extend because of superclass annotations
}
