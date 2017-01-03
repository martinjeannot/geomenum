/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraintvalidators;

import com.geomenum.common.validation.constraints.LightPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.CharBuffer;
import java.util.regex.Pattern;

/**
 * Validator for the {@link LightPassword} constraint.
 */
public class LightPasswordValidator implements ConstraintValidator<LightPassword, char[]> {

    private static final Pattern LIGHT_PASSWORD_PATTERN = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[a-z]).*$");

    @Override
    public void initialize(LightPassword constraintAnnotation) {}

    @Override
    public boolean isValid(char[] value, ConstraintValidatorContext context) {
        if(value == null || value.length == 0) {
            return true;
        }

        return LIGHT_PASSWORD_PATTERN.matcher(CharBuffer.wrap(value)).matches();
    }
}
