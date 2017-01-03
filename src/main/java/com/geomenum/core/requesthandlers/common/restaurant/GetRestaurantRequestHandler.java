/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.common.restaurant;

import com.geomenum.common.operations.restaurant.GetRestaurantRequest;
import com.geomenum.common.operations.restaurant.GetRestaurantResponse;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.core.services.restaurant.RestaurantCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GetRestaurantRequestHandler<REQ extends GetRestaurantRequest, RES extends GetRestaurantResponse> extends AbstractRequestHandler<REQ, RES> {

    @Autowired
    private RestaurantCoreService restaurantCoreService;

    @Override
    public RES handle(REQ request) {
        CoreRestaurant restaurant = restaurantCoreService.findById(request.getRestaurantId());
        return createResponse(restaurant);
    }

    protected abstract RES createResponse(CoreRestaurant restaurant);

    @Override
    public abstract RES createDefaultResponse();
}
