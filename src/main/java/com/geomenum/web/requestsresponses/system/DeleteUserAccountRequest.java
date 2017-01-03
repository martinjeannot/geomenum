/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.system;

import com.geomenum.r2d2.common.Request;

/**
 * Remove a user account in its entirety :
 * <ul>
 *     <li>delete menu</li>
 *     <li>delete restaurant</li>
 *     <li>delete user</li>
 * </ul>
 *
 */
public class DeleteUserAccountRequest extends Request {

    private String username;

    public DeleteUserAccountRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
