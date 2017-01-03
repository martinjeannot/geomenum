/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.restaurant;

import com.google.common.collect.Maps;
import org.testng.annotations.Test;

import javax.validation.ValidationException;
import java.util.Map;

import static com.geomenum.config.TestingLevels.UNIT;
import static com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurantFixtures.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(groups = {UNIT})
public class PersistenceRestaurantUnitTests {

    public void fromMap() {
        assertNotNull(PersistenceRestaurant.of(newDto()));
    }

    public void toMap() {
        Map<Object, Object> standardRestaurantMap = newDto();
        Map<Object, Object> geolocationDTO = Maps.newHashMap();
        geolocationDTO.put("x", 48.880691);
        geolocationDTO.put("y", 2.338668);
        standardRestaurantMap.put("geolocation", geolocationDTO);
        assertEquals(standardRestaurant().toMap(), standardRestaurantMap);
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        Map<Object, Object> dto = newDto();
        dto.remove("id");
        PersistenceRestaurant.of(dto);
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidDto() {
        PersistenceRestaurant.of(invalidDto());
    }
}
