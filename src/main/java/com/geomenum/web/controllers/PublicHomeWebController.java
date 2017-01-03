/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.common.Languages;
import com.geomenum.web.Messages;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Locale;

@WebController
@RequestMapping(value = WebURLPath.PUBLIC_HOME, method = RequestMethod.GET)
public class PublicHomeWebController {

    @RequestMapping
    public String welcome() {
        if(SecurityContextHolder.getContext().getAuthentication() != null
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return WebURLPath.redirect(WebURLPath.PRIVATE_HOME);
        } else {
            return View.PUBLIC_HOME;
        }
    }

    @RequestMapping(value = WebURLPath.TOS) // works because WebURLPath.PUBLIC_HOME = root of the web app
    public String showTOS() {
        return View.TOS;
    }

    @RequestMapping(value = WebURLPath.PRIVACY) // works because WebURLPath.PUBLIC_HOME = root of the web app
    public String showPrivacyPolicy() {
        return View.PRIVACY;
    }

    @RequestMapping(params = "error")
    public String genericError(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        sessionStatus.setComplete();
        // add error message
        redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.GENERIC_ERROR);
        return WebURLPath.redirect(WebURLPath.PUBLIC_HOME);
    }

    @ModelAttribute("supportedLanguages")
    private Collection<Locale> populateSupportedLanguages(Locale lang) {
        return Languages.getSupportedLanguagesSortedByName(lang);
    }
}
