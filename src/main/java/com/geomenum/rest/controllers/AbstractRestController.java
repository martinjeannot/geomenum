/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers;

import com.geomenum.common.Languages;
import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.r2d2.common.RequestDispatcher;
import com.geomenum.r2d2.common.RequestDispatcherFactory;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.primitives.Doubles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@ResponseBody
public abstract class AbstractRestController {

    private static final String LOCALIZATION_PARAM_KEY = "lang";

    @Autowired
    private RequestDispatcherFactory requestDispatcherFactory;

    @Autowired
    protected MessageSource messageSource;

    /**
     * Creates a new request dispatcher and returns it.<br/>
     * If another request dispatcher can be used, please do so by calling
     * the {@link RequestDispatcher#clear()} method on it before re-use.
     *
     * @return {@link RequestDispatcher}
     */
    protected RequestDispatcher createRequestDispatcher() {
        return requestDispatcherFactory.createRequestDispatcher();
    }

    /**
     * Determines the preferred {@link Locale} that should be used to compute the received request.
     *
     * @param request the received request
     * @return the preferred locale
     */
    protected Locale getLocale(WebRequest request) {
        if (request.getParameter(LOCALIZATION_PARAM_KEY) != null
                && Languages.LANGUAGES.contains(
                Locale.forLanguageTag(
                        Locale.forLanguageTag(request.getParameter(LOCALIZATION_PARAM_KEY)).getLanguage()))) {
            return Locale.forLanguageTag(request.getParameter(LOCALIZATION_PARAM_KEY));
        } else if (request.getLocale() != null
                && Languages.LANGUAGES.contains(
                Locale.forLanguageTag(
                        request.getLocale().getLanguage()))) {
            return request.getLocale();
        }
        return Locale.ENGLISH;
    }

    //~ Parameters validation ==========================================================================================

    protected void validateParameterAsLong(String paramName, String value, boolean required, List<String> errors) {
        if (required && value == null) {
            errors.add("Missing required parameter : " + paramName);
        }
        if (value != null) {
            try {
                Long.parseLong(value);
            } catch (NumberFormatException e) {
                errors.add("Parameter " + paramName + " could not be parsed : " + value);
            }
        }
    }

    protected void validateParameterAsDouble(String paramName, String value, boolean required, List<String> errors) {
        if (required && value == null) {
            errors.add("Missing required parameter : " + paramName);
        }
        if (value != null) {
            if (Doubles.tryParse(value) == null) {
                errors.add("Parameter " + paramName + " could not be parsed : " + value);
            }
        }
    }

    protected void validateParameterAsCuisineStyle(String paramName, String value, boolean required, List<String> errors) {
        if (required && value == null) {
            errors.add("Missing required parameter : " + paramName);
        }
        if (value != null) {
            try {
                Cuisine.valueOf(value);
            } catch (IllegalArgumentException e) {
                errors.add("Parameter " + paramName + " could not be parsed : " + value);
            }
        }
    }

    //~ Responses ======================================================================================================

    // 400 - BAD REQUEST
    private static final Map<Object, Object> BAD_REQUEST_MESSAGE = ImmutableMap.<Object, Object>of("message", "Request wrongly formatted, see errors for more information.");
    protected final ResponseEntity<Map<Object, Object>> getBadRequestResponse(List<String> errors) {
        Map<Object, Object> response = Maps.newHashMap(BAD_REQUEST_MESSAGE);
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 404 - NOT FOUND
    private static final Map<Object, Object> NOT_FOUND_MESSAGE = ImmutableMap.<Object, Object>of("message", "Requested resource could not be found.");
    private static final ResponseEntity<Map<Object, Object>> NOT_FOUND_RESPONSE = new ResponseEntity<>(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
    protected final ResponseEntity<Map<Object, Object>> getNotFoundResponse() {
        return NOT_FOUND_RESPONSE;
    }

    // 500 - INTERNAL SERVER ERROR
    private static final Map<Object, Object> INTERNAL_SERVER_ERROR_MESSAGE = ImmutableMap.<Object, Object>of("message", "Sorry, an error occurred. Please try again later.");
    private static final ResponseEntity<Map<Object, Object>> INTERNAL_SERVER_ERROR_RESPONSE = new ResponseEntity<>(INTERNAL_SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    protected final ResponseEntity<Map<Object, Object>> getInternalServerErrorResponse() {
        return INTERNAL_SERVER_ERROR_RESPONSE;
    }
}
