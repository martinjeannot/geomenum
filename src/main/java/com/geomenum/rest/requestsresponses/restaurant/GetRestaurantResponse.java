/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.requestsresponses.restaurant;

import com.geomenum.rest.domainmodel.restaurant.RestRestaurant;

import java.util.Map;

/**
 * @see GetRestaurantRequest
 */
public class GetRestaurantResponse extends com.geomenum.common.operations.restaurant.GetRestaurantResponse {

    public GetRestaurantResponse(Map<Object, Object> restaurantDTO) {
        super(restaurantDTO);
    }

    @Override
    public RestRestaurant getRestaurant() {
        return RestRestaurant.of(getRestaurantDTO());
    }

    public boolean wasRestaurantFound() {
        return getRestaurantDTO() != null;
    }
}
