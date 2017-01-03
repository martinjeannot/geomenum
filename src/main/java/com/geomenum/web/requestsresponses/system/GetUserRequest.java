/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.system;

import com.geomenum.r2d2.common.Request;

/**
 * Retrieve a user by its id.
 */
public class GetUserRequest extends Request {

    private String userId;

    public GetUserRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
