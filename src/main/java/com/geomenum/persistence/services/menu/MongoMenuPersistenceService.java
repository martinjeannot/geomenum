/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.menu;

import com.geomenum.persistence.domainmodel.menu.PersistenceMenu;
import com.geomenum.persistence.repositories.menu.MenuRepository;
import com.geomenum.persistence.services.MongoGenericPersistenceService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * {@link MenuPersistenceService} MongoDB implementation.
 */
@Service
public class MongoMenuPersistenceService extends MongoGenericPersistenceService<PersistenceMenu> implements MenuPersistenceService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected MongoRepository<PersistenceMenu, ObjectId> getRepository() {
        return menuRepository;
    }

    @Override
    protected PersistenceMenu getDomainObjectFromDTO(Map<Object, Object> dto) {
        return PersistenceMenu.of(dto);
    }

    @Override
    public PersistenceMenu findEnabledById(ObjectId id) {
        return menuRepository.findByIdAndEnabled(id, true);
    }
}
