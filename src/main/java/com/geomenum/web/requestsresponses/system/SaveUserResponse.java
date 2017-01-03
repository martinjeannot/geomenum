/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.system;

import com.geomenum.r2d2.common.Response;

import java.util.Locale;

/**
 * @see SaveUserRequest
 */
public class SaveUserResponse extends Response {

    private boolean updatedUserEnabled;
    private Locale updatedUserLanguage;

    public SaveUserResponse(boolean updatedUserEnabled, Locale updatedUserLanguage) {
        this.updatedUserEnabled = updatedUserEnabled;
        this.updatedUserLanguage = updatedUserLanguage;
    }

    public boolean isUpdatedUserEnabled() {
        return updatedUserEnabled;
    }

    public void setUpdatedUserEnabled(boolean updatedUserEnabled) {
        this.updatedUserEnabled = updatedUserEnabled;
    }

    public Locale getUpdatedUserLanguage() {
        return updatedUserLanguage;
    }

    public void setUpdatedUserLanguage(Locale updatedUserLanguage) {
        this.updatedUserLanguage = updatedUserLanguage;
    }
}
