/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.restaurant;

import com.geomenum.r2d2.common.RequestDispatcher;
import com.geomenum.r2d2.common.Response;
import com.geomenum.web.Messages;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.domainmodel.restaurant.WebRestaurant;
import com.geomenum.web.requestsresponses.restaurant.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

import static com.geomenum.web.controllers.restaurant.RestaurantQueryWebController.restaurantBackingBeanName;

@WebController
@RequestMapping(value = WebURLPath.RESTAURANT_ROOT, method = RequestMethod.POST)
@SessionAttributes({restaurantBackingBeanName})
public class RestaurantCommandWebController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantCommandWebController.class);

    private static final String addedLanguageParamName = "addedLanguage";
    private static final String removedLanguageParamName = "removedLanguage";

    @RequestMapping
    public String saveRestaurant(@Valid @ModelAttribute(restaurantBackingBeanName) WebRestaurant restaurant, BindingResult bindingResult,
                                 Model model, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, WebRequest webRequest)
            throws IOException, ServletException {
        if(bindingResult.hasErrors()) {
            // add failure message
            webRequest.setAttribute(RequestAttribute.MESSAGE, Messages.Restaurant.WARNING_ON_SAVE, RequestAttributes.SCOPE_REQUEST);
            // return the form in edit mode
            model.addAttribute("mode", "edit");
            return View.RESTAURANT;
        } else {
            // save the restaurant
            RequestDispatcher requestDispatcher = createRequestDispatcher();
            requestDispatcher.add(new SaveRestaurantRequest(restaurant));

            // check for added or removed language
            if(webRequest.getParameter(addedLanguageParamName) != null) {
                Locale addedLanguage = Locale.forLanguageTag(webRequest.getParameter(addedLanguageParamName));
                requestDispatcher.add(new AddLanguageSupportRequest(restaurant.getId(), addedLanguage));
            } else if(webRequest.getParameter(removedLanguageParamName) != null) {
                Locale removedLanguage = Locale.forLanguageTag(webRequest.getParameter(removedLanguageParamName));
                requestDispatcher.add(new RemoveLanguageSupportRequest(restaurant.getId(), removedLanguage));
            }

            SaveRestaurantResponse saveRestaurantResponse = requestDispatcher.getResponse(SaveRestaurantResponse.class);
            Response languageSupportResponse = null;
            if(requestDispatcher.hasResponse(AddLanguageSupportResponse.class)) {
                languageSupportResponse = requestDispatcher.getResponse(AddLanguageSupportResponse.class);
            } else if(requestDispatcher.hasResponse(RemoveLanguageSupportResponse.class)) {
                languageSupportResponse = requestDispatcher.getResponse(RemoveLanguageSupportResponse.class);
            }
            if(saveRestaurantResponse.hasExceptionOccurred()
                    || (languageSupportResponse != null && languageSupportResponse.hasExceptionOccurred())) {
                // add error message
                redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.ERROR_ON_SAVE);
            } else {
                // add success message
                redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Restaurant.SUCCESS_ON_SAVE);
            }
            // form processing completed
            sessionStatus.setComplete();
            return redirect(WebURLPath.getRestaurantURL(restaurant.getId()));
        }
    }
}
