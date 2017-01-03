/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common;

import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.menu.PersistenceMenu;
import com.geomenum.persistence.domainmodel.menu.PersistenceMenuFixtures;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurantFixtures;
import com.geomenum.persistence.domainmodel.system.PersistenceUser;
import com.geomenum.persistence.domainmodel.system.PersistenceUserFixtures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static com.geomenum.config.TestingLevels.INTEGRATION;

/**
 * Abstract class implemented by any integration-level testing class.<br/>
 * {@code Configuration context} classes can be added at any test class level if those provided by upper classes
 * are not sufficient to run all the tests.
 */
@Test(groups = {INTEGRATION})
public class AbstractIntegrationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private MongoOperations mongoOps;

    //~ PERSISTENCE UTIL ===============================================================================================

    protected void insert(Object object) {
        mongoOps.insert(object);
    }

    protected MongoOperations getMongoOperations() {
        return mongoOps;
    }

    // USER

    protected void insertStandardUser() {
        mongoOps.insert(PersistenceUserFixtures.standardUser());
    }

    protected int getUserCollectionSize() {
        return mongoOps.findAll(PersistenceUser.class).size();
    }

    protected void clearUserCollection() {
        mongoOps.remove(new Query(), DBCollection.USERS);
    }

    // RESTAURANT

    protected void insertStandardRestaurant() {
        mongoOps.insert(PersistenceRestaurantFixtures.standardRestaurant());
    }

    protected int getRestaurantCollectionSize() {
        return mongoOps.findAll(PersistenceRestaurant.class).size();
    }

    protected void clearRestaurantCollection() {
        mongoOps.remove(new Query(), DBCollection.RESTAURANTS);
    }

    // MENU

    protected void insertStandardMenu() {
        mongoOps.insert(PersistenceMenuFixtures.standardMenu());
    }

    protected int getMenuCollectionSize() {
        return mongoOps.findAll(PersistenceMenu.class).size();
    }

    protected void clearMenuCollection() {
        mongoOps.remove(new Query(), DBCollection.MENUS);
    }
}
