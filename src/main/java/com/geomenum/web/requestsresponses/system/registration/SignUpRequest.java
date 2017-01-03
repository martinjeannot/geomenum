/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.system.registration;

import com.geomenum.r2d2.common.Request;
import com.geomenum.web.domainmodel.system.registration.RegistrationForm;

import java.util.Map;

/**
 * Create a new User associated with a new Restaurant (and a new Menu).
 */
public class SignUpRequest extends Request {

    private Map<Object, Object> registrationFormDTO;

    public SignUpRequest(RegistrationForm registrationForm) {
        registrationFormDTO = registrationForm.toMap();
    }

    public Map<Object, Object> getRegistrationFormDTO() {
        return registrationFormDTO;
    }

    public void setRegistrationFormDTO(Map<Object, Object> registrationFormDTO) {
        this.registrationFormDTO = registrationFormDTO;
    }
}
