/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.system;

import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.services.system.UserCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.system.SaveUserRequest;
import com.geomenum.web.requestsresponses.system.SaveUserResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class SaveUserRequestHandler extends AbstractRequestHandler<SaveUserRequest, SaveUserResponse> {

    @Autowired
    private UserCoreService userCoreService;

    @Override
    public SaveUserResponse handle(SaveUserRequest request) {
        CoreUser updatedUser = userCoreService.update(request.getUserDTO());
        return new SaveUserResponse(updatedUser.isEnabled(), updatedUser.getLanguage());
    }

    @Override
    public SaveUserResponse createDefaultResponse() {
        return new SaveUserResponse(false, null);
    }
}
