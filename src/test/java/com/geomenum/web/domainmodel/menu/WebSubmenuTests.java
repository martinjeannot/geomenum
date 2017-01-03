/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.config.TestingLevels.UNIT;
import static com.geomenum.web.domainmodel.menu.WebSubmenuFixtures.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(groups = {UNIT})
public class WebSubmenuTests {

    public void fromMap() {
        assertNotNull(WebSubmenu.of(newDTO()));
    }

    @Test(enabled = false) // UUID problem
    public void toMap() {
        assertEquals(standardSubmenu().toMap(), WebSubmenuFixtures.newDTO());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        WebSubmenu.of(newDTOWithoutId());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.web.domainmodel.menu.WebSubmenu\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidDto() {
        WebSubmenu.of(invalidDTO());
    }
}
