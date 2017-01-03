/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.Request;
import com.geomenum.web.domainmodel.menu.NewMenuItem;

import java.util.Map;
import java.util.UUID;

/**
 * Add the given menu item within the parent submenu.
 */
public class AddMenuItemRequest extends Request {

    private String menuId;
    private UUID parentId;
    private Map<Object, Object> newMenuItemDTO;

    public AddMenuItemRequest(String menuId, String parentId, NewMenuItem newMenuItem) {
        this.menuId = menuId;
        this.parentId = UUID.fromString(parentId);
        this.newMenuItemDTO = newMenuItem.toMap();
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

    public Map<Object, Object> getNewMenuItemDTO() {
        return newMenuItemDTO;
    }

    public void setNewMenuItemDTO(Map<Object, Object> newMenuItemDTO) {
        this.newMenuItemDTO = newMenuItemDTO;
    }
}
