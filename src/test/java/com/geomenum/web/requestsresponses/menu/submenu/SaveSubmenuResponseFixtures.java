/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.ExceptionType;

public class SaveSubmenuResponseFixtures {

    public static SaveSubmenuResponse successResponse() {
        return new SaveSubmenuResponse();
    }

    public static SaveSubmenuResponse defaultResponse() {
        SaveSubmenuResponse response = new SaveSubmenuResponse();
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
