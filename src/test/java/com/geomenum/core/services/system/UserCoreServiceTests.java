/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system;

import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.domainmodel.system.CoreUserFixtures;
import com.geomenum.core.services.AbstractCoreServiceIntegrationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.geomenum.core.domainmodel.system.CoreUserFixtures.standardUpdatedUser;
import static com.geomenum.core.domainmodel.system.CoreUserFixtures.standardUser;
import static org.testng.Assert.*;

public class UserCoreServiceTests extends AbstractCoreServiceIntegrationTests {

    @Autowired
    private UserCoreService userCoreService;

    @BeforeMethod
    public void setUp() {
        clearUserCollection();
    }

    //~ create =========================================================================================================

    public void create() {
        CoreUser createdObject = userCoreService.create(standardUser());

        assertEquals(getUserCollectionSize(), 1);
        assertNotNull(createdObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot insert an existing object into DB \\(id already found\\)")
    public void createExistingObject() {
        insertStandardUser();

        userCoreService.create(standardUser());
    }

    //~ findById =======================================================================================================

    public void findById() {
        insertStandardUser();

        CoreUser retrievedObject = userCoreService.findById(CoreUserFixtures.ID);

        assertEquals(getUserCollectionSize(), 1);
        assertEquals(retrievedObject.getId(), CoreUserFixtures.ID);
    }

    public void findByIdWithUnknownObject() {
        CoreUser retrievedObject = userCoreService.findById(CoreUserFixtures.ID);

        assertEquals(getUserCollectionSize(), 0);
        assertNull(retrievedObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Invalid id : ")
    public void findByIdWithInvalidId() {
        userCoreService.findById("");
    }

    //~ findByUsername =================================================================================================

    public void findByUsername() {
        insertStandardUser();

        CoreUser retrievedObject = userCoreService.findUserByUsername(standardUser().getUsername());

        assertEquals(getUserCollectionSize(), 1);
        assertEquals(retrievedObject.getUsername(), standardUser().getUsername());
    }

    public void findByUsernameWithUnknownUser() {
        CoreUser retrievedObject = userCoreService.findUserByUsername(standardUser().getUsername());

        assertEquals(getUserCollectionSize(), 0);
        assertNull(retrievedObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot find user with null or empty username")
    public void findByUsernameWithInvalidUsername() {
        userCoreService.findUserByUsername("");
    }

    //~ update =========================================================================================================

    public void update() {
        CoreUser objectToUpdate = standardUser();
        assertTrue(objectToUpdate.isEnabled());
        insertStandardUser();

        CoreUser updatedObject = userCoreService.update(standardUpdatedUser());

        assertEquals(getUserCollectionSize(), 1);
        assertFalse(updatedObject.isEnabled());
    }

    //~ delete =========================================================================================================

    public void delete() {
        insertStandardUser();

        CoreUser deletedObject = userCoreService.delete(CoreUserFixtures.ID, false);

        assertEquals(getUserCollectionSize(), 0);
        assertEquals(deletedObject.getId(), CoreUserFixtures.ID);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Invalid id : ")
    public void deleteWithInvalidId() {
        userCoreService.delete("", false);
    }
}
