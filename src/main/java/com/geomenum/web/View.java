/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web;

/**
 * List of all the views exposed by our controllers.
 */
public final class View {

    //~ COMMON =========================================================================================================

    public static final String PUBLIC_HOME = view("public_home");
    public static final String PRIVATE_HOME = view("private_home");
    public static final String ERROR = view("error");
    public static final String LOGIN = view("login");
    public static final String SIGN_UP = view("system/registration/signup");
    public static final String TOS = view("tos");
    public static final String PRIVACY = view("privacy");

    //~ USER ===========================================================================================================

    public static final String USER = view("system/user");

    //~ RESTAURANT =====================================================================================================

    public static final String RESTAURANT = view("restaurant/restaurant");

    //~ MENU ===========================================================================================================

    public static final String MENU_ITEM = view("menu/menu_item");
    public static final String SUBMENU = view("menu/submenu");

    // RESERVATION =====================================================================================================

    public static final String RESERVATION = view("reservation/reservation");

    //~ UTIL ===========================================================================================================

    /**
     * Computes the full view path.
     *
     * @param viewPath the last part of the view path
     * @return the full view path
     * @see com.geomenum.config.servletcontext.WebConfiguration#templateResolver()
     */
    private static String view(String viewPath) {
        return "views/" + viewPath;
    }
}
