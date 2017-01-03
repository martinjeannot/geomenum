/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.Request;

import java.util.UUID;

/**
 * Retrieve a menu item by its id.
 */
public class GetMenuItemRequest extends Request {

    private String menuId;
    private UUID menuItemId;

    public GetMenuItemRequest(String menuId, String menuItemId) {
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
