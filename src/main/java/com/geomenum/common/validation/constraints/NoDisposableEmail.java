/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraints;

import com.geomenum.common.validation.constraintvalidators.NoDisposableEmailValidator;
import org.hibernate.validator.constraints.Email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated {@code CharSequence} must not be an Email address issued from any DEAD (Disposable Email Address Domain).
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = NoDisposableEmailValidator.class)
@Documented
@Email
public @interface NoDisposableEmail {

    String message() default "{com.geomenum.common.validation.constraints.NoDisposableEmail.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}