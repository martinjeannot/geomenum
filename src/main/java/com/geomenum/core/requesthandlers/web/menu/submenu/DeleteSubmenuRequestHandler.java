/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu.submenu;

import com.geomenum.core.services.menu.SubmenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.submenu.DeleteSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.DeleteSubmenuResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class DeleteSubmenuRequestHandler extends AbstractRequestHandler<DeleteSubmenuRequest, DeleteSubmenuResponse> {

    @Autowired
    private SubmenuCoreService submenuCoreService;

    @Override
    public DeleteSubmenuResponse handle(DeleteSubmenuRequest request) {
        submenuCoreService.delete(request.getSubmenuId(), request.getMenuId());
        return new DeleteSubmenuResponse();
    }

    @Override
    public DeleteSubmenuResponse createDefaultResponse() {
        return new DeleteSubmenuResponse();
    }
}
