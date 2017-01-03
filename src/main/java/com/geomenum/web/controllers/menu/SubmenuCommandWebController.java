/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.web.Messages;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.domainmodel.menu.WebSubmenu;
import com.geomenum.web.requestsresponses.menu.submenu.SaveSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.SaveSubmenuResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.geomenum.web.controllers.menu.SubmenuQueryWebController.submenuBackingBeanName;

@WebController
@RequestMapping(value = WebURLPath.SUBMENU_ROOT, method = RequestMethod.POST)
@SessionAttributes({submenuBackingBeanName})
public class SubmenuCommandWebController extends AbstractWebController {

    @RequestMapping
    public String saveSubmenu(@PathVariable("menuId") String menuId,
                              @Valid @ModelAttribute(submenuBackingBeanName) WebSubmenu submenu, BindingResult bindingResult,
                              Model model, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, WebRequest webRequest) {
        if(bindingResult.hasErrors()) {
            // add failure message
            webRequest.setAttribute(RequestAttribute.MESSAGE, Messages.Submenu.WARNING_ON_SAVE, RequestAttributes.SCOPE_REQUEST);
            // return the form in edit mode
            model.addAttribute("mode", "edit");
            return View.SUBMENU;
        } else {
            // saving the submenu
            SaveSubmenuResponse response = createRequestDispatcher().getResponse(
                    new SaveSubmenuRequest(menuId, submenu),
                    SaveSubmenuResponse.class);
            if(response.hasExceptionOccurred()) {
                // add exception message
                redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.ERROR_ON_SAVE);
            } else {
                // add success message
                redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.SUCCESS_ON_SAVE);
            }
            // form processing completed
            sessionStatus.setComplete();
            return redirect(WebURLPath.getSubmenuURL(menuId, submenu.getId()));
        }
    }
}
