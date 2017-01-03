/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.Request;

/**
 * Retrieve the information necessary to create a new submenu.
 *
 * @see {@link com.geomenum.web.domainmodel.menu.NewSubmenu}
 */
public class GetSubmenuCreationInfoRequest extends Request {

    private String menuId;

    public GetSubmenuCreationInfoRequest(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
