/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraintvalidators;

import com.geomenum.common.validation.constraints.PersistenceId;
import org.testng.annotations.Test;

import javax.validation.ConstraintValidator;

import static com.geomenum.config.TestingLevels.UNIT;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test(groups = {UNIT})
public class PersistenceIDValidatorTests {

    private final ConstraintValidator<PersistenceId, CharSequence> validator = new PersistenceIdValidator();

    public void validId() {
        assertTrue(validator.isValid("JhLy5b2L2cckN6R7C4moIS5Z05QyTZrBCaWUm_KLOM4", null));
    }

    public void nullIsValid() {
        assertTrue(validator.isValid(null, null));
    }

    public void blankIsValid() {
        assertTrue(validator.isValid("", null));
    }

    public void invalidIdWithWrongLength() {
        assertFalse(validator.isValid("JhLy5b2L2cckN6R7C4moIS5Z05QyTZrBCaWUm_KLOM", null));
    }

    public void invalidIdWithNoBase64Encoding() {
        assertFalse(validator.isValid("JhLy5b2L2cckN6R7C4moIS5Z05QyTZrBCaWUm_KLOM\0", null));
    }
}
