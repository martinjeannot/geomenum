/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraints;

import com.geomenum.common.validation.constraintvalidators.LightPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A light password is comprise of letters, numbers or special characters and min 8 characters.<br/>
 * Note that this constraint does not check for {@code null} nor empty value.
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = LightPasswordValidator.class)
@Documented
public @interface LightPassword {

    String message() default "{com.geomenum.common.validation.constraints.LightPassword.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
