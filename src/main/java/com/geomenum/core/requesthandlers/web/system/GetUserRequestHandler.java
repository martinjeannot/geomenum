/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.system;

import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.services.system.UserCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.system.GetUserRequest;
import com.geomenum.web.requestsresponses.system.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class GetUserRequestHandler extends AbstractRequestHandler<GetUserRequest, GetUserResponse> {

    @Autowired
    private UserCoreService userCoreService;

    @Override
    public GetUserResponse handle(GetUserRequest request) {
        CoreUser user = userCoreService.findById(request.getUserId());
        return new GetUserResponse(user.toMap());
    }

    @Override
    public GetUserResponse createDefaultResponse() {
        return new GetUserResponse(null);
    }
}
