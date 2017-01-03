/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.util;

import com.geomenum.web.WebURLPath;
import com.geomenum.web.requestsresponses.ui.GetHeaderResponse;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * User Interface Utilities
 */
public class UIUtil {

    public static Map<String, Object> buildHeader(GetHeaderResponse response) {
        Map<String, Object> header = Maps.newHashMapWithExpectedSize(3);
        header.put("userId", response.getUserId());

        List<Map<String, String>> menuHeaderList = Lists.newArrayListWithCapacity(response.getHeaderData().size());
        List<Map<String, String>> restaurantHeaderList = Lists.newArrayListWithCapacity(response.getHeaderData().size());
        for(Map<String, String> headerData : response.getHeaderData()) {
            menuHeaderList.add(
                    ImmutableMap.of(
                            "text", headerData.get("restaurantName"),
                            "url", WebURLPath.getSubmenuURL(headerData.get("menuId"), headerData.get("menuRootId"))));
            restaurantHeaderList.add(
                    ImmutableMap.of(
                            "text", headerData.get("restaurantName"),
                            "url", WebURLPath.getRestaurantURL(headerData.get("restaurantId"))));
        }
        header.put("menu", menuHeaderList);
        header.put("restaurant", restaurantHeaderList);

        return header;
    }
}
