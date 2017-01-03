/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNode;
import com.geomenum.core.datamodel.tree.menu.MenuNodeContent;
import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.services.GenericCoreService;

import java.util.Map;
import java.util.UUID;

/**
 * This interfaces declares core services behavior common to domain object implementing the {@link MenuNodeContent} interface.
 */
interface MenuNodeContentCoreService<T extends MenuNodeContent> extends GenericCoreService<T> {

    /**
     * Creates a new menu node with the given DTO and adds it to the menu.
     *
     * @param newMenuNodeContentDTO the base data of the new menu node content
     * @param menuId the id of the menu to add the new content to
     * @param parentNodeId the id of the parent node which will embed the new node
     * @return the added menu node content
     */
    T add(Map<Object, Object> newMenuNodeContentDTO, String menuId, UUID parentNodeId);

    /**
     * Adds a new menu node content to the given menu as a child of the node with the given parent node id.
     *
     * @param menuNodeContent the menu node content to add
     * @param menu the menu to add the new content to
     * @param parentNodeId the id of the parent node which will embed the new node
     * @return the added menu node content
     */
    T add(T menuNodeContent, CoreMenu menu, UUID parentNodeId);


    /**
     * Retrieves the menu node content with the given id if found, returns {@code null} otherwise.
     *
     * @param nodeId the id of the menu node content to find
     * @param menuId the id of the menu embedding the targeted content
     * @return the found menu node content or {@code null}
     */
    T findById(UUID nodeId, String menuId);

    /**
     * Updates an existing menu node content with the given DTO.
     *
     * @param menuNodeContentDTO the menu node content DTO
     * @param menuId the menu containing the menu node content to update
     * @return the updated menu node content
     */
    T update(Map<Object, Object> menuNodeContentDTO, String menuId);

    /**
     * Updates an existing menu node content.
     *
     * @param menuNodeContent the menu node content to update
     * @param menu the menu containing the menu node content to update
     * @return the updated menu node content
     */
    T update(T menuNodeContent, CoreMenu menu);

    /**
     * Deletes an existing node content.
     *
     * @param menuNodeContentId the id of the menu node content to delete
     * @param menuId the id of the menu embedding the targeted content
     * @return the deleted menu node content
     */
    T delete(UUID menuNodeContentId, String menuId);

    /**
     * Moves a {@link MenuNode} up in the child list it belongs to.
     *
     * @param menuId the id of the menu of the targeted node
     * @param nodeId the id of the node to move (targeted node)
     * @return true if the move is successful, false otherwise
     */
    boolean moveUp(String menuId, UUID nodeId);

    /**
     * Moves a {@link MenuNode} down in the child list it belongs to.
     *
     * @param menuId the id of the menu of the targeted node
     * @param nodeId the id of the node to move (targeted node)
     * @return true if the move is successful, false otherwise
     */
    boolean moveDown(String menuId, UUID nodeId);

    /**
     * Moves a {@link MenuNode} to the child list of its parent.
     *
     * @param menuId the id of the menu of the targeted node
     * @param nodeId the id of the node to move (targeted node)
     * @return true if the move is successful, false otherwise
     */
    boolean moveToUpperLevel(String menuId, UUID nodeId);

    /**
     * Moves a {@link MenuNode} to any of its submenu's child list.
     *
     * @param menuId the id of the menu of the targeted node
     * @param nodeId the id of the node to move (targeted node)
     * @param siblingId the id of the node of the future parent of the targeted node
     * @return true if the move is successful, false otherwise
     */
    boolean moveToLowerLevel(String menuId, UUID nodeId, UUID siblingId);
}
