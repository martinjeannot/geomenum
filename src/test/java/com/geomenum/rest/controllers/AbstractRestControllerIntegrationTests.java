/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers;

import com.geomenum.config.CoreTestConfiguration;
import com.geomenum.config.PersistenceTestConfiguration;
import com.geomenum.config.RestTestConfiguration;
import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.menu.PersistenceMenuFixtures;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurantFixtures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.geomenum.config.TestingLevels.INTEGRATION;

@WebAppConfiguration
@ContextConfiguration(classes = {
        //WebSecurityTestConfiguration.class,
        RestTestConfiguration.class,
        CoreTestConfiguration.class,
        PersistenceTestConfiguration.class})
@Test(groups = {INTEGRATION})
public abstract class AbstractRestControllerIntegrationTests extends AbstractTestNGSpringContextTests {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected MongoOperations mongoOps;

    @BeforeClass
    public void setUpBeforeClass() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    //~ DATA UTIL ======================================================================================================

    protected void insertStandardMenu() {
        mongoOps.insert(PersistenceMenuFixtures.standardMenu());
    }

    protected void clearMenusCollection() {
        mongoOps.remove(new Query(), DBCollection.MENUS);
    }

    protected void insertStandardRestaurant() {
        mongoOps.insert(PersistenceRestaurantFixtures.standardRestaurant());
    }

    protected void clearRestaurantsCollection() {
        mongoOps.remove(new Query(), DBCollection.RESTAURANTS);
    }
}
