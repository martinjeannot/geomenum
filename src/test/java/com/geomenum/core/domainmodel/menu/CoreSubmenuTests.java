/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.geomenum.core.domainmodel.AbstractCoreDomainModelUnitTests;
import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.core.domainmodel.menu.CoreSubmenuFixtures.*;
import static org.testng.Assert.*;

public class CoreSubmenuTests extends AbstractCoreDomainModelUnitTests {

    public void fromMap() {
        assertNotNull(CoreSubmenu.of(CoreSubmenuFixtures.newDto()));
    }

    public void toMap() {
        assertEquals(standardSubmenu().toMap(), CoreSubmenuFixtures.newDto());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        CoreSubmenu.of(newDtoWithoutId());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.core.domainmodel.menu.CoreSubmenu\\] Validation failed :\n" +
                    "localizedNames : may not be empty\n")
    public void createWithInvalidDto() {
        assertNull(CoreSubmenu.of(invalidDto()));
    }

    /*public void createNewSubmenu() {
        assertNotNull(CoreSubmenu.createNewSubmenu(MESSAGE_SOURCE, Lists.newArrayList(Locale.ENGLISH)));
    }*/
}
