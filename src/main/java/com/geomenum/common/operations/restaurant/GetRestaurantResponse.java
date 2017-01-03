/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.operations.restaurant;

import com.geomenum.r2d2.common.Response;

import java.util.Map;

/**
 * @see GetRestaurantRequest
 */
public abstract class GetRestaurantResponse extends Response {

    private Map<Object, Object> restaurantDTO;

    public GetRestaurantResponse(Map<Object, Object> restaurantDTO) {
        this.restaurantDTO = restaurantDTO;
    }

    public abstract Object getRestaurant();

    public Map<Object, Object> getRestaurantDTO() {
        return restaurantDTO;
    }

    public void setRestaurantDTO(Map<Object, Object> restaurantDTO) {
        this.restaurantDTO = restaurantDTO;
    }
}
