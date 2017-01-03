/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.config.TestingLevels.UNIT;
import static com.geomenum.web.domainmodel.menu.WebMenuItemFixtures.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(groups = {UNIT})
public class WebMenuItemTests {

    public void fromMap() {
        assertNotNull(WebMenuItem.of(newDTO()));
    }

    @Test(enabled = false) // UUID problem
    public void toMap() {
        assertEquals(standardMenuItem().toMap(), WebMenuItemFixtures.newDTO());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        WebMenuItem.of(newDTOWithoutId());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.web.domainmodel.menu.WebMenuItem\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidDto() {
        WebMenuItem.of(invalidDTO());
    }
}
