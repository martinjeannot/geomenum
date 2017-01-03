/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.restaurant;

import com.geomenum.r2d2.common.Request;
import com.geomenum.web.domainmodel.restaurant.WebRestaurant;

import java.util.Map;

/**
 * Save (= update) a restaurant.
 */
public class SaveRestaurantRequest extends Request {

    private Map<Object, Object> restaurantDTO;

    public SaveRestaurantRequest(WebRestaurant restaurant) {
        this.restaurantDTO = restaurant.toMap();
    }

    public void setRestaurant(WebRestaurant restaurant) {
        this.restaurantDTO = restaurant.toMap();
    }

    public Map<Object, Object> getRestaurantDTO() {
        return restaurantDTO;
    }

    public void setRestaurantDTO(Map<Object, Object> restaurantDTO) {
        this.restaurantDTO = restaurantDTO;
    }
}
