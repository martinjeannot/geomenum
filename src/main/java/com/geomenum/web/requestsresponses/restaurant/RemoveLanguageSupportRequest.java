/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.restaurant;

import com.geomenum.r2d2.common.Request;

import java.util.Locale;

/**
 * Remove support for a language.
 */
public class RemoveLanguageSupportRequest extends Request {

    private String restaurantId;
    private Locale language;

    public RemoveLanguageSupportRequest(String restaurantId, Locale language) {
        this.restaurantId = restaurantId;
        this.language = language;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }
}
