/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.Request;
import com.geomenum.web.domainmodel.menu.WebSubmenu;

import java.util.Map;

/**
 * Save (= update) a submenu.
 */
public class SaveSubmenuRequest extends Request {

    private String menuId;
    private Map<Object, Object> submenuDTO;

    public SaveSubmenuRequest(String menuId, WebSubmenu submenu) {
        this.menuId = menuId;
        this.submenuDTO = submenu.toMap();
    }

    public void setSubmenu(WebSubmenu submenu) {
        this.submenuDTO = submenu.toMap();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Map<Object, Object> getSubmenuDTO() {
        return submenuDTO;
    }

    public void setSubmenuDTO(Map<Object, Object> submenuDTO) {
        this.submenuDTO = submenuDTO;
    }
}
