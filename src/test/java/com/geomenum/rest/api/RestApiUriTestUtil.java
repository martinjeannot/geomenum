/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.api;

import com.geomenum.rest.RestApiUri;

/**
 * This utility class can build REST URI based on {@link com.geomenum.rest.RestApiUri} to test our API.
 */
public class RestApiUriTestUtil {

    //~ RESTAURANT =====================================================================================================

    /*public static String getRestaurantURI(String restaurantId) {
        return RestApiUri.RESTAURANT.replace("{restaurantId}", restaurantId);
    }*/

    //~ MENU ===========================================================================================================

    public static String getMenuURI(String restaurantId, String menuId) {
        return RestApiUri.MENU.replace("{restaurantId}", restaurantId).replace("{menuId}", menuId);
    }
}
