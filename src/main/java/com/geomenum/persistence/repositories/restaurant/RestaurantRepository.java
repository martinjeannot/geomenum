/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.repositories.restaurant;

import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import org.bson.types.ObjectId;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Restaurant repository.
 */
public interface RestaurantRepository extends MongoRepository<PersistenceRestaurant, ObjectId>, CustomRestaurantRepository {

    GeoResults<PersistenceRestaurant> findByGeolocationNear(Point geolocation, Distance distance);

    List<PersistenceRestaurant> findByLocationFormattedAddress(String formattedAddress);
}
