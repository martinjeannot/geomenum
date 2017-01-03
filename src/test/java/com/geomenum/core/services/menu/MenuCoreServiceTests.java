/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.core.services.AbstractCoreServiceIntegrationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.geomenum.core.domainmodel.menu.CoreMenuFixtures.standardMenu;
import static com.geomenum.core.domainmodel.menu.CoreMenuFixtures.standardUpdatedMenu;
import static org.testng.Assert.*;

public class MenuCoreServiceTests extends AbstractCoreServiceIntegrationTests {

    @Autowired
    private MenuCoreService menuCoreService;

    @BeforeMethod
    public void setUp() {
        clearMenuCollection();
    }

    //~ create =========================================================================================================

    public void create() {
        CoreMenu createdObject = menuCoreService.create(standardMenu());

        assertEquals(getMenuCollectionSize(), 1);
        assertNotNull(createdObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot insert an existing object into DB \\(id already found\\)")
    public void createExistingObject() {
        insertStandardMenu();

        menuCoreService.create(standardMenu());
    }

    //~ findById =======================================================================================================

    public void findById() {
        insertStandardMenu();

        CoreMenu retrievedObject = menuCoreService.findById(CoreMenuFixtures.ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertNotNull(retrievedObject.getId(), CoreMenuFixtures.ID);
    }

    public void findByIdWithUnknownObject() {
        CoreMenu retrievedObject = menuCoreService.findById(CoreMenuFixtures.ID);

        assertEquals(getMenuCollectionSize(), 0);
        assertNull(retrievedObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Invalid id : ")
    public void findByIdWithInvalidId() {
        menuCoreService.findById("");
    }

    //~ update =========================================================================================================

    public void update() {
        insertStandardMenu();
        CoreMenu objectToUpdate = standardMenu();
        assertTrue(objectToUpdate.getEnabled());

        CoreMenu updatedObject = menuCoreService.update(standardUpdatedMenu());

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(updatedObject.getEnabled());
    }

    //~ delete =========================================================================================================

    public void delete() {
        insertStandardMenu();

        CoreMenu deletedObject = menuCoreService.delete(CoreMenuFixtures.ID, false);

        assertEquals(getMenuCollectionSize(), 0);
        assertNotNull(deletedObject.getId(), CoreMenuFixtures.ID);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Invalid id : ")
    public void deleteWithInvalidId() {
        menuCoreService.delete("", false);
    }
}
