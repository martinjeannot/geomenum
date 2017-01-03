/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.restaurant;

import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.core.services.GenericCoreService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Restaurant core service.
 */
public interface RestaurantCoreService extends GenericCoreService<CoreRestaurant> {

    CoreRestaurant findByFormattedAddress(String formattedAddress);

    List<Map<Object, Object>> findEnabledByAnyCriteria(
            double locationLatitude,
            double locationLongitude,
            double maxDistance,
            Cuisine cuisine);

    CoreRestaurant update(Map<Object, Object> restaurantDTO);

    /**
     * Add support to the given language.
     *
     * @param restaurantId the restaurant ID
     * @param language the new language to support
     * @return the restaurant
     */
    CoreRestaurant addLanguageSupport(String restaurantId, Locale language);

    /**
     * Remove support of the given language.
     *
     * @param restaurantId the restaurant ID
     * @param language the language to remove the support of
     * @return the restaurant
     */
    CoreRestaurant removeLanguageSupport(String restaurantId, Locale language);
}
