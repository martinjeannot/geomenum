/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.system;

import com.geomenum.common.Languages;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.requestsresponses.system.GetUserRequest;
import com.geomenum.web.requestsresponses.system.GetUserResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Locale;

@WebController
@RequestMapping(value = WebURLPath.USER_ROOT, method = RequestMethod.GET)
@SessionAttributes({UserQueryWebController.userBackingBeanName})
public class UserQueryWebController extends AbstractWebController {

    public static final String userBackingBeanName = "user";

    @RequestMapping
    public String viewUser(Model model) {
        if(model.containsAttribute(userBackingBeanName)) {
            return View.USER;
        } else {
            return View.ERROR;
        }
    }

    @RequestMapping(params = "mode=edit")
    public String editUser(Model model) {
        if(model.containsAttribute(userBackingBeanName)) {
            model.addAttribute("mode", "edit");
            return View.USER;
        } else {
            return View.ERROR;
        }
    }

    @ModelAttribute
    private void populateUser(@PathVariable("userId") String userId,
                              Model model) {
        model.asMap().remove(userBackingBeanName);
        GetUserResponse response = createRequestDispatcher().getResponse(
                new GetUserRequest(userId),
                GetUserResponse.class);
        if(!response.hasExceptionOccurred()) {
            model.addAttribute(userBackingBeanName, response.getUser());
        }
    }

    @ModelAttribute("supportedLanguages")
    private Collection<Locale> populateSupportedLanguages(Locale lang) {
        return Languages.getSupportedLanguagesSortedByName(lang);
    }
}
