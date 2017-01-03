/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.restaurant;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Fixtures for {@link WebRestaurant}
 */
public class WebRestaurantFixtures {

    public static String ID = "ZmwByn39mWJrmMK0WWxpfptjvtg6KiKoYyLLbdjo3FQ";
    public static String MENU_ID = "4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY";
    public static String UPDATED_MENU_ID = "yk2cEfmmgQNDxuWguKlTBzJJt3hbSIL1QaXPSl7cAys";

    public static WebRestaurant standardRestaurant() {
        return WebRestaurant.of(newDto());
    }

    public static WebRestaurant standardUpdatedRestaurant() {
        return WebRestaurant.of(updatedDto());
    }

    public static Map<Object, Object> newDtoWithoutId() {
        Map<Object, Object> restaurantDto = Maps.newHashMap();
        restaurantDto.put("menuId", MENU_ID);
        restaurantDto.put("name", "Au Petit Chef");
        restaurantDto.put("enabled", true);
        Map<Object, Object> locationDto = Maps.newHashMap();
        locationDto.put("latitude", 48.880691);
        locationDto.put("longitude", 2.338668);
        locationDto.put("address", "17 rue victor masse");
        locationDto.put("city", "Paris");
        locationDto.put("postalCode", "75009");
        locationDto.put("countryCode", "FR");
        locationDto.put("formattedAddress", "17 Rue Victor Massé, 75009 Paris, France");
        restaurantDto.put("location", locationDto);
        return restaurantDto;
    }

    public static Map<Object, Object> newDto() {
        Map<Object, Object> restaurantDto = newDtoWithoutId();
        restaurantDto.put("id", ID);
        return restaurantDto;
    }

    public static Map<Object, Object> invalidDto() {
        Map<Object, Object> restaurantDto = newDto();
        restaurantDto.remove("enabled");
        return restaurantDto;
    }

    public static Map<Object, Object> updatedDto() {
        Map<Object, Object> restaurant = Maps.newHashMap();
        restaurant.put("id", newDto().get("id")); // as this is an update, we keep the same id
        restaurant.put("menuId", UPDATED_MENU_ID);
        restaurant.put("name", "Au Grand Chef");
        restaurant.put("enabled", false);
        restaurant.put("location", newDto().get("location"));
        return restaurant;
    }
}
