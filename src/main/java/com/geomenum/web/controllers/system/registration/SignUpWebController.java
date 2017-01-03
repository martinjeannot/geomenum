/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.system.registration;

import com.geomenum.common.Countries;
import com.geomenum.common.Languages;
import com.geomenum.r2d2.common.RequestDispatcher;
import com.geomenum.r2d2.common.RequestDispatcherFactory;
import com.geomenum.web.Messages;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.domainmodel.system.registration.RegistrationForm;
import com.geomenum.web.requestsresponses.system.registration.ConfirmAccountRequest;
import com.geomenum.web.requestsresponses.system.registration.ConfirmAccountResponse;
import com.geomenum.web.requestsresponses.system.registration.SignUpRequest;
import com.geomenum.web.requestsresponses.system.registration.SignUpResponse;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Locale;

import static com.geomenum.common.Countries.Country;

@WebController
@RequestMapping(WebURLPath.SIGN_UP)
public class SignUpWebController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpWebController.class);

    private static final String RECAPTCHA_PRIVATE_KEY;

    static {
        try {
            RECAPTCHA_PRIVATE_KEY =
                    Files.readAllLines(
                            Paths.get(SignUpWebController.class.getClassLoader().getResource("security/reCaptchaPrivateKey.txt").getPath()),
                            Charset.defaultCharset())
                            .get(0);
        } catch (IOException e) {
            throw new Error("[FATAL ERROR] SignUpWebController INITIALIZATION FAILED !", e); // abort mission !
        }
    }

    @Autowired
    private RequestDispatcherFactory requestDispatcherFactory;

    @RequestMapping(method = RequestMethod.GET)
    public String showSignUpForm(@ModelAttribute RegistrationForm registrationForm) {
        return View.SIGN_UP;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute RegistrationForm registrationForm, BindingResult bindingResult,
                           SessionStatus sessionStatus, RedirectAttributes redirectAttributes, WebRequest webRequest,
                           HttpServletRequest httpRequest) {
        if(bindingResult.hasErrors()) {
            // add warning message
            if(bindingResult.hasFieldErrors("username")) {
                webRequest.setAttribute(RequestAttribute.MESSAGE, Messages.SignUp.WARNING_ON_USERNAME, RequestAttributes.SCOPE_REQUEST);
            } else {
                webRequest.setAttribute(RequestAttribute.MESSAGE, Messages.SignUp.WARNING_ON_REGISTRATION, RequestAttributes.SCOPE_REQUEST);
            }
            return View.SIGN_UP;
        }

        // CAPTCHA verification
        String remoteAddr = httpRequest.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey(RECAPTCHA_PRIVATE_KEY);
        String challenge = webRequest.getParameter("recaptcha_challenge_field") == null ? "" : webRequest.getParameter("recaptcha_challenge_field");
        String uresponse = webRequest.getParameter("recaptcha_response_field") == null ? "" : webRequest.getParameter("recaptcha_response_field");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
        if(!reCaptchaResponse.isValid()) {
            // add CAPTCHA warning message
            webRequest.setAttribute(RequestAttribute.MESSAGE, Messages.SignUp.WARNING_ON_CAPTCHA, RequestAttributes.SCOPE_REQUEST);
            return View.SIGN_UP;
        }

        RequestDispatcher requestDispatcher = requestDispatcherFactory.createRequestDispatcher();
        SignUpResponse response = requestDispatcher.getResponse(
                new SignUpRequest(registrationForm),
                SignUpResponse.class);
        if(response.hasExceptionOccurred()) {
            // add error message
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.SignUp.ERROR_ON_REGISTRATION);
        } else {
            // add success message
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.SignUp.SUCCESS_ON_REGISTRATION);
        }
        // form processing completed
        sessionStatus.setComplete();
        return WebURLPath.redirect(WebURLPath.PUBLIC_HOME);
    }

    @RequestMapping(method = RequestMethod.GET, params = "confirm")
    public String confirmAccount(@RequestParam("confirm") String userId, RedirectAttributes redirectAttributes) {
        RequestDispatcher requestDispatcher = requestDispatcherFactory.createRequestDispatcher();
        ConfirmAccountResponse response = requestDispatcher.getResponse(
                new ConfirmAccountRequest(userId),
                ConfirmAccountResponse.class);
        if(response.hasExceptionOccurred()) {
            // add error message
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.SignUp.ERROR_ON_ACCOUNT_CONFIRMATION);
        } else {
            // add success message
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.SignUp.SUCCESS_ON_ACCOUNT_CONFIRMATION);
        }
        return WebURLPath.redirect(WebURLPath.PUBLIC_HOME);
    }

    @ModelAttribute("countries")
    private Collection<Country> populateCountries(Locale lang) {
        return Countries.getCountriesSortedByName(lang);
    }

    @ModelAttribute("supportedLanguages")
    private Collection<Locale> populateSupportedLanguages(Locale lang) {
        return Languages.getSupportedLanguagesSortedByName(lang);
    }
}
