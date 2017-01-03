/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.web.Messages;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@WebController
@RequestMapping(value = WebURLPath.PRIVATE_HOME, method = RequestMethod.GET)
public class PrivateHomeWebController extends AbstractWebController {

    @RequestMapping
    public String welcome() {
        return View.PRIVATE_HOME;
    }

    @RequestMapping(params = "error")
    public String genericError(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        sessionStatus.setComplete();
        // add error message
        redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.GENERIC_ERROR);
        return WebURLPath.redirect(WebURLPath.PRIVATE_HOME);
    }
}
