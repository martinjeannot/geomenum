/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.restaurant;

import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import com.geomenum.persistence.services.GenericPersistenceService;

import java.util.List;
import java.util.Map;

/**
 * Restaurant persistence service.
 */
public interface RestaurantPersistenceService extends GenericPersistenceService<PersistenceRestaurant> {

    /**
     * Retrieves enabled restaurants based on multiple criteria.
     *
     * @param locationLatitude the location latitude
     * @param locationLongitude the location longitude
     * @param maxDistance the maximum distance in kilometers
     * @param cuisine the desired cuisine style
     * @return a List of Map containing both the restaurant and the distance
     */
    List<Map<Object, Object>> findEnabledByAnyCriteria(
            double locationLatitude,
            double locationLongitude,
            double maxDistance,
            Cuisine cuisine);

    /**
     * Retrieves the restaurant with the given formatted address if found, returns {@code null} otherwise.
     *
     * @param formattedAddress the formatted address of the restaurant's location
     * @return the found restaurant or {@code null}
     * @throws IllegalStateException if multiple restaurants have been found
     */
    PersistenceRestaurant findByFormattedAddress(String formattedAddress);
}
