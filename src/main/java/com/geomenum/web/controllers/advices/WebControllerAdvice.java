/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.advices;

import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = WebController.class)
public class WebControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(WebControllerAdvice.class);

    @ExceptionHandler(value = {Exception.class})
    private String defaultErrorHandler(Exception e) {
        logger.error("Unhandled exception caught in WEB domain :", e);
        if(SecurityContextHolder.getContext().getAuthentication() != null
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return WebURLPath.redirect(WebURLPath.PRIVATE_HOME, "error");
        } else {
            return WebURLPath.redirect(WebURLPath.PUBLIC_HOME, "error");
        }
    }
}
