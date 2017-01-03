/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest;

/**
 * This class contains the URIs of our REST API.<br/>
 * Some of these URIs are expressed as templates in order to address specific resources.
 */
public final class RestApiUri {

    public static final String API_ROOT_V1 = "/menum/api/v1";

    //~ RESTAURANT =====================================================================================================

    public static final String RESTAURANTS = API_ROOT_V1 + "/restaurants";
    public static final String RESTAURANT = RESTAURANTS + "/{restaurantId}";

    public static String getRestaurantURI(String restaurantId) {
        return RESTAURANT.replace("{restaurantId}", restaurantId);
    }

    //~ MENU ===========================================================================================================

    public static final String MENUS = RESTAURANT + "/menus";
    public static final String MENU = MENUS + "/{menuId}";

    public static String getMenusURI(String restaurantId) {
        return MENUS.replace("{restaurantId}", restaurantId);
    }

    public static String getMenuURI(String restaurantId, String menuId) {
        return MENU.replace("{restaurantId}", restaurantId).replace("{menuId}", menuId);
    }

    //~ UTIL ===========================================================================================================

    private static String restApiUri(String uri) {
        return API_ROOT_V1 + uri;
    }
}
