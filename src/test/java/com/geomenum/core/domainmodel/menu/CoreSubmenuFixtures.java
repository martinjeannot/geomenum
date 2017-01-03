/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNodeFixtures;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Fixtures for {@link CoreSubmenu}.
 */
public class CoreSubmenuFixtures {

    public static UUID ID = MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID;

    public static CoreSubmenu standardSubmenu() {
        return CoreSubmenu.of(newDto());
    }

    public static Map<Object, Object> newDtoWithoutId() {
        Map<Object, Object> submenuDto = Maps.newHashMap();
        submenuDto.put("localizedNames", ImmutableMap.of(Locale.ENGLISH, "Salads"));
        submenuDto.put("enabled", true);
        return submenuDto;
    }

    public static Map<Object, Object> newDto() {
        return newDto(ID);
    }

    public static Map<Object, Object> newDto(UUID id) {
        Map<Object, Object> submenuDto = newDtoWithoutId();
        submenuDto.put("id", id);
        return submenuDto;
    }

    public static Map<Object, Object> invalidDto() {
        Map<Object, Object> submenuDto = newDto();
        submenuDto.remove("localizedNames");
        return submenuDto;
    }

    public static Map<Object, Object> updatedDto() {
        Map<Object, Object> submenuDto = Maps.newHashMap();
        submenuDto.put("id", newDto().get("id")); // as this is an update, we keep the same id
        submenuDto.put("localizedNames", ImmutableMap.of(Locale.ENGLISH, "Salads 2"));
        submenuDto.put("enabled", false);
        return submenuDto;
    }
}
