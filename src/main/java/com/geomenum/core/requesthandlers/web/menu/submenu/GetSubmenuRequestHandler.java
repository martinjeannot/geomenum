/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.submenu;

import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.geomenum.core.services.menu.SubmenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class GetSubmenuRequestHandler extends AbstractRequestHandler<GetSubmenuRequest, GetSubmenuResponse> {

    @Autowired
    private SubmenuCoreService submenuCoreService;

    @Override
    public GetSubmenuResponse handle(GetSubmenuRequest request) {
        CoreSubmenu submenu = submenuCoreService.findById(request.getSubmenuId(), request.getMenuId());
        return new GetSubmenuResponse(submenu.toMap());
    }

    @Override
    public GetSubmenuResponse createDefaultResponse() {
        return new GetSubmenuResponse(null);
    }
}
