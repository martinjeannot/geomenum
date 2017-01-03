/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.datamodel.tree.menu.BranchMenuNode;
import com.geomenum.core.datamodel.tree.menu.LeafMenuNode;
import com.geomenum.core.datamodel.tree.menu.MenuNode;
import com.geomenum.core.datamodel.tree.menu.MenuNodeContent;
import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.geomenum.core.services.DefaultGenericCoreService;
import com.geomenum.core.services.system.upload.UploadCoreService;
import com.geomenum.persistence.servicefacades.GenericPersistenceServiceFacade;

import java.util.Objects;
import java.util.UUID;

/**
 * {@link MenuNodeContentCoreService} abstract default implementation.
 */
abstract class DefaultMenuNodeContentCoreService<T extends MenuNodeContent> extends DefaultGenericCoreService<T> implements MenuNodeContentCoreService<T> {

    protected final MenuCoreService menuCoreService;
    protected final UploadCoreService uploadCoreService;

    protected DefaultMenuNodeContentCoreService(MenuCoreService menuCoreService, UploadCoreService uploadCoreService) {
        this.menuCoreService = menuCoreService;
        this.uploadCoreService = uploadCoreService;
    }

    //~ Add ============================================================================================================

    @Override
    public T add(T menuNodeContent, CoreMenu menu, UUID parentNodeId) {
        // parameters validation
        Objects.requireNonNull(menuNodeContent, "Cannot add a null menu node content");
        Objects.requireNonNull(menu, "Cannot update a null menu");
        Objects.requireNonNull(parentNodeId, "Cannot find parent node with null id");

        // parent node retrieval
        MenuNode parentNode = menu.getRoot().findNodeById(parentNodeId);
        if(parentNode == null) {
            throw new NullPointerException("Cannot find parent node with id " + parentNodeId);
        } else if(!parentNode.allowsChildren()) {
            throw new IllegalArgumentException("Given parent node does not allow children (id : " + parentNodeId + ")");
        }

        // new menu node creation
        MenuNode newChildNode = null;
        if(menuNodeContent instanceof CoreMenuItem) {
            newChildNode = new LeafMenuNode((CoreMenuItem) menuNodeContent);
        } else if(menuNodeContent instanceof CoreSubmenu) {
            newChildNode = new BranchMenuNode((CoreSubmenu) menuNodeContent);
        }

        // new menu node addition
        if(parentNode.addChild(newChildNode)) {
            CoreMenu updatedMenu = menuCoreService.update(menu);
            return (T) updatedMenu.getRoot().findNodeById(menuNodeContent.getId()).getContent();
        } else {
            throw new IllegalStateException("Couldn't add a new child node to the parent node with id " + parentNodeId);
        }
    }

    //~ Find by ========================================================================================================

    @Override
    public T findById(UUID nodeId, String menuId) {
        MenuNode foundNode = findNodeById(menuId, nodeId);
        if(foundNode != null) {
            return (T) foundNode.getContent();
        } else {
            return null;
        }
    }

    protected MenuNode findNodeById(String menuId, UUID nodeId) {
        CoreMenu foundMenu = menuCoreService.findById(menuId);
        if(foundMenu == null) {
            throw new NullPointerException("Cannot find menu with id " + menuId);
        }
        return foundMenu.getRoot().findNodeById(nodeId);
    }

    //~ Update =========================================================================================================

    @Override
    public T update(T menuNodeContent, CoreMenu menu) {
        // parameters validation
        Objects.requireNonNull(menuNodeContent, "Cannot update a null menu node content");
        Objects.requireNonNull(menu, "Cannot update a null menu");

        MenuNode nodeToUpdate = menu.getRoot().findNodeById(menuNodeContent.getId());
        nodeToUpdate.setContent(menuNodeContent); // update
        CoreMenu updatedMenu = menuCoreService.update(menu);
        return (T) updatedMenu.getRoot().findNodeById(menuNodeContent.getId()).getContent();
    }

