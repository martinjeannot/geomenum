/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.repositories;

import com.geomenum.config.PersistenceTestConfiguration;
import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurant;
import com.geomenum.persistence.repositories.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.geomenum.config.TestingLevels.INTEGRATION;
import static com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurantFixtures.standardRestaurant;
import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = {PersistenceTestConfiguration.class})
@Test(groups = {INTEGRATION})
public class RestaurantRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MongoOperations mongoOps;

    @BeforeClass
    public void setUpBeforeClass() {
        mongoOps.remove(new Query(), DBCollection.RESTAURANTS);
    }

    @AfterMethod
    public void tearDown() {
        mongoOps.remove(new Query(), DBCollection.RESTAURANTS);
    }

    public void save() {
        restaurantRepository.save(standardRestaurant());

        assertEquals(restaurantRepository.findAll().size(), 1);
    }

    public void find() {
        PersistenceRestaurant restaurant = standardRestaurant();
        mongoOps.insert(restaurant);
        PersistenceRestaurant retrievedRestaurant = restaurantRepository.findOne(restaurant.getId());

        assertEquals(retrievedRestaurant, restaurant);
        assertEquals(retrievedRestaurant.hashCode(), restaurant.hashCode());
        assertEquals(retrievedRestaurant.toString(), restaurant.toString());
    }

    //~ findByGeolocationNear ==========================================================================================

    public void findByGeolocationNear() {
        PersistenceRestaurant restaurant = standardRestaurant();
        mongoOps.insert(restaurant);

        Point pigalleSubwayStation = new Point(48.882201, 2.337574);
        Distance distance = new Distance(0.5, Metrics.KILOMETERS);
        GeoResults<PersistenceRestaurant> results = restaurantRepository.findByGeolocationNear(pigalleSubwayStation, distance);

        assertEquals(results.getContent().size(), 1);
    }
}
