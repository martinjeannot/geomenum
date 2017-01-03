/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.Response;
import com.geomenum.web.domainmodel.menu.WebSubmenu;

import java.util.Map;

/**
 * @see GetSubmenuRequest
 */
public class GetSubmenuResponse extends Response {

    private Map<Object, Object> submenuDTO;

    public GetSubmenuResponse(Map<Object, Object> submenuDTO) {
        this.submenuDTO = submenuDTO;
    }

    public WebSubmenu getSubmenu() {
        return WebSubmenu.of(submenuDTO);
    }

    public Map<Object, Object> getSubmenuDTO() {
        return submenuDTO;
    }

    public void setSubmenuDTO(Map<Object, Object> submenuDTO) {
        this.submenuDTO = submenuDTO;
    }
}
