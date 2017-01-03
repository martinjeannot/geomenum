/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.restaurant;

import com.geomenum.core.services.restaurant.RestaurantCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.restaurant.SaveRestaurantRequest;
import com.geomenum.web.requestsresponses.restaurant.SaveRestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class SaveRestaurantRequestHandler extends AbstractRequestHandler<SaveRestaurantRequest, SaveRestaurantResponse> {

    @Autowired
    private RestaurantCoreService restaurantCoreService;

    @Override
    public SaveRestaurantResponse handle(SaveRestaurantRequest request) {
        restaurantCoreService.update(request.getRestaurantDTO());
        return new SaveRestaurantResponse();
    }

    @Override
    public SaveRestaurantResponse createDefaultResponse() {
        return new SaveRestaurantResponse();
    }
}
