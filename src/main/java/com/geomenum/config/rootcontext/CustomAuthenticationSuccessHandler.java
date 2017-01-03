/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import com.geomenum.core.domainmodel.system.CoreUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom {@link org.springframework.security.web.authentication.AuthenticationSuccessHandler}<br/>
 * Currently responsible for tasks such as :<br/>
 * - setting the user's locale upon successful authentication.
 */
class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LocaleResolver localeResolver;

    public CustomAuthenticationSuccessHandler(String defaultTargetUrl, LocaleResolver localeResolver) {
        super(defaultTargetUrl);
        this.localeResolver = localeResolver;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setUserLocale(request, response, authentication);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void setUserLocale(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal instanceof CoreUser) {
                localeResolver.setLocale(
                        request,
                        response,
                        ((CoreUser) principal).getLanguage());
            }
        }
    }
}
