/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.ExceptionType;

public class GetSubmenuAndBreadcrumbsAndDashboardResponseFixtures {

    public static GetSubmenuResponse successResponse() {
        /*return new GetSubmenuResponse(
                WebSubmenuFixtures.standardSubmenu().toMap(),
                Lists.<Map<String, String>>newArrayList(),
                Lists.<Map<String, Object>>newArrayList());*/
        return null;
    }

    public static GetSubmenuResponse defaultResponse() {
        GetSubmenuResponse response = new GetSubmenuResponse(null);
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
