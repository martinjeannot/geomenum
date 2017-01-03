/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.requestsresponses.restaurant;

import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.r2d2.common.Request;

/**
 * Retrieve all restaurants based on multiple criteria, the only required one being a location (that is a latitude and
 * longitude within a certain distance).<br/>
 * Other criteria will be added along the application life.
 */
public class GetRestaurantsRequest extends Request {

    private double locationLatitude;
    private double locationLongitude;
    private double maximumDistance;
    private Cuisine cuisine;

    public GetRestaurantsRequest(double locationLatitude,
                                 double locationLongitude,
                                 double maximumDistance,
                                 Cuisine cuisine) {
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.maximumDistance = maximumDistance;
        this.cuisine = cuisine;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public double getMaximumDistance() {
        return maximumDistance;
    }

    public void setMaximumDistance(double maximumDistance) {
        this.maximumDistance = maximumDistance;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }
}
