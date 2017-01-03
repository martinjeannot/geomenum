/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.servicefacades.system;

import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.persistence.converters.StringToPersistenceIdConverter;
import com.geomenum.persistence.domainmodel.system.PersistenceUser;
import com.geomenum.persistence.servicefacades.GenericPersistenceServiceFacade;
import com.geomenum.persistence.services.system.UserPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceServiceFacade implements GenericPersistenceServiceFacade<CoreUser> {

    @Autowired
    private UserPersistenceService service;

    public CoreUser findByUsername(String username) {
        PersistenceUser user = service.findByUsername(username);
        return user != null ? CoreUser.of(user.toMap()) : null;
    }

    @Override
    public CoreUser create(CoreUser entity) {
        return CoreUser.of(
                service.create(entity.toMap())
                        .toMap());
    }

    @Override
    public CoreUser findById(String id) {
        PersistenceUser user = service.findById(StringToPersistenceIdConverter.convert(id));
        return user != null ? CoreUser.of(user.toMap()) : null;
    }

    @Override
    public CoreUser update(CoreUser entity) {
        return CoreUser.of(
                service.update(entity.toMap())
                        .toMap());
    }

    @Override
    public CoreUser delete(String id) {
        return CoreUser.of(
                service.delete(StringToPersistenceIdConverter.convert(id))
                        .toMap());
    }
}
