/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNodeFixtures;
import com.geomenum.core.domainmodel.menu.CoreMenuItemFixtures;

import java.util.Map;
import java.util.UUID;

/**
 * Fixtures for {@link WebMenuItem}
 */
public class WebMenuItemFixtures {

    public static UUID ID = MenuNodeFixtures.LEAF_NODE4_DEPTH2_ID;

    public static WebMenuItem standardMenuItem() {
        return WebMenuItem.of(newDTO());
    }

    public static WebMenuItem standardUpdatedMenuItem() {
        return WebMenuItem.of(updatedDTO());
    }

    public static Map<Object, Object> newDTOWithoutId() {
        return CoreMenuItemFixtures.newDTOWithoutId();
    }

    public static Map<Object, Object> newDTO() {
        Map<Object, Object> menuItemDTO = newDTOWithoutId();
        menuItemDTO.put("id", ID);
        return menuItemDTO;
    }

    public static Map<Object, Object> invalidDTO() {
        Map<Object, Object> menuItemDTO = newDTO();
        menuItemDTO.remove("enabled");
        return menuItemDTO;
    }

    public static Map<Object, Object> updatedDTO() {
        Map<Object, Object> menuItemDTO = CoreMenuItemFixtures.updatedDTO();
        menuItemDTO.put("id", ID); // as this is an update, we keep the same id
        return menuItemDTO;
    }
}
