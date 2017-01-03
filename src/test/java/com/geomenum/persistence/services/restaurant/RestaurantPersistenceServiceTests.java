/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.restaurant;

import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurantFixtures;
import com.geomenum.persistence.services.AbstractPersistenceServiceIntegrationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurantFixtures.*;
import static org.testng.Assert.*;

public class RestaurantPersistenceServiceTests extends AbstractPersistenceServiceIntegrationTests {

    @Autowired
    private RestaurantPersistenceService restaurantPersistenceService;

    @BeforeMethod
    public void setUp() {
        clearRestaurantCollection();
    }

    //~ create =========================================================================================================

    public void create() {
        PersistenceRestaurant createdObject = restaurantPersistenceService.create(newDto());

        assertEquals(getRestaurantCollectionSize(), 1);
        assertNotNull(createdObject);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot insert an existing object into DB \\(id already found\\)")
    public void createExistingObject() {
        insertStandardRestaurant();

        restaurantPersistenceService.create(newDto());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidObject() {
        restaurantPersistenceService.create(invalidDto());
    }

    //~ findById =======================================================================================================

    public void findById() {
        insertStandardRestaurant();

        PersistenceRestaurant retrievedObject = restaurantPersistenceService.findById(PersistenceRestaurantFixtures.ID);

        assertEquals(getRestaurantCollectionSize(), 1);
        assertEquals(retrievedObject.getId(), PersistenceRestaurantFixtures.ID);
    }

    public void findByIdWithUnknownObject() {
        PersistenceRestaurant retrievedObject = restaurantPersistenceService.findById(PersistenceRestaurantFixtures.ID);

        assertEquals(getRestaurantCollectionSize(), 0);
        assertNull(retrievedObject);
    }

    //~ findByLocationNear =============================================================================================

    public void findByLocationNear() {
        assertTrue(false);
    }

    //~ findByFormattedAddress =========================================================================================

    public void findByFormattedAddress() {
        insertStandardRestaurant();

        PersistenceRestaurant retrievedObject = restaurantPersistenceService.findByFormattedAddress("17 Rue Victor Massé, 75009 Paris, France");

        assertEquals(getRestaurantCollectionSize(), 1);
        assertNotNull(retrievedObject);
    }

    public void findMissingRestaurantByFormattedAddress() {
        PersistenceRestaurant retrievedObject = restaurantPersistenceService.findByFormattedAddress("17 Rue Victor Massé, 75009 Paris, France");

        assertEquals(getRestaurantCollectionSize(), 0);
        assertNull(retrievedObject);
    }

    //~ update =========================================================================================================

    public void update() {
        PersistenceRestaurant objectToUpdate = standardRestaurant();
        assertTrue(objectToUpdate.getEnabled());
        insert(objectToUpdate);

        PersistenceRestaurant updatedObject = restaurantPersistenceService.update(updatedDto());

        assertEquals(getRestaurantCollectionSize(), 1);
        assertFalse(updatedObject.getEnabled());
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot update a non-existing object into DB \\(id not found\\)")
    public void updateWithUnknownObject() {
        restaurantPersistenceService.update(updatedDto());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void updateWithInvalidObject() {
        insertStandardRestaurant();

        restaurantPersistenceService.update(invalidDto());
    }

    //~ delete =========================================================================================================

    public void delete() {
        insertStandardRestaurant();

        PersistenceRestaurant deletedObject = restaurantPersistenceService.delete(PersistenceRestaurantFixtures.ID);

        assertEquals(getRestaurantCollectionSize(), 0);
        assertEquals(deletedObject.getId(), PersistenceRestaurantFixtures.ID);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Cannot delete a non-existing object into DB \\(object not found\\)")
    public void deleteWithUnknownObject() {
        restaurantPersistenceService.delete(PersistenceRestaurantFixtures.ID);
    }
}
