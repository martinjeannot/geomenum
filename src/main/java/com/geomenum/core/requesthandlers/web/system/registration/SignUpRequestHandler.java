/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.system.registration;

import com.geomenum.core.services.system.registration.RegistrationCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.system.registration.SignUpRequest;
import com.geomenum.web.requestsresponses.system.registration.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class SignUpRequestHandler extends AbstractRequestHandler<SignUpRequest, SignUpResponse> {

    @Autowired
    private RegistrationCoreService registrationCoreService;

    @Override
    public SignUpResponse handle(SignUpRequest request) {
        registrationCoreService.launchRegistrationProcess(request.getRegistrationFormDTO());
        return new SignUpResponse();
    }

    @Override
    public SignUpResponse createDefaultResponse() {
        return new SignUpResponse();
    }
}
