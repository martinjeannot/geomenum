/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.common.Languages;
import com.geomenum.web.Messages;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Locale;

import static com.geomenum.web.WebURLPath.redirect;

@WebController
@RequestMapping(value = WebURLPath.LOGIN, method = RequestMethod.GET)
public class LoginWebController {

    @RequestMapping
    public String login() {
        return View.LOGIN;
    }

    @RequestMapping(params = "error")
    public String error(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Login.INVALID_CREDENTIALS);
        return redirect(WebURLPath.LOGIN);
    }

    @RequestMapping(params = "logout")
    public String logout(RedirectAttributes redirectAttributes) {
        if(AbstractWebController.logoutMessage.get() == null) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Login.LOGOUT);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, AbstractWebController.logoutMessage.get());
            AbstractWebController.logoutMessage.remove();
        }
        return redirect(WebURLPath.PUBLIC_HOME);
    }

    @ModelAttribute("supportedLanguages")
    private Collection<Locale> populateSupportedLanguages(Locale lang) {
        return Languages.getSupportedLanguagesSortedByName(lang);
    }
}
