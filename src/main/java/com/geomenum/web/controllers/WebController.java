/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * A convenience annotation that is itself annotated with {@link org.springframework.stereotype.Controller @Controller}.
 * <p>
 * Types that carry this annotation are treated as controllers operating inside our WEB domain.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
public @interface WebController {
}
