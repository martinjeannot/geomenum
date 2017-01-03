/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.geomenum.core.domainmodel.AbstractCoreDomainModelUnitTests;
import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.core.domainmodel.menu.CoreMenuItemFixtures.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CoreMenuItemTests extends AbstractCoreDomainModelUnitTests {

    public void fromMap() {
        assertNotNull(CoreMenuItem.of(CoreMenuItemFixtures.newDTO()));
    }

    public void toMap() {
        assertEquals(standardMenuItem().toMap(), CoreMenuItemFixtures.newDTO());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        CoreMenuItem.of(newDTOWithoutId());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.core.domainmodel.menu.CoreMenuItem\\] Validation failed :\n" +
                    "localizedNames : may not be empty\n")
    public void createWithInvalidDto() {
        CoreMenuItem.of(invalidDTO());
    }

    /*public void createNewMenuItem() {
        assertNotNull(CoreMenuItem.createNewMenuItem(
                MESSAGE_SOURCE,
                Lists.newArrayList(Locale.ENGLISH),
                Currency.getInstance("USD")));
    }*/
}
