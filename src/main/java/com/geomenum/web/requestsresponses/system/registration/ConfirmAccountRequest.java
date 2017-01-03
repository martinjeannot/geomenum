/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.system.registration;

import com.geomenum.r2d2.common.Request;

/**
 * Confirms a user account.<br/>
 * This {@link Request} is issued when the user click the confirmation link sent to him (also confirms his mail address).
 */
public class ConfirmAccountRequest extends Request {

    private String userId;

    public ConfirmAccountRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
