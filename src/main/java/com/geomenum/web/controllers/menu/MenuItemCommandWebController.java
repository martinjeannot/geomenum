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
import com.geomenum.web.domainmodel.menu.WebMenuItem;
import com.geomenum.web.requestsresponses.menu.menuitem.SaveMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.SaveMenuItemResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.geomenum.web.controllers.menu.MenuItemQueryWebController.menuItemBackingBeanName;

@WebController
@RequestMapping(value = WebURLPath.MENU_ITEM_ROOT, method = RequestMethod.POST)
@SessionAttributes({menuItemBackingBeanName})
public class MenuItemCommandWebController extends AbstractWebController {

    @RequestMapping
    public String saveMenuItem(@PathVariable("menuId") String menuId,
                               @Valid @ModelAttribute(menuItemBackingBeanName) WebMenuItem menuItem, BindingResult bindingResult,
                               Model model, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, WebRequest webRequest) {
        if(bindingResult.hasErrors()) {
            // add failure message
            webRequest.setAttribute(RequestAttribute.MESSAGE, Messages.MenuItem.WARNING_ON_SAVE, RequestAttributes.SCOPE_REQUEST);
            // return the form in edit mode
            model.addAttribute("mode", "edit");
            return View.MENU_ITEM;
        } else {
            // saving the menu item
            SaveMenuItemResponse response = createRequestDispatcher().getResponse(
                    new SaveMenuItemRequest(menuId, menuItem),
                    SaveMenuItemResponse.class);
            if(response.hasExceptionOccurred()) {
                // add exception message
                redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.ERROR_ON_SAVE);
            } else {
                // add success message
                redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.MenuItem.SUCCESS_ON_SAVE);
            }
            // form processing completed
            sessionStatus.setComplete();
            return redirect(WebURLPath.getMenuItemURL(menuId, menuItem.getId()));
        }
    }
}
