/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.services.DefaultGenericCoreService;
import com.geomenum.core.services.system.upload.UploadCoreService;
import com.geomenum.persistence.servicefacades.GenericPersistenceServiceFacade;
import com.geomenum.persistence.servicefacades.menu.MenuPersistenceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * {@link MenuCoreService} default implementation.
 */
@Service
public class DefaultMenuCoreService extends DefaultGenericCoreService<CoreMenu> implements MenuCoreService {

    private final MenuPersistenceServiceFacade menuPersistenceService;

    private final UploadCoreService uploadCoreService;

    @Autowired
    public DefaultMenuCoreService(MenuPersistenceServiceFacade menuPersistenceService,
                                  UploadCoreService uploadCoreService) {
        this.menuPersistenceService = menuPersistenceService;
        this.uploadCoreService = uploadCoreService;
    }

    @Override
    public CoreMenu findEnabledById(String id) {
        if(!PERSISTENCE_ID_VALIDATOR.isValid(id, null)) {
            throw new IllegalArgumentException("Invalid id : " + id);
        }
        return menuPersistenceService.findEnabledById(id);
    }

    @Override
    protected CoreMenu cascadeDelete(String id) {
        Objects.requireNonNull(id, "Cannot delete a menu with null id");
        CoreMenu menu = findById(id);
        Objects.requireNonNull(menu, "Cannot find the menu to delete (id : " + id + ")");

        // delete additional resources
        menu.getRoot().deleteImageIfAny(uploadCoreService);

        return delete(id);
    }

    //~ DefaultGenericCoreService ======================================================================================

    @Override
    protected GenericPersistenceServiceFacade<CoreMenu> getPersistenceService() {
        return menuPersistenceService;
    }
}
