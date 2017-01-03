/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.Request;

import java.util.UUID;

/**
 * Remove a menu item from the menu.
 */
public class DeleteMenuItemRequest extends Request {

    private String menuId;
    private UUID menuItemId;

    public DeleteMenuItemRequest(String menuId, String menuItemId) {
        this.menuId = menuId;
        this.menuItemId = UUID.fromString(menuItemId);
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public UUID getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(UUID menuItemId) {
        this.menuItemId = menuItemId;
    }
}
