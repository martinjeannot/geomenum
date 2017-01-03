/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.rest.menu;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.services.menu.MenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.rest.requestsresponses.menu.GetMenuRequest;
import com.geomenum.rest.requestsresponses.menu.GetMenuResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler("RestGetMenuRequestHandler")
public class GetMenuRequestHandler extends AbstractRequestHandler<GetMenuRequest, GetMenuResponse> {

    @Autowired
    private MenuCoreService menuCoreService;

    @Override
    public GetMenuResponse handle(GetMenuRequest request) {
        CoreMenu menu = menuCoreService.findEnabledById(request.getMenuId());
        return new GetMenuResponse(menu == null ? null : menu.toMap());
    }

    @Override
    public GetMenuResponse createDefaultResponse() {
        return new GetMenuResponse(null);
    }
}