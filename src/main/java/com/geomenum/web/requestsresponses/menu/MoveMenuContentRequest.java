/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.requestsresponses.menu;

import com.geomenum.r2d2.common.Request;

import java.util.UUID;

/**
 * Move a menu content in the hierarchy of menu contents, either :<br/>
 * - {@link Direction.UP} : up in the list of its parent's children<br/>
 * - {@link Direction.DOWN} : down in the list of its parent's children<br/>
 * - {@link Direction.UPPER_LEVEL} : to its parent level<br/>
 * - {@link Direction.LOWER_LEVEL} : to one of its sibling's children list<br/>
 */
public class MoveMenuContentRequest extends Request {

    private String menuId;
    private UUID menuContentId;
    private Direction direction;
    private UUID siblingId;

    /**
     * Ctor to move :<br/>
     * - {@link Direction.UP}<br/>
     * - {@link Direction.DOWN}<br/>
     * - to {@link Direction.UPPER_LEVEL}<br/>
     *
     * @param menuId id of the targeted menu
     * @param menuContentId id of the menu content to move
     * @param direction the {@link Direction} to move the menu content to
     */
    public MoveMenuContentRequest(String menuId, String menuContentId, Direction direction) {
        this.menuId = menuId;
        this.menuContentId = UUID.fromString(menuContentId);
        this.direction = direction;
    }

    /**
     * Ctor to move :<br/>
     * - to {@link Direction.LOWER_LEVEL}<br/>
     *
     * @param menuId id of the targeted menu
     * @param menuContentId id of the menu content to move
     * @param siblingId id of the future parent of the menu content to move
     */
    public MoveMenuContentRequest(String menuId, String menuContentId, String siblingId) {
        this.menuId = menuId;
        this.menuContentId = UUID.fromString(menuContentId);
        this.direction = Direction.LOWER_LEVEL;
        this.siblingId = UUID.fromString(siblingId);
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public UUID getMenuContentId() {
        return menuContentId;
    }

    public void setMenuContentId(UUID menuContentId) {
        this.menuContentId = menuContentId;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public UUID getSiblingId() {
        return siblingId;
    }

    public void setSiblingId(UUID siblingId) {
        this.siblingId = siblingId;
    }

    public static enum Direction {
        UP,
        DOWN,
        UPPER_LEVEL,
        LOWER_LEVEL
    }
}
