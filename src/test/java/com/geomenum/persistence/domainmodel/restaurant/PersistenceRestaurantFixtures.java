/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.restaurant;

import com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures;
import com.geomenum.persistence.converters.StringToPersistenceIdConverter;
import org.bson.types.ObjectId;

import java.util.Map;

import static com.geomenum.common.Fixtures.localDateTime;

/**
 * Fixtures for {@link PersistenceRestaurant}.
 */
public class PersistenceRestaurantFixtures {

    public static ObjectId ID = StringToPersistenceIdConverter.convert(CoreRestaurantFixtures.ID);

    public static PersistenceRestaurant standardRestaurant() {
        return PersistenceRestaurant.of(newDto());
    }

    public static Map<Object, Object> newDto() {
        Map<Object, Object> restaurantDTO = CoreRestaurantFixtures.newDto();
        restaurantDTO.put("creationDate", localDateTime);
        return restaurantDTO;
    }

    public static Map<Object, Object> invalidDto() {
        Map<Object, Object> restaurantDto = newDto();
        restaurantDto.remove("enabled");
        return restaurantDto;
    }

    public static Map<Object, Object> updatedDto() {
        Map<Object, Object> restaurantDto = CoreRestaurantFixtures.updatedDto();
        restaurantDto.put("lastUpdateDate", localDateTime);
        return restaurantDto;
    }
}
