/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.system;

import com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;

import static com.geomenum.common.Fixtures.localDateTime;

/**
 * Fixtures for {@link CoreUser}.
 */
public class CoreUserFixtures {

    public static String ID = "UT33f4kqllTyP6941W-wtN4RDlHhiC3FSO_Zdo9VG_E";
    public static String RESTAURANT_ID = CoreRestaurantFixtures.ID;
    public static String UPDATED_RESTAURANT_ID = "agYpAoBS52Tq_sWm5rArwSODslO_ZBjBlH7EZPt8-LI";

    public static CoreUser standardUser() {
        return CoreUser.of(newDTO());
    }

    public static CoreUser standardUpdatedUser() {
        return CoreUser.of(updatedDTO());
    }

    public static Map<Object, Object> newDTOWithoutId() {
        Map<Object, Object> userDto = Maps.newHashMap();
        userDto.put("username", "morgan.sullivan@gmail.com");
        userDto.put("password", "Passw0rd");
        userDto.put("enabled", true);
        userDto.put("accountNonExpired", true);
        userDto.put("credentialsNonExpired", true);
        userDto.put("accountNonLocked", true);
        userDto.put("authorities", Lists.newArrayList("ROLE_ADMIN"));
        userDto.put("restaurantId", RESTAURANT_ID);
        userDto.put("firstName", "Morgan");
        userDto.put("lastName", "Sullivan");
        userDto.put("language", Locale.ENGLISH.toString());
        // time-tracking fields
        userDto.put("creationDate", null);
        userDto.put("lastUpdateDate", null);
        userDto.put("deletionDate", null);
        return userDto;
    }

    public static Map<Object, Object> newDTO() {
        Map<Object, Object> userDto = newDTOWithoutId();
        userDto.put("id", ID);
        return userDto;
    }

    public static Map<Object, Object> invalidDTO() {
        Map<Object, Object> userDto = newDTO();
        userDto.remove("lastName");
        return userDto;
    }

    public static Map<Object, Object> updatedDTO() {
        Map<Object, Object> userDto = newDTO();
        userDto.put("username", "jack.thursby@outlook.com");
        userDto.put("password", "crYpt3Dpassw0rd");
        userDto.put("enabled", false);
        userDto.put("accountNonExpired", false);
        userDto.put("credentialsNonExpired", false);
        userDto.put("accountNonLocked", false);
        userDto.put("authorities", Lists.newArrayList("ROLE_USER"));
        userDto.put("restaurantId", UPDATED_RESTAURANT_ID);
        userDto.put("firstName", "Jack");
        userDto.put("lastName", "Thursby");
        // time-tracking fields
        userDto.put("creationDate", localDateTime);
        return userDto;
    }

    public static Map<Object, Object> registrationFormDTO() {
        Map<Object, Object> registrationFormDTO = Maps.newHashMap();

        // TODO with data from Web fixtures !

        return registrationFormDTO;
    }
}
