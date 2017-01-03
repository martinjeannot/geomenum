/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.Request;

/**
 * Retrieve the information necessary to create a new menu item.
 *
 * @see {@link com.geomenum.web.domainmodel.menu.NewMenuItem}
 */
public class GetMenuItemCreationInfoRequest extends Request {

    private String menuId;

    public GetMenuItemCreationInfoRequest(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
