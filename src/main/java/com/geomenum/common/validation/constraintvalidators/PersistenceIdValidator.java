/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraintvalidators;

import com.geomenum.common.validation.constraints.PersistenceId;
import org.apache.commons.codec.binary.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for the {@link com.geomenum.common.validation.constraints.PersistenceId} constraint.
 */
public class PersistenceIdValidator implements ConstraintValidator<PersistenceId, CharSequence> {

    private static final int ID_LENGTH = 43;

    @Override
    public void initialize(PersistenceId constraintAnnotation) {}

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if(value == null || value.length() == 0) {
            return true;
        }

        if(value.length() != ID_LENGTH
                || !Base64.isBase64(value.toString())) {
            return false;
        }

        return true;
    }

    /**
     * Returns {@code true} if the id is valid, false otherwise.
     *
     * @param id the id to validate
     * @return a boolean
     */
    public static boolean validate(final String id) {
        if(id == null) {
            return false;
        }
        if(id.isEmpty()) {
            return false;
        }
        if(id.length() != ID_LENGTH) {
            return false;
        }
        if(!Base64.isBase64(id)) {
            return false;
        }
        return true;
    }
}
