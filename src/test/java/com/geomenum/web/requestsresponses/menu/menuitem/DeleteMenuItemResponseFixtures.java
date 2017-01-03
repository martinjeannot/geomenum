/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu.menuitem;

import com.geomenum.r2d2.common.ExceptionType;

public class DeleteMenuItemResponseFixtures {

    public static DeleteMenuItemResponse successResponse() {
        return new DeleteMenuItemResponse();
    }

    public static DeleteMenuItemResponse defaultResponse() {
        DeleteMenuItemResponse response = new DeleteMenuItemResponse();
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
