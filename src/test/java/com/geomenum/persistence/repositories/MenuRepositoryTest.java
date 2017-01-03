/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.repositories;

import com.geomenum.config.PersistenceTestConfiguration;
import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.menu.PersistenceMenu;
import com.geomenum.persistence.repositories.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.geomenum.config.TestingLevels.INTEGRATION;
import static com.geomenum.persistence.domainmodel.menu.PersistenceMenuFixtures.standardMenu;
import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = {PersistenceTestConfiguration.class})
@Test(groups = {INTEGRATION})
public class MenuRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MongoOperations mongoOps;

    @BeforeMethod
    public void setUp() {
        mongoOps.remove(new Query(), DBCollection.MENUS);
    }

    @AfterMethod
    public void tearDown() {
        mongoOps.remove(new Query(), DBCollection.MENUS);
    }

    public void save() {
        menuRepository.save(standardMenu());

        assertEquals(menuRepository.findAll().size(), 1);
    }

    public void find() {
        PersistenceMenu menu = standardMenu();
        mongoOps.insert(menu);
        PersistenceMenu retrievedMenu = menuRepository.findOne(menu.getId());

        assertEquals(retrievedMenu, menu);
        assertEquals(retrievedMenu.hashCode(), menu.hashCode());
        assertEquals(retrievedMenu.toString(), menu.toString());
    }
}
