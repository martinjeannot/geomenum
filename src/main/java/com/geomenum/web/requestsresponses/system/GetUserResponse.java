/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.system;

import com.geomenum.r2d2.common.Response;
import com.geomenum.web.domainmodel.system.WebUser;

import java.util.Map;

/**
 * @see GetUserRequest
 */
public class GetUserResponse extends Response {

    private Map<Object, Object> userDTO;

    public GetUserResponse(Map<Object, Object> userDTO) {
        this.userDTO = userDTO;
    }

    public WebUser getUser() {
        return WebUser.of(userDTO);
    }

    public Map<Object, Object> getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(Map<Object, Object> userDTO) {
        this.userDTO = userDTO;
    }
}
