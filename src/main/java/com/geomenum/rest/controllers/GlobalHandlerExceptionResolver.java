/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This {@link org.springframework.web.servlet.HandlerExceptionResolver} is our last safety net in case an exception
 * occurs while resolving another one in {@link org.springframework.web.bind.annotation.ExceptionHandler} annotated
 * methods.<br/>
 * Can also be used to throw exceptions in {@link org.springframework.web.bind.annotation.ExceptionHandler} annotated
 * methods to force a {@code 500 Internal Server Error} response.
 *
 * @see GlobalExceptionHandler
 */
@Component
public class GlobalHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final String INTERNAL_SERVER_ERROR_JSON = "{\"message\":\"Sorry, an error occurred. Please try again later.\"}";

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(INTERNAL_SERVER_ERROR_JSON);
        } catch (Exception resolutionEx) {
            logger.error("Failed to resolve the following exception in GlobalHandlerExceptionResolver : " + ex, resolutionEx);
            return null;
        }
        return new ModelAndView();
    }
}
