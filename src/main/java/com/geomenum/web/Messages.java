/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web;

/**
 * List of our all messages returned as attributes to our views.
 */
public final class Messages {

    //~ Common =========================================================================================================

    public static final String GENERIC_ERROR = errorStyle("error.genericError");
    public static final String ERROR_ON_SAVE = errorStyle("error.errorOnSave");
    public static final String ERROR_ON_CREATE = errorStyle("error.errorOnCreate");
    public static final String ERROR_ON_DELETE = errorStyle("error.errorOnDelete");

    /**
     * Everything went fine (add a "success" style to the message).
     */
    private static String successStyle(String messageKey) {
        return messageKey + "~success";
    }

    /**
     * Some recoverable error(s) occurred, further action needed (add a "warning" style to the message).
     */
    private static String warningStyle(String messageKey) {
        return messageKey + "~warning";
    }

    /**
     * Some unrecoverable error(s) occurred (add an "error" style to the message).
     */
    private static String errorStyle(String messageKey) {
        return messageKey + "~error";
    }

    //~ Public =========================================================================================================

    public static class Login {

        public static final String INVALID_CREDENTIALS = viewPrefix(errorStyle("invalidCredentials"));
        public static final String LOGOUT = viewPrefix(successStyle("logout"));

        private static String viewPrefix(String messageKey) {
            return "login." + messageKey;
        }
    }

    public static class SignUp {

        public static final String SUCCESS_ON_REGISTRATION = viewPrefix(successStyle("successOnRegistration"));
        public static final String WARNING_ON_REGISTRATION = viewPrefix(warningStyle("warningOnRegistration"));
        public static final String WARNING_ON_CAPTCHA = viewPrefix(warningStyle("warningOnCaptcha"));
        public static final String WARNING_ON_USERNAME = viewPrefix(warningStyle("warningOnUsername"));
        public static final String ERROR_ON_REGISTRATION = viewPrefix(errorStyle("errorOnRegistration"));

        public static final String SUCCESS_ON_ACCOUNT_CONFIRMATION = viewPrefix(successStyle("successOnAccountConfirmation"));
        public static final String ERROR_ON_ACCOUNT_CONFIRMATION = viewPrefix(errorStyle("errorOnAccountConfirmation"));

        private static String viewPrefix(String messageKey) {
            return "signup." + messageKey;
        }
    }

    //~ System =========================================================================================================

    public static class User {

        public static final String SUCCESS_ON_SAVE = viewPrefix(successStyle("successOnSave"));
        public static final String WARNING_ON_SAVE = viewPrefix(warningStyle("warningOnSave"));

        public static final String CONFIRMATION_ON_SAVE = viewPrefix(successStyle("confirmationOnSave"));

        public static final String SUCCESS_ON_DELETE = viewPrefix(successStyle("successOnDelete"));
        public static final String ERROR_ON_DELETE = viewPrefix(errorStyle("errorOnDelete"));

        private static String viewPrefix(String messageKey) {
            return "user." + messageKey;
        }
    }

    //~ Restaurant =====================================================================================================

    public static class Restaurant {

        public static final String SUCCESS_ON_SAVE = viewPrefix(successStyle("successOnSave"));
        public static final String WARNING_ON_SAVE = viewPrefix(warningStyle("warningOnSave"));


         private static String viewPrefix(String messageKey) {
             return "restaurant." + messageKey;
         }
    }

    //~ Menu ===========================================================================================================

    public static class MenuItem {

        public static final String SUCCESS_ON_SAVE = viewPrefix(successStyle("successOnSave"));
        public static final String WARNING_ON_SAVE = viewPrefix(warningStyle("warningOnSave"));

        public static final String SUCCESS_ON_CREATE = viewPrefix(successStyle("successOnCreate"));
        public static final String ERROR_ON_CREATE = viewPrefix(errorStyle("errorOnCreate"));

        public static final String SUCCESS_ON_DELETE = viewPrefix(successStyle("successOnDelete"));
        public static final String ERROR_ON_DELETE = viewPrefix(errorStyle("errorOnDelete"));

        private static String viewPrefix(String messageKey) {
            return "menuItem." + messageKey;
        }
    }

    public static class Submenu {

        public static final String SUCCESS_ON_SAVE = viewPrefix(successStyle("successOnSave"));
        public static final String WARNING_ON_SAVE = viewPrefix(warningStyle("warningOnSave"));

        public static final String SUCCESS_ON_CREATE = viewPrefix(successStyle("successOnCreate"));
        public static final String ERROR_ON_CREATE = viewPrefix(errorStyle("errorOnCreate"));

        public static final String SUCCESS_ON_DELETE = viewPrefix(successStyle("successOnDelete"));
        public static final String ERROR_ON_DELETE = viewPrefix(errorStyle("errorOnDelete"));

        //~ Submenu Dashboard

        public static final String SUCCESS_ON_MOVE = viewPrefix(successStyle("dashboard.successOnMove"));
        public static final String ERROR_ON_MOVE = viewPrefix(errorStyle("dashboard.errorOnMove"));

        private static String viewPrefix(String messageKey) {
            return "submenu." + messageKey;
        }
    }
}
