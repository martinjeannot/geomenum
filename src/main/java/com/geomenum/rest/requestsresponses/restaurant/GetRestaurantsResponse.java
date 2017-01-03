/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.requestsresponses.restaurant;

import com.geomenum.r2d2.common.Response;

import java.util.List;
import java.util.Map;

/**
 * Each result map contains :<br/>
 * - restaurantDTO : a restaurant DTO<br/>
 * - distance : the distance between the restaurant and the given location<br/>
 *
 * @see GetRestaurantsRequest
 */
public class GetRestaurantsResponse extends Response {

    private List<Map<Object, Object>> results;

    public GetRestaurantsResponse(List<Map<Object, Object>> results) {
        this.results = results;
    }

    public List<Map<Object, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<Object, Object>> results) {
        this.results = results;
    }
}
