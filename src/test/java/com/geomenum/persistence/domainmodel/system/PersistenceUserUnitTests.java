/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.system;

import org.testng.annotations.Test;

import javax.validation.ValidationException;
import java.util.Map;

import static com.geomenum.config.TestingLevels.UNIT;
import static com.geomenum.persistence.domainmodel.system.PersistenceUserFixtures.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(groups = {UNIT})
public class PersistenceUserUnitTests {

    public void fromMap() {
        assertNotNull(PersistenceUser.of(newDto()));
    }

    public void toMap() {
        assertEquals(standardUser().toMap(), newDto());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        Map<Object, Object> userData = newDto();
        userData.remove("id");
        PersistenceUser.of(userData);
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.system.PersistenceUser\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidDto() {
        PersistenceUser.of(invalidDto());
    }
}
