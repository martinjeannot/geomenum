/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.Response;
import com.geomenum.web.domainmodel.menu.WebSubmenu;

import java.util.Map;

/**
 * @see CreateSubmenuRequest
 */
public class CreateSubmenuResponse extends Response {

    private Map<Object, Object> createdSubmenuDTO;

    public CreateSubmenuResponse(Map<Object, Object> createdSubmenuDTO) {
        this.createdSubmenuDTO = createdSubmenuDTO;
    }

    public WebSubmenu getCreatedSubmenu() {
        return WebSubmenu.of(createdSubmenuDTO);
    }

    public Map<Object, Object> getCreatedSubmenuDTO() {
        return createdSubmenuDTO;
    }

    public void setCreatedSubmenuDTO(Map<Object, Object> createdSubmenuDTO) {
        this.createdSubmenuDTO = createdSubmenuDTO;
    }
}
