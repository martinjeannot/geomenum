/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.menu;

import com.geomenum.persistence.domainmodel.menu.PersistenceMenu;
import com.geomenum.persistence.services.GenericPersistenceService;
import org.bson.types.ObjectId;

/**
 * Menu persistence service.
 */
public interface MenuPersistenceService extends GenericPersistenceService<PersistenceMenu> {

    /**
     * Retrieves the menu with the given id if it is enabled, returns {@code null} if not found or disabled.
     *
     * @param id the id of the menu
     * @return a {@link PersistenceMenu} or {@code null}
     */
    PersistenceMenu findEnabledById(ObjectId id);
}
