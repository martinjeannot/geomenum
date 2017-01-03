/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.submenu;

import com.geomenum.r2d2.common.ExceptionType;

public class DeleteSubmenuResponseFixtures {

    public static DeleteSubmenuResponse successResponse() {
        return new DeleteSubmenuResponse();
    }

    public static DeleteSubmenuResponse defaultResponse() {
        DeleteSubmenuResponse response = new DeleteSubmenuResponse();
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
