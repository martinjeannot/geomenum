/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.services.system;

import com.geomenum.persistence.domainmodel.system.PersistenceUser;
import com.geomenum.persistence.services.GenericPersistenceService;

/**
 * User persistence service.
 */
public interface UserPersistenceService extends GenericPersistenceService<PersistenceUser> {

    /**
     * Retrieves the user with the given username if found, returns {@code null} otherwise.
     *
     * @param username the username of the user to find
     * @return the found user or {@code null}
     * @throws IllegalStateException if multiple users have been found
     */
    PersistenceUser findByUsername(String username);
}
