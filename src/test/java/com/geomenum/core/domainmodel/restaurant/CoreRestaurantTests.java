/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.restaurant;

import com.geomenum.core.domainmodel.AbstractCoreDomainModelUnitTests;
import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures.*;
import static org.testng.Assert.*;

public class CoreRestaurantTests extends AbstractCoreDomainModelUnitTests {

    public void fromMap() {
        assertNotNull(CoreRestaurant.of(newDto()));
    }

    public void toMap() {
        assertEquals(standardRestaurant().toMap(), newDto());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        CoreRestaurant.of(newDtoWithoutId());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.core.domainmodel.restaurant.CoreRestaurant\\] Validation failed :\n" +
                    "location : may not be null\n")
    public void createWithInvalidDto() {
        assertNull(CoreRestaurant.of(invalidDto()));
    }

    /*public void createNewRestaurantFromRegistrationForm() {
        assertNotNull(CoreRestaurant.createNewRestaurantFromRegistrationForm(
                CoreUserFixtures.registrationFormDTO(),
                PERSISTENCE_ID_GENERATOR));
    }*/
}
