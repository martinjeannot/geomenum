/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services;

import com.geomenum.common.AbstractIntegrationTests;
import com.geomenum.config.PersistenceTestConfiguration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {PersistenceTestConfiguration.class})
public class AbstractPersistenceServiceIntegrationTests extends AbstractIntegrationTests {
}
