/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.servicefacades.menu;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.persistence.converters.StringToPersistenceIdConverter;
import com.geomenum.persistence.domainmodel.menu.PersistenceMenu;
import com.geomenum.persistence.servicefacades.GenericPersistenceServiceFacade;
import com.geomenum.persistence.services.menu.MenuPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuPersistenceServiceFacade implements GenericPersistenceServiceFacade<CoreMenu> {

    @Autowired
    private MenuPersistenceService service;

    @Override
    public CoreMenu create(CoreMenu entity) {
        return CoreMenu.of(
                service.create(entity.toMap())
                        .toMap());
    }

    @Override
    public CoreMenu findById(String id) {
        PersistenceMenu menu = service.findById(StringToPersistenceIdConverter.convert(id));
        return menu != null ? CoreMenu.of(menu.toMap()) : null;
    }

    public CoreMenu findEnabledById(String id) {
        PersistenceMenu menu = service.findEnabledById(StringToPersistenceIdConverter.convert(id));
        return menu != null ? CoreMenu.of(menu.toMap()) : null;
    }

    @Override
    public CoreMenu update(CoreMenu entity) {
        return CoreMenu.of(
                service.update(entity.toMap())
                        .toMap());
    }

    @Override
    public CoreMenu delete(String id) {
        return CoreMenu.of(
                service.delete(StringToPersistenceIdConverter.convert(id))
                        .toMap());
    }
}
