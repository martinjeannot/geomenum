/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraintvalidators;

import com.geomenum.common.validation.constraints.LightPassword;
import org.testng.annotations.Test;

import javax.validation.ConstraintValidator;

import static com.geomenum.config.TestingLevels.UNIT;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test(groups = {UNIT})
public class LightPasswordValidatorTests {

    private final ConstraintValidator<LightPassword, char[]> validator = new LightPasswordValidator();

    public void validPwd() {
        assertTrue(validator.isValid(new char[]{'p', 'a', 's', 's', 'w', '0', 'r', 'd'}, null));
    }

    public void nullIsValid() {
        assertTrue(validator.isValid(null, null));
    }

    public void blankIsValid() {
        assertTrue(validator.isValid(new char[]{}, null));
    }

    public void validPwdWithSpecialCharacter() {
        assertTrue(validator.isValid(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', ';'}, null));
    }

    public void invalidPwdBeingTooSmall() {
        assertFalse(validator.isValid(new char[]{'p', 'a', 's', 's', 'w', '0', 'r'}, null));
    }

    public void invalidPwdWithOnlyLetters() {
        assertFalse(validator.isValid(new char[]{'P', 'a', 's', 'S', 'w', 'O', 'r', 'D'}, null));
    }
}