    //~ Delete =========================================================================================================

    @Override
    public T delete(UUID menuNodeContentId, String menuId) {
        // parameters validation
        Objects.requireNonNull(menuNodeContentId, "Cannot find menu node content with null id");
        if (menuId == null || menuId.isEmpty()) {
            throw new IllegalArgumentException("Cannot find menu with null or empty id");
        }

        // menu retrieval
        CoreMenu menuToUpdate = menuCoreService.findById(menuId);
        Objects.requireNonNull(menuToUpdate, "Cannot find menu with id : " + menuId);

        // targeted node retrieval
        MenuNode nodeToDelete = menuToUpdate.getRoot().findNodeById(menuNodeContentId);
        Objects.requireNonNull(nodeToDelete, "Cannot find menu node content with id : " + menuNodeContentId);

        // proceed to additional deletion if necessary
        deleteAdditionalResources((T) nodeToDelete.getContent());

        // deletion from parent node
        MenuNode parentNode = nodeToDelete.getParentAsMenuNode();
        if(parentNode.removeChild(nodeToDelete)) {
            menuCoreService.update(menuToUpdate);
            return (T) nodeToDelete.getContent();
        } else {
            throw new IllegalStateException("Couldn't remove the child node " + menuNodeContentId
                    + " from parent node " + parentNode.getContent().getId());
        }
    }

    protected void deleteAdditionalResources(T menuNodeContent) {}

    //~ Move ===========================================================================================================

    @Override
    public boolean moveUp(String menuId, UUID nodeId) {
        return moveMenuNode(menuId, nodeId, Direction.UP, null);
    }

    @Override
    public boolean moveDown(String menuId, UUID nodeId) {
        return moveMenuNode(menuId, nodeId, Direction.DOWN, null);
    }

    @Override
    public boolean moveToUpperLevel(String menuId, UUID nodeId) {
        return moveMenuNode(menuId, nodeId, Direction.UPPER_LEVEL, null);
    }

    @Override
    public boolean moveToLowerLevel(String menuId, UUID nodeId, UUID siblingId) {
        return moveMenuNode(menuId, nodeId, Direction.LOWER_LEVEL, siblingId);
    }

    private boolean moveMenuNode(String menuId, UUID nodeId, Direction direction, UUID siblingId) {
        CoreMenu menuToUpdate = menuCoreService.findById(menuId);
        if(menuToUpdate == null) {
            throw new NullPointerException("Cannot find menu with id " + menuId);
        }
        MenuNode nodeToMove = menuToUpdate.getRoot().findNodeById(nodeId);
        if(nodeToMove == null) {
            throw new NullPointerException("Cannot find node with id " + nodeId);
        }
        boolean successfulMove = false;
        switch(direction) {
            case UP:
                MenuNode parentNode = nodeToMove.getParentAsMenuNode();
                successfulMove = parentNode.moveUpChild(nodeToMove);
                break;
            case DOWN:
                parentNode = nodeToMove.getParentAsMenuNode();
                successfulMove = parentNode.moveDownChild(nodeToMove);
                break;
            case UPPER_LEVEL:
                successfulMove = nodeToMove.moveToUpperLevel();
                break;
            case LOWER_LEVEL:
                MenuNode siblingNode = menuToUpdate.getRoot().findNodeById(siblingId);
                successfulMove = nodeToMove.moveToLowerLevel(siblingNode);
                break;
        }
        if(successfulMove) {
            // update the menu
            menuCoreService.update(menuToUpdate);
            return true;
        } else {
            return false;
        }
    }

    private enum Direction {
        UP,
        DOWN,
        UPPER_LEVEL,
        LOWER_LEVEL
    }

    //~ DefaultGenericCoreService ======================================================================================

    @Override
    protected GenericPersistenceServiceFacade<T> getPersistenceService() {
        throw new UnsupportedOperationException();
    }
}
