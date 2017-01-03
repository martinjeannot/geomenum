/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.requestsresponses.menu;

import com.geomenum.r2d2.common.Request;

/**
 * Retrieve a menu by its id.
 */
public class GetMenuRequest extends Request {

    private String menuId;

    public GetMenuRequest(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
