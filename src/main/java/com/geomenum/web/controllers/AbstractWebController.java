/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.r2d2.common.RequestDispatcher;
import com.geomenum.r2d2.common.RequestDispatcherFactory;
import com.geomenum.web.WebURLPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value = {AbstractWebController.headerBeanName})
public abstract class AbstractWebController {

    public static final String headerBeanName = "header";

    /**
     * Workaround used to transmit message to the home page upon logout.
     * We're forced to use a ThreadLocal since a double-redirection occurs when invoking the logout flow :
     * /logout (logout invocation) --> /login?logout (successful logout) --> / (home page redirection)
     * Any better idea is welcome !
     */
    public static final ThreadLocal<String> logoutMessage = new ThreadLocal<>();

    @Autowired
    private RequestDispatcherFactory requestDispatcherFactory;

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
     * Redirects to the given URL path.
     *
     * @param URLPath the path to redirect the request to
     * @param queryStrings query Strings to be appended (must already be formatted e.g "field=value")
     * @return the redirection as a String
     */
    protected String redirect(String URLPath, String... queryStrings) {
        return WebURLPath.redirect(URLPath, queryStrings);
    }
}
