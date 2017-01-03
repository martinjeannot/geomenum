/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraints;

import com.geomenum.common.validation.constraintvalidators.PersistenceIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated element must be valid in regards to the id structure used by the persistence domain.<br/>
 * Note that this constraint does not check for {@code null} nor empty value.
 *
 * @see com.geomenum.common.validation.constraintvalidators.PersistenceIdValidator
 * @see PersistenceIdNotBlank
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = PersistenceIdValidator.class)
@Documented
public @interface PersistenceId {

    String message() default "{com.geomenum.common.validation.constraints.PersistenceId.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
