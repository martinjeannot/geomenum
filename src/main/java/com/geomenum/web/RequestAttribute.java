/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web;

/**
 * List of all our {@link org.springframework.web.servlet.mvc.support.RedirectAttributes}
 * (also used in some failure case as web request attribute, without redirection)
 */
public final class RequestAttribute {

    public static final String MESSAGE = "message";

    //~ Names ==========================================================================================================

    public final class Name {

        // "Create" messages
        public static final String MENU_ITEM_CREATION_MESSAGE = "menuItemCreationMessage";
        public static final String SUBMENU_CREATION_MESSAGE = "submenuCreationMessage";

        // "Update" messages
        public static final String SAVE_MESSAGE = "saveMessage";

        // "Delete" messages
        public static final String MENU_ITEM_DELETION_MESSAGE = "menuItemDeletionMessage";
        public static final String SUBMENU_DELETION_MESSAGE = "submenuDeletionMessage";

        // "Move" messages
        public static final String MOVE_MESSAGE = "moveMessage";

        // "Login" messages
        public static final String LOGIN_MESSAGE = "loginMessage";
    }

    //~ Values =========================================================================================================

    public static final class Value {

        public static final String SUCCESS = "success";
        public static final String FAILURE = "failure";
        public static final String EXCEPTION = "exception";

        // auth
        public static final String ERROR = "error";
        public static final String LOGOUT = "logout";
    }
}
