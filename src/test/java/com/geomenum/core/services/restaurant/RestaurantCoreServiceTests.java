/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.restaurant;

import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures;
import com.geomenum.core.services.AbstractCoreServiceIntegrationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures.standardRestaurant;
import static com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures.standardUpdatedRestaurant;
import static org.testng.Assert.*;

public class RestaurantCoreServiceTests extends AbstractCoreServiceIntegrationTests {

    @Autowired
    private RestaurantCoreService restaurantCoreService;

    @BeforeMethod
    public void setUp() {
        clearRestaurantCollection();
    }

    //~ create =========================================================================================================

    public void create() {
        CoreRestaurant createdObject = restaurantCoreService.create(standardRestaurant());

        assertEquals(getRestaurantCollectionSize(), 1);
        assertNotNull(createdObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot insert an existing object into DB \\(id already found\\)")
    public void createExistingObject() {
        insertStandardRestaurant();

        restaurantCoreService.create(standardRestaurant());
    }

    //~ findById =======================================================================================================

    public void findById() {
        insertStandardRestaurant();

        CoreRestaurant retrievedObject = restaurantCoreService.findById(CoreRestaurantFixtures.ID);

        assertEquals(getRestaurantCollectionSize(), 1);
        assertEquals(retrievedObject.getId(), CoreRestaurantFixtures.ID);
    }

    public void findByIdWithUnknownObject() {
        CoreRestaurant retrievedObject = restaurantCoreService.findById(CoreRestaurantFixtures.ID);

        assertEquals(getRestaurantCollectionSize(), 0);
        assertNull(retrievedObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Invalid id : ")
    public void findByIdWithInvalidId() {
        restaurantCoreService.findById("");
    }

    //~ findByLocationNear =============================================================================================

    public void findByLocationNear() {
        assertTrue(false);
    }

    //~ update =========================================================================================================

    public void update() {
        insertStandardRestaurant();
        CoreRestaurant objectToUpdate = standardRestaurant();
        assertTrue(objectToUpdate.getEnabled());

        CoreRestaurant updatedObject = restaurantCoreService.update(standardUpdatedRestaurant());

        assertEquals(getRestaurantCollectionSize(), 1);
        assertFalse(updatedObject.getEnabled());
    }

    //~ delete =========================================================================================================

    public void delete() {
        insertStandardRestaurant();

        CoreRestaurant deletedObject = restaurantCoreService.delete(CoreRestaurantFixtures.ID, false);

        assertEquals(getRestaurantCollectionSize(), 0);
        assertEquals(deletedObject.getId(), CoreRestaurantFixtures.ID);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Invalid id : ")
    public void deleteWithInvalidId() {
        restaurantCoreService.delete("", false);
    }
}
