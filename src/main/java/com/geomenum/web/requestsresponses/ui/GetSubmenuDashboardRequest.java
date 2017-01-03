package com.geomenum.web.requestsresponses.ui;

import com.geomenum.r2d2.common.Request;

import java.util.UUID;

/**
 * Retrieve the submenu dashboard data related to the targeted menu and submenu.
 */
public class GetSubmenuDashboardRequest extends Request {

    private String menuId;
    private UUID submenuId;

    public GetSubmenuDashboardRequest(String menuId, String submenuId) {
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
