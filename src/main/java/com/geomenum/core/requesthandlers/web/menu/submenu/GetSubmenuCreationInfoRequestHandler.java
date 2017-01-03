/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.submenu;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.services.menu.MenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuCreationInfoRequest;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuCreationInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class GetSubmenuCreationInfoRequestHandler extends AbstractRequestHandler<GetSubmenuCreationInfoRequest, GetSubmenuCreationInfoResponse> {

    private final MenuCoreService menuCoreService;

    @Autowired
    public GetSubmenuCreationInfoRequestHandler(MenuCoreService menuCoreService) {
        this.menuCoreService = menuCoreService;
    }

    @Override
    public GetSubmenuCreationInfoResponse handle(GetSubmenuCreationInfoRequest request) {
        CoreMenu menu = menuCoreService.findById(request.getMenuId());
        return new GetSubmenuCreationInfoResponse(menu.getSupportedLanguages());
    }

    @Override
    public GetSubmenuCreationInfoResponse createDefaultResponse() {
        return new GetSubmenuCreationInfoResponse(null);
    }
}
