/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.Response;

import java.util.Locale;
import java.util.Set;

/**
 * @see GetSubmenuCreationInfoRequest
 */
public class GetSubmenuCreationInfoResponse extends Response {

    private Set<Locale> supportedLanguages;

    public GetSubmenuCreationInfoResponse(Set<Locale> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    public Set<Locale> getSupportedLanguages() {
        return supportedLanguages;
    }

    public void setSupportedLanguages(Set<Locale> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }
}
