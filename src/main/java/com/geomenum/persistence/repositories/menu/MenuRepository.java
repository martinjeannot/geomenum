/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.repositories.menu;

import com.geomenum.persistence.domainmodel.menu.PersistenceMenu;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Menu repository.
 */
public interface MenuRepository extends MongoRepository<PersistenceMenu, ObjectId> {

    PersistenceMenu findByIdAndEnabled(ObjectId id, boolean enabled);
}
