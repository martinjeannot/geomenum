/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.restaurant;

import com.geomenum.core.services.restaurant.RestaurantCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.restaurant.AddLanguageSupportRequest;
import com.geomenum.web.requestsresponses.restaurant.AddLanguageSupportResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RequestHandler
public class AddLanguageSupportRequestHandler extends AbstractRequestHandler<AddLanguageSupportRequest, AddLanguageSupportResponse> {

    @Autowired
    private RestaurantCoreService restaurantCoreService;

    @Override
    public AddLanguageSupportResponse handle(AddLanguageSupportRequest request) {
        restaurantCoreService.addLanguageSupport(request.getRestaurantId(), request.getLanguage());
        return new AddLanguageSupportResponse();
    }

    @Override
    public AddLanguageSupportResponse createDefaultResponse() {
        return new AddLanguageSupportResponse();
    }
}
