/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Persistence Spring configuration.
 */
@Configuration
@Import({MongoConfiguration.class})
@ComponentScan(basePackages = {"com.geomenum.persistence.services", "com.geomenum.persistence.servicefacades"})
public class PersistenceConfiguration {
}
