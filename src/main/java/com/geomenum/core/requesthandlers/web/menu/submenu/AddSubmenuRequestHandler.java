/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.submenu;

import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.geomenum.core.services.menu.SubmenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.submenu.AddSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.AddSubmenuResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class AddSubmenuRequestHandler extends AbstractRequestHandler<AddSubmenuRequest, AddSubmenuResponse> {

    private final SubmenuCoreService submenuCoreService;

    @Autowired
    public AddSubmenuRequestHandler(SubmenuCoreService submenuCoreService) {
        this.submenuCoreService = submenuCoreService;
    }

    @Override
    public AddSubmenuResponse handle(AddSubmenuRequest request) {
        CoreSubmenu addedSubmenu = submenuCoreService.add(
                request.getNewSubmenuDTO(),
                request.getMenuId(),
                request.getParentId());
        return new AddSubmenuResponse(addedSubmenu.toMap());
    }

    @Override
    public AddSubmenuResponse createDefaultResponse() {
        return new AddSubmenuResponse(null);
    }
}
