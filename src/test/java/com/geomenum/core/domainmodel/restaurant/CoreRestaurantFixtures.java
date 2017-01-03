/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.restaurant;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;

import static com.geomenum.common.Fixtures.localDateTime;

/**
 * Fixtures for {@link CoreRestaurant}.
 */
public class CoreRestaurantFixtures {

    public static String ID = "ZmwByn39mWJrmMK0WWxpfptjvtg6KiKoYyLLbdjo3FQ";
    public static String MENU_ID = CoreMenuFixtures.ID;
    public static String UPDATED_MENU_ID = "yk2cEfmmgQNDxuWguKlTBzJJt3hbSIL1QaXPSl7cAys";

    public static CoreRestaurant standardRestaurant() {
        return CoreRestaurant.of(newDto());
    }

    public static CoreRestaurant standardUpdatedRestaurant() {
        return CoreRestaurant.of(updatedDto());
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
        restaurantDto.put("supportedLanguages", Lists.newArrayList(Locale.ENGLISH.toString(), Locale.FRENCH.toString()));
        restaurantDto.put("currency", "USD");
        Map<Object, Object> localizedDescriptionsDTO = Maps.newHashMap();
        localizedDescriptionsDTO.put(Locale.ENGLISH.toString(), "Welcome to our restaurant !");
        restaurantDto.put("localizedDescriptions", localizedDescriptionsDTO);
        restaurantDto.put("cuisine", "GLOBAL_INTERNATIONAL");
        // time-tracking fields
        restaurantDto.put("creationDate", null);
        restaurantDto.put("lastUpdateDate", null);
        restaurantDto.put("deletionDate", null);
        return restaurantDto;
    }

    public static Map<Object, Object> newDto() {
        Map<Object, Object> restaurantDto = newDtoWithoutId();
        restaurantDto.put("id", ID);
        return restaurantDto;
    }

    public static Map<Object, Object> invalidDto() {
        Map<Object, Object> restaurantDto = newDto();
        restaurantDto.remove("location");
        return restaurantDto;
    }

    public static Map<Object, Object> updatedDto() {
        Map<Object, Object> restaurantDto = newDto();
        restaurantDto.put("menuId", UPDATED_MENU_ID);
        restaurantDto.put("name", "Au Grand Chef");
        restaurantDto.put("enabled", false);
        restaurantDto.put("supportedLanguages", Lists.newArrayList(Locale.ENGLISH.toString(), Locale.GERMAN.toString(),
                Locale.ITALIAN.toString()));
        restaurantDto.put("currency", "EUR");
        Map<Object, Object> localizedDescriptionsDTO = Maps.newHashMap();
        localizedDescriptionsDTO.put(Locale.ENGLISH.toString(), "Welcome to our updated restaurant !");
        restaurantDto.put("localizedDescriptions", localizedDescriptionsDTO);
        restaurantDto.put("cuisineStyle", "JAPANESE");
        // time-tracking fields
        restaurantDto.put("creationDate", localDateTime);
        return restaurantDto;
    }
}
