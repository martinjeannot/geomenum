/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.repositories.system;

import com.geomenum.persistence.domainmodel.system.PersistenceUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * User repository.
 */
public interface UserRepository extends MongoRepository<PersistenceUser, ObjectId> {

    List<PersistenceUser> findByUsername(String username);
}
