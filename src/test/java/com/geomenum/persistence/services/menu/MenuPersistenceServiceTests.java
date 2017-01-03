/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.menu;

import com.geomenum.persistence.domainmodel.menu.PersistenceMenu;
import com.geomenum.persistence.domainmodel.menu.PersistenceMenuFixtures;
import com.geomenum.persistence.services.AbstractPersistenceServiceIntegrationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.persistence.domainmodel.menu.PersistenceMenuFixtures.*;
import static org.testng.Assert.*;

public class MenuPersistenceServiceTests extends AbstractPersistenceServiceIntegrationTests {

    @Autowired
    private MenuPersistenceService menuPersistenceService;

    @BeforeMethod
    public void setUp() {
        clearMenuCollection();
    }

    //~ create =========================================================================================================

    public void create() {
        PersistenceMenu createdObject = menuPersistenceService.create(newDto());

        assertEquals(getMenuCollectionSize(), 1);
        assertNotNull(createdObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot insert an existing object into DB \\(id already found\\)")
    public void createExistingObject() {
        insertStandardMenu();

        menuPersistenceService.create(newDto());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.menu.PersistenceMenu\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidObject() {
        menuPersistenceService.create(invalidDto());
    }

    //~ findById =======================================================================================================

    public void findById() {
        insertStandardMenu();

        PersistenceMenu retrievedObject = menuPersistenceService.findById(PersistenceMenuFixtures.ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertEquals(retrievedObject.getId(), PersistenceMenuFixtures.ID);
    }

    public void findByIdWithUnknownObject() {
        PersistenceMenu retrievedObject = menuPersistenceService.findById(PersistenceMenuFixtures.ID);

        assertEquals(getMenuCollectionSize(), 0);
        assertNull(retrievedObject);
    }

    //~ update =========================================================================================================

    public void update() {
        PersistenceMenu objectToUpdate = standardMenu();
        assertTrue(objectToUpdate.getEnabled());
        insert(objectToUpdate);

        PersistenceMenu updatedObject = menuPersistenceService.update(updatedDto());

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(updatedObject.getEnabled());
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot update a non-existing object into DB \\(id not found\\)")
    public void updateWithUnknownObject() {
        menuPersistenceService.update(updatedDto());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.menu.PersistenceMenu\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void updateWithInvalidObject() {
        insertStandardMenu();

        menuPersistenceService.update(invalidDto());
    }

    //~ delete =========================================================================================================

    public void delete() {
        insertStandardMenu();

        PersistenceMenu deletedObject = menuPersistenceService.delete(PersistenceMenuFixtures.ID);

        assertEquals(getMenuCollectionSize(), 0);
        assertEquals(deletedObject.getId(), PersistenceMenuFixtures.ID);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot delete a non-existing object into DB \\(object not found\\)")
    public void deleteWithUnknownObject() {
        menuPersistenceService.delete(PersistenceMenuFixtures.ID);
    }
}
