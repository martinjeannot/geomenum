/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu;

import com.geomenum.r2d2.common.Response;

/**
 * @see MoveMenuContentRequest
 */
public class MoveMenuContentResponse extends Response {

    private boolean moveSuccessful;

    public MoveMenuContentResponse(boolean moveSuccessful) {
        this.moveSuccessful = moveSuccessful;
    }

    public boolean isMoveSuccessful() {
        return moveSuccessful;
    }

    public void setMoveSuccessful(boolean moveSuccessful) {
        this.moveSuccessful = moveSuccessful;
    }
}
