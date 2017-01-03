/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.restaurant;

import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.config.TestingLevels.UNIT;
import static com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures.*;
import static org.testng.Assert.*;

@Test(groups = {UNIT})
public class WebRestaurantTests {

    public void create() {
        assertNotNull(WebRestaurant.of(newDto()));
    }

    public void toMap() {
        assertEquals(standardRestaurant().toMap(), newDto());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        WebRestaurant.of(newDtoWithoutId());
    }

    public void createWithMissingField() {
        assertNull(WebRestaurant.of(invalidDto()));
    }
}
