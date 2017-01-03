/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers.restaurant;

import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.rest.RestApiUri;
import com.geomenum.rest.controllers.AbstractRestController;
import com.geomenum.rest.domainmodel.restaurant.RestRestaurant;
import com.geomenum.rest.domainmodel.restaurant.ShortRestaurantResource;
import com.geomenum.rest.requestsresponses.restaurant.GetRestaurantsRequest;
import com.geomenum.rest.requestsresponses.restaurant.GetRestaurantsResponse;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(value = RestApiUri.RESTAURANTS, method = RequestMethod.GET)
public class RestaurantsQueryRestController extends AbstractRestController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantsQueryRestController.class);

    private static final String LATITUDE_PARAM_KEY = "lat";
    private static final String LONGITUDE_PARAM_KEY = "lng";
    private static final String MAX_DISTANCE_PARAM_KEY = "dist";
    private static final String CUISINE_STYLE_PARAM_KEY = "cuisine";

    @RequestMapping
    public ResponseEntity<?> getRestaurants(@RequestParam Map<String, String> params,
                                            WebRequest webRequest) {
        // Parameters validation
        List<String> errors = validateParameters(params);
        if (!errors.isEmpty()) {
            return getBadRequestResponse(errors);
        }

        // Parameters parsing
        double locationLatitude = Double.parseDouble(params.get(LATITUDE_PARAM_KEY));
        double locationLongitude = Double.parseDouble(params.get(LONGITUDE_PARAM_KEY));
        double maxDistance = Double.parseDouble(params.get(MAX_DISTANCE_PARAM_KEY));
        Cuisine cuisine = null;
        if (params.containsKey(CUISINE_STYLE_PARAM_KEY)) {
            cuisine = Cuisine.valueOf(params.get(CUISINE_STYLE_PARAM_KEY));
        }

        GetRestaurantsResponse response = createRequestDispatcher().getResponse(
                new GetRestaurantsRequest(
                        locationLatitude,
                        locationLongitude,
                        maxDistance,
                        cuisine),
                GetRestaurantsResponse.class);

        if (response.hasExceptionOccurred()) {
            return getInternalServerErrorResponse();
        }

        List<ShortRestaurantResource> restaurants = Lists.newArrayListWithCapacity(response.getResults().size());
        Locale requestedLanguage = getLocale(webRequest);
        for (Map<Object, Object> result : response.getResults()) {
            ShortRestaurantResource restaurant = new ShortRestaurantResource(
                    RestRestaurant.of((Map<Object, Object>) result.get("restaurantDTO")),
                    requestedLanguage,
                    messageSource);
            restaurant.setDistance((Double) result.get("distance"));
            restaurants.add(restaurant);
        }

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    private List<String> validateParameters(Map<String, String> params) {
        List<String> errors = Lists.newArrayList();

        validateParameterAsDouble(LATITUDE_PARAM_KEY, params.get(LATITUDE_PARAM_KEY), true, errors);
        validateParameterAsDouble(LONGITUDE_PARAM_KEY, params.get(LONGITUDE_PARAM_KEY), true, errors);
        validateParameterAsDouble(MAX_DISTANCE_PARAM_KEY, params.get(MAX_DISTANCE_PARAM_KEY), true, errors);
        validateParameterAsCuisineStyle(CUISINE_STYLE_PARAM_KEY, params.get(CUISINE_STYLE_PARAM_KEY), false, errors);

        return errors;
    }
}
