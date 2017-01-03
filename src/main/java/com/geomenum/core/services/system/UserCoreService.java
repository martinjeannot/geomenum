/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system;

import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.services.GenericCoreService;

import java.util.Map;

/**
 * User core service.
 */
public interface UserCoreService extends GenericCoreService<CoreUser> {

    CoreUser findUserByUsername(String username);

    CoreUser update(Map<Object, Object> userDTO);

    void deleteUserAccount(String username);
}
