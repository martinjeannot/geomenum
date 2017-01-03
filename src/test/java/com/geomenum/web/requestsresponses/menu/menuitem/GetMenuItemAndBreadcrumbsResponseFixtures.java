/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.ExceptionType;

public class GetMenuItemAndBreadcrumbsResponseFixtures {

    public static GetMenuItemResponse successResponse() {
        /*return new GetMenuItemResponse(
                WebMenuItemFixtures.standardMenuItem().toMap(),
                Lists.<Map<String, String>>newArrayList());*/
        return null;
    }

    public static GetMenuItemResponse defaultResponse() {
        GetMenuItemResponse response = new GetMenuItemResponse(null);
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
