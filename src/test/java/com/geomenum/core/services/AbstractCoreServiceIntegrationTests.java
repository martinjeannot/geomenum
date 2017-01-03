/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services;

import com.geomenum.common.AbstractIntegrationTests;
import com.geomenum.config.CoreTestConfiguration;
import com.geomenum.config.PersistenceTestConfiguration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {CoreTestConfiguration.class, PersistenceTestConfiguration.class})
public class AbstractCoreServiceIntegrationTests extends AbstractIntegrationTests {
}
