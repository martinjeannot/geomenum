/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.Request;
import com.geomenum.web.domainmodel.menu.NewSubmenu;

import java.util.Map;
import java.util.UUID;

/**
 * Add the given submenu within the parent submenu.
 */
public class AddSubmenuRequest extends Request {

    private String menuId;
    private UUID parentId;
    private Map<Object, Object> newSubmenuDTO;

    public AddSubmenuRequest(String menuId, String parentId, NewSubmenu newSubmenu) {
        this.menuId = menuId;
        this.parentId = UUID.fromString(parentId);
        this.newSubmenuDTO = newSubmenu.toMap();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public Map<Object, Object> getNewSubmenuDTO() {
        return newSubmenuDTO;
    }

    public void setNewSubmenuDTO(Map<Object, Object> newSubmenuDTO) {
        this.newSubmenuDTO = newSubmenuDTO;
    }
}
