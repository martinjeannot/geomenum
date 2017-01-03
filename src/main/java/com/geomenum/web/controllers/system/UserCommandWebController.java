/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.system;

import com.geomenum.common.Languages;
import com.geomenum.web.Messages;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.domainmodel.system.WebUser;
import com.geomenum.web.requestsresponses.system.DeleteUserAccountRequest;
import com.geomenum.web.requestsresponses.system.DeleteUserAccountResponse;
import com.geomenum.web.requestsresponses.system.SaveUserRequest;
import com.geomenum.web.requestsresponses.system.SaveUserResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.Locale;

import static com.geomenum.web.controllers.system.UserQueryWebController.userBackingBeanName;

@WebController
@RequestMapping(value = WebURLPath.USER_ROOT, method = RequestMethod.POST)
@SessionAttributes({userBackingBeanName})
public class UserCommandWebController extends AbstractWebController {

    private static final String userAccountDeletionParamName = "deleteUserAccount";

    @RequestMapping
    public String saveUser(@Valid @ModelAttribute(userBackingBeanName) WebUser user, BindingResult bindingResult,
                           Model model, WebRequest webRequest, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
        if(bindingResult.hasErrors()) {
            // add warning message
            webRequest.setAttribute(RequestAttribute.MESSAGE, Messages.User.WARNING_ON_SAVE, RequestAttributes.SCOPE_REQUEST);
            // return the form in edit mode
            model.addAttribute("mode", "edit");
            return View.USER;
        } else {
            // attempt at saving the user
            SaveUserResponse response = createRequestDispatcher().getResponse(
                    new SaveUserRequest(user),
                    SaveUserResponse.class);
            if(response.hasExceptionOccurred()) {
                // add error message
                redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.ERROR_ON_SAVE);
            } else if(!response.isUpdatedUserEnabled()) {
                // form processing completed
                sessionStatus.setComplete();
                logoutMessage.set(Messages.User.CONFIRMATION_ON_SAVE);
                return redirect(WebURLPath.LOGOUT);
            } else {
                // whether the user language has been modified or not, we reset the value to the updated one
                redirectAttributes.addAttribute(LocaleChangeInterceptor.DEFAULT_PARAM_NAME, response.getUpdatedUserLanguage());
                // add success message
                redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.User.SUCCESS_ON_SAVE);
            }
            // form processing completed
            sessionStatus.setComplete();
            return redirect(WebURLPath.getUserURL(user.getId()));
        }
    }

    @RequestMapping(params = userAccountDeletionParamName)
    public String deleteUser(Principal principal, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        DeleteUserAccountResponse response = createRequestDispatcher().getResponse(
                new DeleteUserAccountRequest(principal.getName()),
                DeleteUserAccountResponse.class);
        if(response.hasExceptionOccurred()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.User.ERROR_ON_DELETE);
            return redirect(WebURLPath.PRIVATE_HOME);
        }
        logoutMessage.set(Messages.User.SUCCESS_ON_DELETE);
        return redirect(WebURLPath.LOGOUT);
    }

    @ModelAttribute("supportedLanguages")
    private Collection<Locale> populateSupportedLanguages(Locale lang) {
        return Languages.getSupportedLanguagesSortedByName(lang);
    }
}
