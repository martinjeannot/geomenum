/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.menuitem;

import com.geomenum.core.services.menu.MenuItemCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.menuitem.SaveMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.SaveMenuItemResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class SaveMenuItemRequestHandler extends AbstractRequestHandler<SaveMenuItemRequest, SaveMenuItemResponse> {

    private final MenuItemCoreService menuItemCoreService;

    @Autowired
    public SaveMenuItemRequestHandler(MenuItemCoreService menuItemCoreService) {
        this.menuItemCoreService = menuItemCoreService;
    }

    @Override
    public SaveMenuItemResponse handle(SaveMenuItemRequest request) {
        menuItemCoreService.update(request.getMenuItemDTO(), request.getMenuId());
        return new SaveMenuItemResponse();
    }

    @Override
    public SaveMenuItemResponse createDefaultResponse() {
        return new SaveMenuItemResponse();
    }
}
