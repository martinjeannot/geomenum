/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.system;

import com.geomenum.core.services.system.UserCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.system.DeleteUserAccountRequest;
import com.geomenum.web.requestsresponses.system.DeleteUserAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class DeleteUserAccountRequestHandler extends AbstractRequestHandler<DeleteUserAccountRequest, DeleteUserAccountResponse> {

    @Autowired
    private UserCoreService userCoreService;

    @Override
    public DeleteUserAccountResponse handle(DeleteUserAccountRequest request) {
        userCoreService.deleteUserAccount(request.getUsername());
        return new DeleteUserAccountResponse();
    }

    @Override
    public DeleteUserAccountResponse createDefaultResponse() {
        return new DeleteUserAccountResponse();
    }
}
