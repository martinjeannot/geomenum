/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.rest.restaurant;

import com.geomenum.core.services.restaurant.RestaurantCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.rest.requestsresponses.restaurant.GetRestaurantsRequest;
import com.geomenum.rest.requestsresponses.restaurant.GetRestaurantsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RequestHandler
public class GetRestaurantsRequestHandler extends AbstractRequestHandler<GetRestaurantsRequest, GetRestaurantsResponse> {

    @Autowired
    private RestaurantCoreService restaurantCoreService;

    @Override
    public GetRestaurantsResponse handle(GetRestaurantsRequest request) {
        List<Map<Object, Object>> results = restaurantCoreService.findEnabledByAnyCriteria(
                request.getLocationLatitude(),
                request.getLocationLongitude(),
                request.getMaximumDistance(),
                request.getCuisine());
        return new GetRestaurantsResponse(results);
    }

    @Override
    public GetRestaurantsResponse createDefaultResponse() {
        return new GetRestaurantsResponse(null);
    }
}
