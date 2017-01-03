/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.menuitem;

import com.geomenum.core.services.menu.MenuItemCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.menuitem.DeleteMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.DeleteMenuItemResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class DeleteMenuItemRequestHandler extends AbstractRequestHandler<DeleteMenuItemRequest, DeleteMenuItemResponse> {

    @Autowired
    private MenuItemCoreService menuItemCoreService;

    @Override
    public DeleteMenuItemResponse handle(DeleteMenuItemRequest request) {
        menuItemCoreService.delete(request.getMenuItemId(), request.getMenuId());
        return new DeleteMenuItemResponse();
    }

    @Override
    public DeleteMenuItemResponse createDefaultResponse() {
        return new DeleteMenuItemResponse();
    }
}
