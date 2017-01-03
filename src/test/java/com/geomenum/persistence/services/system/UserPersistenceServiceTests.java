/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.system;

import com.geomenum.persistence.domainmodel.system.PersistenceUser;
import com.geomenum.persistence.domainmodel.system.PersistenceUserFixtures;
import com.geomenum.persistence.services.AbstractPersistenceServiceIntegrationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.persistence.domainmodel.system.PersistenceUserFixtures.*;
import static org.testng.Assert.*;

public class UserPersistenceServiceTests extends AbstractPersistenceServiceIntegrationTests {

    @Autowired
    private UserPersistenceService userPersistenceService;

    @BeforeMethod
    public void setUp() {
        clearUserCollection();
    }

    //~ create =========================================================================================================

    public void create() {
        PersistenceUser createdObject = userPersistenceService.create(newDto());

        assertEquals(getUserCollectionSize(), 1);
        assertNotNull(createdObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot insert an existing object into DB \\(id already found\\)")
    public void createExistingObject() {
        insertStandardUser();

        userPersistenceService.create(newDto());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.system.PersistenceUser\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidObject() {
        userPersistenceService.create(invalidDto());
    }

    //~ findById =======================================================================================================

    public void findById() {
        insertStandardUser();

        PersistenceUser retrievedObject = userPersistenceService.findById(PersistenceUserFixtures.ID);

        assertEquals(getUserCollectionSize(), 1);
        assertEquals(retrievedObject.getId(), PersistenceUserFixtures.ID);
    }

    public void findByIdWithUnknownObject() {
        PersistenceUser retrievedObject = userPersistenceService.findById(PersistenceUserFixtures.ID);

        assertEquals(getUserCollectionSize(), 0);
        assertNull(retrievedObject);
    }

    //~ update =========================================================================================================

    public void update() {
        PersistenceUser objectToUpdate = standardUser();
        assertTrue(objectToUpdate.getEnabled());
        insert(objectToUpdate);

        PersistenceUser updatedObject = userPersistenceService.update(updatedDto());

        assertEquals(getUserCollectionSize(), 1);
        assertFalse(updatedObject.getEnabled());
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot update a non-existing object into DB \\(id not found\\)")
    public void updateWithUnknownObject() {
        userPersistenceService.update(updatedDto());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.system.PersistenceUser\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void updateWithInvalidObject() {
        insertStandardUser();

        userPersistenceService.update(invalidDto());
    }

    //~ delete =========================================================================================================

    public void delete() {
        insertStandardUser();

        PersistenceUser deletedObject = userPersistenceService.delete(PersistenceUserFixtures.ID);

        assertEquals(getUserCollectionSize(), 0);
        assertEquals(deletedObject.getId(), PersistenceUserFixtures.ID);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot delete a non-existing object into DB \\(object not found\\)")
    public void deleteWithUnknownObject() {
        userPersistenceService.delete(PersistenceUserFixtures.ID);
    }
}
