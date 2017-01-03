/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.Request;

import java.util.UUID;

/**
 * Retrieve a submenu by its id.
 */
public class GetSubmenuRequest extends Request {

    private String menuId;
    private UUID submenuId;

    public GetSubmenuRequest(String menuId, String submenuId) {
        this.menuId = menuId;
        this.submenuId = UUID.fromString(submenuId);
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public UUID getSubmenuId() {
        return submenuId;
    }

    public void setSubmenuId(UUID submenuId) {
        this.submenuId = submenuId;
    }
}
