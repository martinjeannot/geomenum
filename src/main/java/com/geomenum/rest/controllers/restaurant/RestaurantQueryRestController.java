/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers.restaurant;

import com.geomenum.rest.RestApiUri;
import com.geomenum.rest.controllers.AbstractRestController;
import com.geomenum.rest.domainmodel.restaurant.RestaurantResource;
import com.geomenum.rest.requestsresponses.restaurant.GetRestaurantRequest;
import com.geomenum.rest.requestsresponses.restaurant.GetRestaurantResponse;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = RestApiUri.RESTAURANT, method = RequestMethod.GET)
public class RestaurantQueryRestController extends AbstractRestController {

    private static final String TIMESTAMP_PARAM_KEY = "ts";

    @RequestMapping
    public ResponseEntity<?> getRestaurant(@PathVariable("restaurantId") String restaurantId,
                                           @RequestParam Map<String, String> params,
                                           WebRequest webRequest) {
        // Parameters validation
        List<String> errors = validateParameters(params);
        if (!errors.isEmpty()) {
            return getBadRequestResponse(errors);
        }

        // Parameters parsing
        long timestamp = Long.parseLong(params.get(TIMESTAMP_PARAM_KEY));

        GetRestaurantResponse response = createRequestDispatcher().getResponse(
                new GetRestaurantRequest(restaurantId),
                GetRestaurantResponse.class);

        if (response.hasExceptionOccurred()) {
            return getInternalServerErrorResponse();
        }

        if (!response.wasRestaurantFound()) {
            return getNotFoundResponse();
        }

        return new ResponseEntity<>(
                new RestaurantResource(response.getRestaurant(), getLocale(webRequest), messageSource, timestamp),
                HttpStatus.OK);
    }

    private List<String> validateParameters(Map<String, String> params) {
        List<String> errors = Lists.newArrayList();

        validateParameterAsLong(TIMESTAMP_PARAM_KEY, params.get(TIMESTAMP_PARAM_KEY), true, errors);

        return errors;
    }
}
