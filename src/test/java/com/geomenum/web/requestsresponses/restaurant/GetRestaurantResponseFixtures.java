/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.restaurant;

import com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures;
import com.geomenum.r2d2.common.ExceptionType;

public class GetRestaurantResponseFixtures {

    public static GetRestaurantResponse successResponse() {
        return new GetRestaurantResponse(CoreRestaurantFixtures.standardRestaurant().toMap());
    }

    public static GetRestaurantResponse defaultResponse() {
        GetRestaurantResponse response = new GetRestaurantResponse(null);
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
