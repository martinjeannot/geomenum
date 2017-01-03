/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.r2d2.common.RequestDispatcher;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemResponse;
import com.geomenum.web.requestsresponses.ui.GetMenuBreadcrumbsRequest;
import com.geomenum.web.requestsresponses.ui.GetMenuBreadcrumbsResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.geomenum.web.controllers.menu.MenuItemQueryWebController.menuItemBackingBeanName;

@WebController
@RequestMapping(value = WebURLPath.MENU_ITEM_ROOT, method = RequestMethod.GET)
@SessionAttributes({menuItemBackingBeanName})
public class MenuItemQueryWebController extends AbstractWebController {
    
    public static final String menuItemBackingBeanName = "menuItem";
    public static final String breadcrumbNavigationBarBeanName = "breadcrumbNavigationBar";

    @RequestMapping
    public String viewMenuItem(Model model) {
        if(model.containsAttribute(menuItemBackingBeanName)
                && model.containsAttribute(breadcrumbNavigationBarBeanName)) {
            return View.MENU_ITEM;
        } else {
            return View.ERROR;
        }
    }

    @RequestMapping(params = "mode=edit")
    public String editMenuItem(Model model) {
        if(model.containsAttribute(menuItemBackingBeanName)
                && model.containsAttribute(breadcrumbNavigationBarBeanName)) {
            model.addAttribute("mode", "edit");
            return View.MENU_ITEM;
        } else {
            return View.ERROR;
        }
    }

    @ModelAttribute
    private void populateMenuItem(@PathVariable("menuId") String menuId,
                                  @PathVariable("menuItemId") String menuItemId,
                                  Model model) {
        model.asMap().remove(menuItemBackingBeanName);
        model.asMap().remove(breadcrumbNavigationBarBeanName);

        RequestDispatcher requestDispatcher = createRequestDispatcher();
        requestDispatcher.add(
                new GetMenuItemRequest(menuId, menuItemId),
                new GetMenuBreadcrumbsRequest(menuId, menuItemId));
        GetMenuItemResponse menuItemResponse = requestDispatcher.getResponse(GetMenuItemResponse.class);
        GetMenuBreadcrumbsResponse breadcrumbsResponse = requestDispatcher.getResponse(GetMenuBreadcrumbsResponse.class);

        if(!menuItemResponse.hasExceptionOccurred()
                && !breadcrumbsResponse.hasExceptionOccurred()) {
            model.addAttribute(menuItemBackingBeanName, menuItemResponse.getMenuItem());
            model.addAttribute(breadcrumbNavigationBarBeanName, breadcrumbsResponse.getBreadcrumbNavigationBarData());
        }
    }
}
