/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.system.registration;

import com.geomenum.core.services.system.registration.RegistrationCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.system.registration.ConfirmAccountRequest;
import com.geomenum.web.requestsresponses.system.registration.ConfirmAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class ConfirmAccountRequestHandler extends AbstractRequestHandler<ConfirmAccountRequest, ConfirmAccountResponse> {

    @Autowired
    private RegistrationCoreService registrationCoreService;

    @Override
    public ConfirmAccountResponse handle(ConfirmAccountRequest request) {
        registrationCoreService.confirmUserAccount(request.getUserId());
        return new ConfirmAccountResponse();
    }

    @Override
    public ConfirmAccountResponse createDefaultResponse() {
        return new ConfirmAccountResponse();
    }
}
