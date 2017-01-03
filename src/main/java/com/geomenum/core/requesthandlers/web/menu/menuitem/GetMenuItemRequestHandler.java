/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.menuitem;

import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.core.services.menu.MenuItemCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class GetMenuItemRequestHandler extends AbstractRequestHandler<GetMenuItemRequest, GetMenuItemResponse> {

    @Autowired
    private MenuItemCoreService menuItemCoreService;

    @Override
    public GetMenuItemResponse handle(GetMenuItemRequest request) {
        CoreMenuItem menuItem = menuItemCoreService.findById(request.getMenuItemId(), request.getMenuId());
        return new GetMenuItemResponse(menuItem.toMap());
    }

    @Override
    public GetMenuItemResponse createDefaultResponse() {
        return new GetMenuItemResponse(null);
    }
}
