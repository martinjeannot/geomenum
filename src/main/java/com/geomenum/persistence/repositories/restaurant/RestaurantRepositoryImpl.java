/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.repositories.restaurant;

import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.NearQuery.near;
import static org.springframework.data.mongodb.core.query.Query.query;

public class RestaurantRepositoryImpl implements CustomRestaurantRepository {

    private final MongoOperations mongoOps;

    @Autowired
    public RestaurantRepositoryImpl(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    @Override
    public GeoResults<PersistenceRestaurant> findByAnyCriteria(boolean enabled,
                                                               double locationLatitude,
                                                               double locationLongitude,
                                                               double maxDistance,
                                                               Cuisine cuisine) {
        return mongoOps.geoNear(near(locationLatitude, locationLongitude).inKilometers().maxDistance(maxDistance)
                .query(query(where("enabled").is(enabled)
                        .and("cuisine").regex(cuisine == null ? "^" : cuisine.name()))),
                PersistenceRestaurant.class);
    }
}