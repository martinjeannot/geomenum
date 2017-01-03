/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.repositories.restaurant;

import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomRestaurantRepository {

    GeoResults<PersistenceRestaurant> findByAnyCriteria(
            boolean enabled,
            double locationLatitude,
            double locationLongitude,
            double maxDistance,
            Cuisine cuisine);
}
