/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu;

import com.geomenum.r2d2.common.ExceptionType;

public class MoveMenuContentResponseFixtures {

    public static MoveMenuContentResponse successResponse() {
        return new MoveMenuContentResponse(true);
    }

    public static MoveMenuContentResponse defaultResponse() {
        MoveMenuContentResponse response = new MoveMenuContentResponse(false);
        response.setException(new NullPointerException());
        response.setExceptionType(ExceptionType.UNKNOWN);
        return response;
    }
}
