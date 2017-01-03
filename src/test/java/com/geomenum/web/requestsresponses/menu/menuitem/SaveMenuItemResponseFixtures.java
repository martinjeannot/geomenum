/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.ExceptionType;

public class SaveMenuItemResponseFixtures {

    public static SaveMenuItemResponse successResponse() {
        return new SaveMenuItemResponse();
    }

    public static SaveMenuItemResponse defaultResponse() {
        SaveMenuItemResponse response = new SaveMenuItemResponse();
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
