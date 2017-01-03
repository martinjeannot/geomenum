/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.system;

import com.geomenum.persistence.domainmodel.system.PersistenceUser;
import com.geomenum.persistence.repositories.system.UserRepository;
import com.geomenum.persistence.services.MongoGenericPersistenceService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * {@link UserPersistenceService} MongoDB implementation.
 */
@Service
public class MongoUserPersistenceService extends MongoGenericPersistenceService<PersistenceUser> implements UserPersistenceService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected MongoRepository<PersistenceUser, ObjectId> getRepository() {
        return userRepository;
    }

    @Override
    protected PersistenceUser getDomainObjectFromDTO(Map<Object, Object> dto) {
        return PersistenceUser.of(dto);
    }

    @Override
    public PersistenceUser findByUsername(String username) {
        List<PersistenceUser> userList = userRepository.findByUsername(username);
        if(userList.size() == 1) {
            return userList.get(0);
        } else if(userList.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Multiple users has been found for username : " + username);
        }
    }
}
