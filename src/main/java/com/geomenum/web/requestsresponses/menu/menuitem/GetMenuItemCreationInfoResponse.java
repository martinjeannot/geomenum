/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.Response;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;

/**
 * @see GetMenuItemCreationInfoRequest
 */
public class GetMenuItemCreationInfoResponse extends Response {

    private Set<Locale> supportedLanguages;
    private Currency currency;

    public GetMenuItemCreationInfoResponse(Set<Locale> supportedLanguages, Currency currency) {
        this.supportedLanguages = supportedLanguages;
        this.currency = currency;
    }

    public Set<Locale> getSupportedLanguages() {
        return supportedLanguages;
    }

    public void setSupportedLanguages(Set<Locale> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
