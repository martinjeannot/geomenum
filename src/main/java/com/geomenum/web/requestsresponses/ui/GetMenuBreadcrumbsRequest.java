package com.geomenum.web.requestsresponses.ui;

import com.geomenum.r2d2.common.Request;

import java.util.UUID;

/**
 * Retrieve the breadcrumb navigation data related to the targeted menu and submenu/menu item (= menuNodeContent).
 */
public class GetMenuBreadcrumbsRequest extends Request {

    private String menuId;
    private UUID menuNodeContentId;

    public GetMenuBreadcrumbsRequest(String menuId, String menuNodeContentId) {
        this.menuId = menuId;
        this.menuNodeContentId = UUID.fromString(menuNodeContentId);
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public UUID getMenuNodeContentId() {
        return menuNodeContentId;
    }

    public void setMenuNodeContentId(UUID menuNodeContentId) {
        this.menuNodeContentId = menuNodeContentId;
    }
}
