/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web;

import java.util.UUID;

/**
 * List of our web url paths.
 */
public final class WebURLPath {

    //~ COMMON =========================================================================================================

    public static final String PUBLIC_HOME = "/";
    public static final String PRIVATE_HOME = "/home";
    public static final String ERROR = "/error";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String SIGN_UP = "/signup";
    public static final String TOS = "/tos";
    public static final String PRIVACY = "/privacy";

    //~ USER ===========================================================================================================

    public static final String USER_ROOT = "/user/{userId}";

    public static String getUserURL(String userId) {
        return USER_ROOT.replace("{userId}", userId);
    }

    //~ RESTAURANT =====================================================================================================

    public static final String RESTAURANT_ROOT = "/restaurant/{restaurantId}";

    public static String getRestaurantURL(String restaurantId) {
        return RESTAURANT_ROOT.replace("{restaurantId}", restaurantId);
    }

    //~ MENU ===========================================================================================================

    public static final String MENU_ITEM_ROOT = "/menu/{menuId}/menuitem/{menuItemId}";
    public static final String SUBMENU_ROOT = "/menu/{menuId}/submenu/{submenuId}";


    public static String getMenuItemURL(String menuId, String menuItemId) {
        return MENU_ITEM_ROOT.replace("{menuId}", menuId).replace("{menuItemId}", menuItemId);
    }

    public static String getMenuItemURL(String menuId, UUID menuItemId) {
        return getMenuItemURL(menuId, menuItemId.toString());
    }

    public static String getSubmenuURL(String menuId, String submenuId) {
        return SUBMENU_ROOT.replace("{menuId}", menuId).replace("{submenuId}", submenuId);
    }

    public static String getSubmenuURL(String menuId, UUID submenuId) {
        return getSubmenuURL(menuId, submenuId.toString());
    }

    //~ RESERVATION ====================================================================================================

    public static final String RESERVATION = "/reservation";

    //~ UTIL ===========================================================================================================

    /**
     * Returns the String result of a redirection to the given URL.
     *
     * @param URLPath the URL to redirect to
     * @param queryStrings query Strings to be appended (must already be formatted e.g "field=value")
     * @return the redirection as a String
     */
    public static String redirect(String URLPath, String... queryStrings) {
        StringBuilder redirection = new StringBuilder("redirect:");
        redirection.append(URLPath);
        if(queryStrings.length == 0) {
            return redirection.toString();
        } else {
            redirection.append("?");
            for(String queryString : queryStrings) {
                redirection.append(queryString).append("&");
            }
            redirection.deleteCharAt(redirection.length() - 1);
            return redirection.toString();
        }
    }
}
