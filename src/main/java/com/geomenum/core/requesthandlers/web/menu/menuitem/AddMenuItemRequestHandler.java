/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.menuitem;

import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.core.services.menu.MenuItemCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.menuitem.AddMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.AddMenuItemResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class AddMenuItemRequestHandler extends AbstractRequestHandler<AddMenuItemRequest, AddMenuItemResponse> {

    private final MenuItemCoreService menuItemCoreService;

    @Autowired
    public AddMenuItemRequestHandler(MenuItemCoreService menuItemCoreService) {
        this.menuItemCoreService = menuItemCoreService;
    }

    @Override
    public AddMenuItemResponse handle(AddMenuItemRequest request) {
        CoreMenuItem addedMenuItem = menuItemCoreService.add(
                request.getNewMenuItemDTO(),
                request.getMenuId(),
                request.getParentId());
        return new AddMenuItemResponse(addedMenuItem.toMap());
    }

    @Override
    public AddMenuItemResponse createDefaultResponse() {
        return new AddMenuItemResponse(null);
    }
}
