/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.operations.restaurant;

import com.geomenum.r2d2.common.Request;

/**
 * Retrieve a restaurant by its id.
 */
public class GetRestaurantRequest extends Request {

    private String restaurantId;

    public GetRestaurantRequest(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
