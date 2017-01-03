/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.Response;

import java.util.Map;

/**
 * @see AddMenuItemRequest
 */
public class AddMenuItemResponse extends Response {

    private Map<Object, Object> addedMenuItemDTO;

    public AddMenuItemResponse(Map<Object, Object> addedMenuItemDTO) {
        this.addedMenuItemDTO = addedMenuItemDTO;
    }

    public Map<Object, Object> getAddedMenuItemDTO() {
        return addedMenuItemDTO;
    }

    public void setAddedMenuItemDTO(Map<Object, Object> addedMenuItemDTO) {
        this.addedMenuItemDTO = addedMenuItemDTO;
    }
}
