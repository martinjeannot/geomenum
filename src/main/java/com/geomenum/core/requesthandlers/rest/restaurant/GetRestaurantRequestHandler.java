/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.rest.restaurant;

import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.rest.requestsresponses.restaurant.GetRestaurantRequest;
import com.geomenum.rest.requestsresponses.restaurant.GetRestaurantResponse;

@RequestHandler
public class GetRestaurantRequestHandler extends com.geomenum.core.requesthandlers.common.restaurant.GetRestaurantRequestHandler<GetRestaurantRequest, GetRestaurantResponse> {

    @Override
    protected GetRestaurantResponse createResponse(CoreRestaurant restaurant) {
        return new GetRestaurantResponse(restaurant.toMap());
    }

    @Override
    public GetRestaurantResponse createDefaultResponse() {
        return new GetRestaurantResponse(null);
    }
}
