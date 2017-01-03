/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.submenu;

import com.geomenum.core.services.menu.SubmenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.submenu.SaveSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.SaveSubmenuResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class SaveSubmenuRequestHandler extends AbstractRequestHandler<SaveSubmenuRequest, SaveSubmenuResponse> {

    private final SubmenuCoreService submenuCoreService;

    @Autowired
    public SaveSubmenuRequestHandler(SubmenuCoreService submenuCoreService) {
        this.submenuCoreService = submenuCoreService;
    }

    @Override
    public SaveSubmenuResponse handle(SaveSubmenuRequest request) {
        submenuCoreService.update(request.getSubmenuDTO(), request.getMenuId());
        return new SaveSubmenuResponse();
    }

    @Override
    public SaveSubmenuResponse createDefaultResponse() {
        return new SaveSubmenuResponse();
    }
}
