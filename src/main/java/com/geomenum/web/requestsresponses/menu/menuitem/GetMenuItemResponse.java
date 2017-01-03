/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.Response;
import com.geomenum.web.domainmodel.menu.WebMenuItem;

import java.util.Map;

/**
 * @see GetMenuItemRequest
 */
public class GetMenuItemResponse extends Response {

    private Map<Object, Object> menuItemDTO;

    public GetMenuItemResponse(Map<Object, Object> menuItemDTO) {
        this.menuItemDTO = menuItemDTO;
    }

    public WebMenuItem getMenuItem() {
        return WebMenuItem.of(menuItemDTO);
    }

    public Map<Object, Object> getMenuItemDTO() {
        return menuItemDTO;
    }

    public void setMenuItemDTO(Map<Object, Object> menuItemDTO) {
        this.menuItemDTO = menuItemDTO;
    }
}
