/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.menu;

import org.testng.annotations.Test;

import javax.validation.ValidationException;
import java.util.Map;

import static com.geomenum.config.TestingLevels.UNIT;
import static com.geomenum.persistence.domainmodel.menu.PersistenceMenuFixtures.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(groups = {UNIT})
public class PersistenceMenuUnitTest {

    public void fromMap() {
        assertNotNull(PersistenceMenu.of(newDto()));
    }

    public void toMap() {
        /*
         * FIXME :
         * The menuTree property prevents us from doing a full value-check since
         * Jackson has its own UUID serializer which output does not really respect
         * the "equals" contract (since Jackson actually serializes UUID as bytes array)
         */
        assertEquals(standardMenu().toMap(), newDto());
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void createWithNullId() {
        Map<Object, Object> dto = newDto();
        dto.remove("id");
        PersistenceMenu.of(dto);
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.persistence.domainmodel.menu.PersistenceMenu\\] Validation failed :\n" +
                    "enabled : may not be null\n")
    public void createWithInvalidDto() {
        PersistenceMenu.of(invalidDto());
    }
}
