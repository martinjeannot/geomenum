/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.Request;
import com.geomenum.web.domainmodel.menu.WebMenuItem;

import java.util.Map;

/**
 * Save (= update) a menu item.
 */
public class SaveMenuItemRequest extends Request {

    private String menuId;
    private Map<Object, Object> menuItemDTO;

    public SaveMenuItemRequest(String menuId, WebMenuItem menuItem) {
        this.menuId = menuId;
        this.menuItemDTO = menuItem.toMap();
    }

    public void setMenuItem(WebMenuItem menuItem) {
        this.menuItemDTO = menuItem.toMap();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Map<Object, Object> getMenuItemDTO() {
        return menuItemDTO;
    }

    public void setMenuItemDTO(Map<Object, Object> menuItemDTO) {
        this.menuItemDTO = menuItemDTO;
    }
}
