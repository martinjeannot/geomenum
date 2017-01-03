/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.repositories;

import com.geomenum.config.PersistenceTestConfiguration;
import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.system.PersistenceUser;
import com.geomenum.persistence.repositories.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.geomenum.config.TestingLevels.INTEGRATION;
import static com.geomenum.persistence.domainmodel.system.PersistenceUserFixtures.standardUser;
import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = {PersistenceTestConfiguration.class})
@Test(groups = {INTEGRATION})
public class UserRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoOperations mongoOps;

    @BeforeMethod
    public void setUp() {
        mongoOps.remove(new Query(), DBCollection.USERS);
    }

    @AfterMethod
    public void tearDown() {
        mongoOps.remove(new Query(), DBCollection.USERS);
    }

    public void save() {
        userRepository.save(standardUser());

        assertEquals(userRepository.findAll().size(), 1);
    }

    public void find() {
        PersistenceUser user = standardUser();
        mongoOps.insert(user);
        PersistenceUser retrievedUser = userRepository.findOne(user.getId());

        assertEquals(retrievedUser, user);
        assertEquals(retrievedUser.hashCode(), user.hashCode());
        assertEquals(retrievedUser.toString(), user.toString());
    }

    public void findByUsername() {
        mongoOps.insert(standardUser());

        List<PersistenceUser> results = userRepository.findByUsername(standardUser().getUsername());
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), standardUser());
    }

    public void findUnknownUserByUsername() {
        mongoOps.insert(standardUser());

        List<PersistenceUser> results = userRepository.findByUsername("morpheus@nebuchadnezzar.com");
        assertEquals(results.size(), 0);
    }
}
