/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.requestsresponses.menu;

import com.geomenum.r2d2.common.Response;
import com.geomenum.rest.domainmodel.menu.RestMenu;

import java.util.Map;

/**
 * @see GetMenuRequest
 */
public class GetMenuResponse extends Response {

    private Map<Object, Object> menuDTO;

    public GetMenuResponse(Map<Object, Object> menuDTO) {
        this.menuDTO = menuDTO;
    }

    public boolean wasMenuFound() {
        return menuDTO != null;
    }

    public RestMenu getMenu() {
        return RestMenu.of(menuDTO);
    }

    public Map<Object, Object> getMenuDTO() {
        return menuDTO;
    }

    public void setMenuDTO(Map<Object, Object> menuDTO) {
        this.menuDTO = menuDTO;
    }
}
