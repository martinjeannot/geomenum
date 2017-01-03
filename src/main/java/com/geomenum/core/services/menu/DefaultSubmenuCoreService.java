/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.geomenum.core.services.system.upload.UploadCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * {@link SubmenuCoreService} default implementation.
 */
@Service
public class DefaultSubmenuCoreService extends DefaultMenuNodeContentCoreService<CoreSubmenu> implements SubmenuCoreService {

    @Autowired
    public DefaultSubmenuCoreService(MenuCoreService menuCoreService, UploadCoreService uploadCoreService) {
        super(menuCoreService, uploadCoreService);
    }

    @Override
    public CoreSubmenu add(Map<Object, Object> newSubmenuDTO, String menuId, UUID parentNodeId) {
        // parameters validation
        Objects.requireNonNull(newSubmenuDTO, "Cannot add a submenu from a null submenu DTO");
        if (newSubmenuDTO.isEmpty()) {
            throw new IllegalArgumentException("Cannot add a submenu from an invalid submenu DTO");
        }
        if (menuId == null || menuId.isEmpty()) {
            throw new IllegalArgumentException("Cannot update a menu with a null or empty id");
        }

        // menu retrieval
        CoreMenu menuToUpdate = menuCoreService.findById(menuId);
        if(menuToUpdate == null) {
            throw new NullPointerException("Cannot find menu with id " + menuId);
        }

        // new submenu creation
        CoreSubmenu newSubmenu = CoreSubmenu.createNewSubmenu(newSubmenuDTO);

        return add(newSubmenu, menuToUpdate, parentNodeId);
    }

    @Override
    public CoreSubmenu update(Map<Object, Object> submenuDTO, String menuId) {
        // parameters validation
        Objects.requireNonNull(submenuDTO, "Cannot update a submenu from a null submenu DTO");
        if(submenuDTO.isEmpty()) {
            throw new IllegalArgumentException("Cannot update a submenu from an invalid submenu DTO");
        }
        if (menuId == null || menuId.isEmpty()) {
            throw new IllegalArgumentException("Cannot update a menu with a null or empty id");
        }

        // menu retrieval
        CoreMenu menuToUpdate = menuCoreService.findById(menuId);

        // original menu item retrieval
        CoreSubmenu originalSubmenu = menuToUpdate.getRoot().findSubmenuById(
                UUID.fromString((String) submenuDTO.get("id")));

        CoreSubmenu submenuToUpdate = originalSubmenu.merge(submenuDTO);

        return update(submenuToUpdate, menuToUpdate);
    }
}
