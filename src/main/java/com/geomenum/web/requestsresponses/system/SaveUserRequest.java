/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.system;

import com.geomenum.r2d2.common.Request;
import com.geomenum.web.domainmodel.system.WebUser;

import java.util.Map;

/**
 * Save (= update) an user.
 */
public class SaveUserRequest extends Request {

    private Map<Object, Object> userDTO;

    public SaveUserRequest(WebUser user) {
        this.userDTO = user.toMap();
    }

    public void setUser(WebUser user) {
        this.userDTO = user.toMap();
    }

    public Map<Object, Object> getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(Map<Object, Object> userDTO) {
        this.userDTO = userDTO;
    }
}
