/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.system;

import com.geomenum.core.domainmodel.AbstractCoreDomainModelUnitTests;
import org.testng.annotations.Test;

import javax.validation.ValidationException;

import static com.geomenum.core.domainmodel.system.CoreUserFixtures.*;
import static org.testng.Assert.*;

public class CoreUserTests extends AbstractCoreDomainModelUnitTests {

    public void fromMap() {
        assertNotNull(CoreUser.of(newDTO()));
    }

    public void toMap() {
        assertEquals(standardUser().toMap(), newDTO());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        CoreUser.of(newDTOWithoutId());
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.core.domainmodel.system.CoreUser\\] Validation failed :\n" +
                    "lastName : may not be empty\n")
    public void createWithInvalidDto() {
        assertNull(CoreUser.of(invalidDTO()));
    }

    /*public void createNewUserFromRegistrationForm() {
        assertNotNull(CoreUser.createNewUserFromRegistrationForm(
                CoreUserFixtures.registrationFormDTO(),
                PERSISTENCE_ID_GENERATOR));
    }*/
}
