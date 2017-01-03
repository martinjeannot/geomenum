/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.controllers;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This {@link org.springframework.web.servlet.HandlerInterceptor} enables the support of JsonP by our REST API.
 */
public class JsonPCallbackHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final String callbackKey = "callback";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String callback = request.getParameter(callbackKey);
        if(callback != null) {
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.print(callback + "(");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(request.getParameter(callbackKey) != null) {
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.print(")");
        }
    }
}
