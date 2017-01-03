/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.restaurant;

import com.geomenum.core.services.restaurant.RestaurantCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.restaurant.RemoveLanguageSupportRequest;
import com.geomenum.web.requestsresponses.restaurant.RemoveLanguageSupportResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class RemoveLanguageSupportRequestHandler extends AbstractRequestHandler<RemoveLanguageSupportRequest, RemoveLanguageSupportResponse> {

    @Autowired
    private RestaurantCoreService restaurantCoreService;

    @Override
    public RemoveLanguageSupportResponse handle(RemoveLanguageSupportRequest request) {
        restaurantCoreService.removeLanguageSupport(request.getRestaurantId(), request.getLanguage());
        return new RemoveLanguageSupportResponse();
    }

    @Override
    public RemoveLanguageSupportResponse createDefaultResponse() {
        return new RemoveLanguageSupportResponse();
    }
}
