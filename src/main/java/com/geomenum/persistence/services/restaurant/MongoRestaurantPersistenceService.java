/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.restaurant;

import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import com.geomenum.persistence.repositories.restaurant.RestaurantRepository;
import com.geomenum.persistence.services.MongoGenericPersistenceService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * {@link RestaurantPersistenceService} MongoDB implementation.
 */
@Service
public class MongoRestaurantPersistenceService extends MongoGenericPersistenceService<PersistenceRestaurant> implements RestaurantPersistenceService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    protected MongoRepository<PersistenceRestaurant, ObjectId> getRepository() {
        return restaurantRepository;
    }

    @Override
    protected PersistenceRestaurant getDomainObjectFromDTO(Map<Object, Object> dto) {
        return PersistenceRestaurant.of(dto);
    }

    @Override
    public List<Map<Object, Object>> findEnabledByAnyCriteria(
            double locationLatitude,
            double locationLongitude,
            double maxDistance,
            Cuisine cuisine) {
        GeoResults<PersistenceRestaurant> geoResults = restaurantRepository.findByAnyCriteria(true,
                locationLatitude, locationLongitude, maxDistance, cuisine);

        List<Map<Object, Object>> results = Lists.newArrayListWithCapacity(geoResults.getContent().size());
        for(GeoResult<PersistenceRestaurant> geoResult : geoResults) {
            Map<Object, Object> result = Maps.newHashMap();
            result.put("restaurant", geoResult.getContent());
            result.put("distance", geoResult.getDistance().getValue());
            results.add(result);
        }
        return results;
    }

    @Override
    public PersistenceRestaurant findByFormattedAddress(String formattedAddress) {
        List<PersistenceRestaurant> restaurantList = restaurantRepository.findByLocationFormattedAddress(formattedAddress);
        if(restaurantList.size() == 1) {
            return restaurantList.get(0);
        } else if(restaurantList.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Multiple restaurants has been found for formattedAddress : " + formattedAddress);
        }
    }
}
