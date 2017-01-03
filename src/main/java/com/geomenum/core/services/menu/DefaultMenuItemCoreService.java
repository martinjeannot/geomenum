/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.core.services.system.upload.UploadCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * {@link MenuItemCoreService} default implementation.
 */
@Service
public class DefaultMenuItemCoreService extends DefaultMenuNodeContentCoreService<CoreMenuItem> implements MenuItemCoreService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMenuItemCoreService.class);

    @Autowired
    public DefaultMenuItemCoreService(MenuCoreService menuCoreService, UploadCoreService uploadCoreService) {
        super(menuCoreService, uploadCoreService);
    }

    @Override
    public CoreMenuItem add(Map<Object, Object> newMenuItemDTO, String menuId, UUID parentNodeId) {
        // parameters validation
        Objects.requireNonNull(newMenuItemDTO, "Cannot add a menu item from a null menu item DTO");
        if (newMenuItemDTO.isEmpty()) {
            throw new IllegalArgumentException("Cannot add a menu item from an invalid menu item DTO");
        }
        if (menuId == null || menuId.isEmpty()) {
            throw new IllegalArgumentException("Cannot update a menu with a null or empty id");
        }

        // menu retrieval
        CoreMenu menuToUpdate = menuCoreService.findById(menuId);

        // new menu item creation
        CoreMenuItem newMenuItem = CoreMenuItem.createNewMenuItem(newMenuItemDTO, menuToUpdate);

        // image upload
        newMenuItem.uploadImageIfAny(uploadCoreService);

        return add(newMenuItem, menuToUpdate, parentNodeId);
    }

    @Override
    public CoreMenuItem update(Map<Object, Object> menuItemDTO, String menuId) {
        // parameters validation
        Objects.requireNonNull(menuItemDTO, "Cannot update a menu item from a null menu item DTO");
        if (menuItemDTO.isEmpty()) {
            throw new IllegalArgumentException("Cannot update a menu item from an invalid menu item DTO");
        }
        if (menuId == null || menuId.isEmpty()) {
            throw new IllegalArgumentException("Cannot update a menu with a null or empty id");
        }

        // menu retrieval
        CoreMenu menuToUpdate = menuCoreService.findById(menuId);

        // original menu item retrieval
        CoreMenuItem originalMenuItem = menuToUpdate.getRoot().findMenuItemById(
                UUID.fromString((String) menuItemDTO.get("id")));

        CoreMenuItem menuItemToUpdate = originalMenuItem.merge(menuItemDTO);

        // image upload
        menuItemToUpdate.uploadImageIfAny(uploadCoreService);

        return update(menuItemToUpdate, menuToUpdate);
    }

    @Override
    protected void deleteAdditionalResources(CoreMenuItem menuItem) {
        menuItem.deleteImageIfAny(uploadCoreService);
    }
}
