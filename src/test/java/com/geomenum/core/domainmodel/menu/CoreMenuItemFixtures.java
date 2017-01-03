/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNodeFixtures;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Fixtures for {@link CoreMenuItem}.
 */
public class CoreMenuItemFixtures {

    public static UUID ID = MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID;

    public static CoreMenuItem standardMenuItem() {
        return CoreMenuItem.of(newDTO());
    }

    public static Map<Object, Object> newDTOWithoutId() {
        Map<Object, Object> menuItemDto = Maps.newHashMap();
        menuItemDto.put("localizedNames", ImmutableMap.of(Locale.ENGLISH, "Caesar Salad"));
        menuItemDto.put("enabled", true);
        menuItemDto.put("amount", new BigDecimal("9.79"));
        menuItemDto.put("currency", Currency.getInstance("USD"));
        menuItemDto.put("localizedDescriptions", ImmutableMap.of(Locale.ENGLISH, "A Caesar salad is a salad of romaine lettuce " +
                "and croutons dressed with parmesan cheese, lemon juice, olive oil, egg, Worcestershire sauce, garlic, and black pepper."));
        return menuItemDto;
    }

    public static Map<Object, Object> newDTO() {
        return newDTO(ID);
    }

    public static Map<Object, Object> newDTO(UUID id) {
        Map<Object, Object> menuItemDto = newDTOWithoutId();
        menuItemDto.put("id", id);
        return menuItemDto;
    }

    public static Map<Object, Object> invalidDTO() {
        Map<Object, Object> menuItemDto = newDTO();
        menuItemDto.remove("localizedNames");
        return menuItemDto;
    }

    public static Map<Object, Object> updatedDTO() {
        Map<Object, Object> menuItemDto = Maps.newHashMap();
        menuItemDto.put("id", newDTO().get("id")); // as this is an update, we keep the same id
        menuItemDto.put("localizedNames", ImmutableMap.of(Locale.ENGLISH, "Chef Salad"));
        menuItemDto.put("enabled", false);
        menuItemDto.put("amount", new BigDecimal("7.99"));
        menuItemDto.put("currency", Currency.getInstance("EUR"));
        menuItemDto.put("localizedDescriptions", ImmutableMap.of(Locale.ENGLISH, " A Chef salad is a salad made of hard-boiled eggs, " +
                "strips of ham or another cold cut (such as roast beef, turkey, or chicken), croutons, tomatoes, cucumbers, " +
                "and cheese (often crumbled), all placed upon a bed of tossed lettuce or other leaf vegetables."));
        return menuItemDto;
    }
}
