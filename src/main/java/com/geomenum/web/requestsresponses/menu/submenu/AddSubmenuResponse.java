/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.Response;

import java.util.Map;

/**
 * @see AddSubmenuRequest
 */
public class AddSubmenuResponse extends Response {

    private Map<Object, Object> addedSubmenuDTO;

    public AddSubmenuResponse(Map<Object, Object> addedSubmenuDTO) {
        this.addedSubmenuDTO = addedSubmenuDTO;
    }

    public Map<Object, Object> getAddedSubmenuDTO() {
        return addedSubmenuDTO;
    }

    public void setAddedSubmenuDTO(Map<Object, Object> addedSubmenuDTO) {
        this.addedSubmenuDTO = addedSubmenuDTO;
    }
}
