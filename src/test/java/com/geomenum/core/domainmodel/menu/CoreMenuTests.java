/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.geomenum.core.domainmodel.AbstractCoreDomainModelUnitTests;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures;
import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.core.domainmodel.menu.CoreMenuFixtures.*;
import static org.testng.Assert.*;

public class CoreMenuTests extends AbstractCoreDomainModelUnitTests {

    public void fromMap() {
        assertNotNull(CoreMenu.of(newDto()));
    }

    public void toMap() {
        assertEquals(standardMenu().toMap(), newDto());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        CoreMenu.of(newDtoWithoutId());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.core.domainmodel.menu.CoreMenu\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidDto() {
        assertNull(CoreMenu.of(invalidDto()));
    }

    public void createNewMenu() {
        CoreRestaurant restaurant = CoreRestaurantFixtures.standardRestaurant();
        assertNotNull(CoreMenu.createNewMenu(restaurant, MESSAGE_SOURCE));
    }
}
