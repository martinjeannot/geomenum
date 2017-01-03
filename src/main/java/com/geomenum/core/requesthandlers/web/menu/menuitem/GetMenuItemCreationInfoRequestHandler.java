/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.menuitem;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.services.menu.MenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemCreationInfoRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemCreationInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class GetMenuItemCreationInfoRequestHandler extends AbstractRequestHandler<GetMenuItemCreationInfoRequest, GetMenuItemCreationInfoResponse> {

    private final MenuCoreService menuCoreService;

    @Autowired
    public GetMenuItemCreationInfoRequestHandler(MenuCoreService menuCoreService) {
        this.menuCoreService = menuCoreService;
    }

    @Override
    public GetMenuItemCreationInfoResponse handle(GetMenuItemCreationInfoRequest request) {
        CoreMenu menu = menuCoreService.findById(request.getMenuId());
        return new GetMenuItemCreationInfoResponse(menu.getSupportedLanguages(), menu.getCurrency());
    }

    @Override
    public GetMenuItemCreationInfoResponse createDefaultResponse() {
        return new GetMenuItemCreationInfoResponse(null, null);
    }
}
