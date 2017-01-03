/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.services.GenericCoreService;

/**
 * Menu core service.
 */
public interface MenuCoreService extends GenericCoreService<CoreMenu> {

    CoreMenu findEnabledById(String id);
}
