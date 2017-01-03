/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.restaurant;

import com.geomenum.web.domainmodel.restaurant.WebRestaurant;

import java.util.Map;

/**
 * @see GetRestaurantRequest
 */
public class GetRestaurantResponse extends com.geomenum.common.operations.restaurant.GetRestaurantResponse {

    public GetRestaurantResponse(Map<Object, Object> restaurantDTO) {
        super(restaurantDTO);
    }

    @Override
    public WebRestaurant getRestaurant() {
        return WebRestaurant.of(getRestaurantDTO());
    }
}
