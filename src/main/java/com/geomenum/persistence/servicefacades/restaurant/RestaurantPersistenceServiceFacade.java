/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.servicefacades.restaurant;

import com.geomenum.common.integration.Mappable;
import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.persistence.converters.StringToPersistenceIdConverter;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import com.geomenum.persistence.servicefacades.GenericPersistenceServiceFacade;
import com.geomenum.persistence.services.restaurant.RestaurantPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RestaurantPersistenceServiceFacade implements GenericPersistenceServiceFacade<CoreRestaurant> {

    @Autowired
    private RestaurantPersistenceService service;

    public List<Map<Object, Object>> findEnabledByAnyCriteria(double locationLatitude, double locationLongitude, double maxDistance,
                                                              Cuisine cuisine) {
        List<Map<Object, Object>> results = service.findEnabledByAnyCriteria(
                locationLatitude, locationLongitude, maxDistance,
                cuisine);
        for(Map<Object, Object> result : results) {
            result.put("restaurant", CoreRestaurant.of(((Mappable) result.get("restaurant")).toMap()));
        }
        return results;
    }

    public CoreRestaurant findByFormattedAddress(String formattedAddress) {
        PersistenceRestaurant restaurant = service.findByFormattedAddress(formattedAddress);
        return restaurant != null ? CoreRestaurant.of(restaurant.toMap()) : null;
    }

    @Override
    public CoreRestaurant create(CoreRestaurant entity) {
        return CoreRestaurant.of(
                service.create(entity.toMap())
                        .toMap());
    }

    @Override
    public CoreRestaurant findById(String id) {
        PersistenceRestaurant restaurant = service.findById(StringToPersistenceIdConverter.convert(id));
        return restaurant != null ? CoreRestaurant.of(restaurant.toMap()) : null;
    }

    @Override
    public CoreRestaurant update(CoreRestaurant entity) {
        return CoreRestaurant.of(
                service.update(entity.toMap())
                        .toMap());
    }

    @Override
    public CoreRestaurant delete(String id) {
        return CoreRestaurant.of(
                service.delete(StringToPersistenceIdConverter.convert(id))
                        .toMap());
    }
}
