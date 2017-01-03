/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.menu;

import com.geomenum.core.services.menu.SubmenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.menu.MoveMenuContentRequest;
import com.geomenum.web.requestsresponses.menu.MoveMenuContentResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class MoveMenuContentRequestHandler extends AbstractRequestHandler<MoveMenuContentRequest, MoveMenuContentResponse> {

    @Autowired
    private SubmenuCoreService submenuCoreService;

    @Override
    public MoveMenuContentResponse handle(MoveMenuContentRequest request) {
        boolean moveResult = false;
        switch(request.getDirection()) {
            case UP :
                moveResult = submenuCoreService.moveUp(request.getMenuId(), request.getMenuContentId());
                break;
            case DOWN :
                moveResult = submenuCoreService.moveDown(request.getMenuId(), request.getMenuContentId());
                break;
            case UPPER_LEVEL :
                moveResult = submenuCoreService.moveToUpperLevel(request.getMenuId(), request.getMenuContentId());
                break;
            case LOWER_LEVEL :
                moveResult = submenuCoreService.moveToLowerLevel(request.getMenuId(), request.getMenuContentId(), request.getSiblingId());
                break;
        }
        return new MoveMenuContentResponse(moveResult);
    }

    @Override
    public MoveMenuContentResponse createDefaultResponse() {
        return new MoveMenuContentResponse(false);
    }
}
