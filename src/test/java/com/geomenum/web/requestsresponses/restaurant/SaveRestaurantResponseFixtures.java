/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.restaurant;

import com.geomenum.r2d2.common.ExceptionType;

public class SaveRestaurantResponseFixtures {

    public static SaveRestaurantResponse successResponse() {
        return new SaveRestaurantResponse();
    }

    public static SaveRestaurantResponse defaultResponse() {
        SaveRestaurantResponse response = new SaveRestaurantResponse();
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
