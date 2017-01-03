/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNodeFixtures;
import com.geomenum.core.domainmodel.menu.CoreSubmenuFixtures;

import java.util.Map;
import java.util.UUID;

/**
 * Fixtures for {@link WebSubmenu}
 */
public class WebSubmenuFixtures {

    public static UUID ID = MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID;

    public static WebSubmenu standardSubmenu() {
        return WebSubmenu.of(newDTO());
    }

    public static WebSubmenu standardUpdatedSubmenu() {
        return WebSubmenu.of(updatedDto());
    }

    public static Map<Object, Object> newDTOWithoutId() {
        return CoreSubmenuFixtures.newDtoWithoutId();
    }

    public static Map<Object, Object> newDTO() {
        Map<Object, Object> submenuDTO = newDTOWithoutId();
        submenuDTO.put("id", ID);
        return submenuDTO;
    }

    public static Map<Object, Object> invalidDTO() {
        Map<Object, Object> submenuDTO = newDTO();
        submenuDTO.remove("enabled");
        return submenuDTO;
    }

    public static Map<Object, Object> updatedDto() {
        Map<Object, Object> submenuDTO = CoreSubmenuFixtures.updatedDto();
        submenuDTO.put("id", ID); // as this is an update, we keep the same id
        return submenuDTO;
    }
}
