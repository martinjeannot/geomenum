/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.ui;

import com.geomenum.r2d2.common.Response;

import java.util.List;
import java.util.Map;

/**
 * @see GetHeaderRequest
 */
public class GetHeaderResponse extends Response {

    private String userId;

    /**
     * Each {@link Map} holds the headerData of one restaurant.
     */
    private List<Map<String, String>> headerData;

    public GetHeaderResponse(String userId, List<Map<String, String>> headerData) {
        this.userId = userId;
        this.headerData = headerData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Map<String, String>> getHeaderData() {
        return headerData;
    }

    public void setHeaderData(List<Map<String, String>> headerData) {
        this.headerData = headerData;
    }
}
