/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.r2d2.common.RequestDispatcher;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.domainmodel.menu.NewMenuItem;
import com.geomenum.web.domainmodel.menu.NewSubmenu;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemCreationInfoRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemCreationInfoResponse;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuCreationInfoRequest;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuCreationInfoResponse;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuResponse;
import com.geomenum.web.requestsresponses.ui.GetMenuBreadcrumbsRequest;
import com.geomenum.web.requestsresponses.ui.GetMenuBreadcrumbsResponse;
import com.geomenum.web.requestsresponses.ui.GetSubmenuDashboardRequest;
import com.geomenum.web.requestsresponses.ui.GetSubmenuDashboardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.geomenum.web.controllers.menu.SubmenuQueryWebController.*;

@WebController
@RequestMapping(value = WebURLPath.SUBMENU_ROOT, method = RequestMethod.GET)
@SessionAttributes({submenuBackingBeanName, newSubmenuBackingBeanName, newMenuItemBackingBeanName})
public class SubmenuQueryWebController extends AbstractWebController {

    public static final String submenuBackingBeanName = "submenu";

    public static final String breadcrumbNavigationBarBeanName = "breadcrumbNavigationBar";
    public static final String submenuDashboardInfoBeanName = "submenuDashboardInfo";

    public static final String newSubmenuBackingBeanName = "newSubmenu";
    public static final String newMenuItemBackingBeanName = "newMenuItem";

    private final MessageSource messageSource;

    @Autowired
    public SubmenuQueryWebController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping
    public String viewSubmenu(Model model) {
        if (isModelFullyPopulated(model)) {
            return View.SUBMENU;
        } else {
            return View.ERROR;
        }
    }

    @RequestMapping(params = "mode=edit")
    public String editSubmenu(Model model) {
        if (isModelFullyPopulated(model)) {
            model.addAttribute("mode", "edit");
            return View.SUBMENU;
        } else {
            return View.ERROR;
        }
    }

    @ModelAttribute
    private void populateModel(@PathVariable("menuId") String menuId,
                               @PathVariable("submenuId") String submenuId,
                               Model model) {
        clearModel(model);

        RequestDispatcher requestDispatcher = createRequestDispatcher();
        requestDispatcher.add(
                new GetSubmenuRequest(menuId, submenuId),
                new GetMenuBreadcrumbsRequest(menuId, submenuId),
                new GetSubmenuDashboardRequest(menuId, submenuId),
                new GetSubmenuCreationInfoRequest(menuId),
                new GetMenuItemCreationInfoRequest(menuId));
        GetSubmenuResponse submenuResponse = requestDispatcher.getResponse(GetSubmenuResponse.class);
        GetMenuBreadcrumbsResponse breadcrumbsResponse = requestDispatcher.getResponse(GetMenuBreadcrumbsResponse.class);
        GetSubmenuDashboardResponse dashboardResponse = requestDispatcher.getResponse(GetSubmenuDashboardResponse.class);
        GetSubmenuCreationInfoResponse submenuCreationInfoResponse = requestDispatcher.getResponse(
                GetSubmenuCreationInfoResponse.class);
        GetMenuItemCreationInfoResponse menuItemCreationInfoResponse = requestDispatcher.getResponse(
                GetMenuItemCreationInfoResponse.class);

        if(!submenuResponse.hasExceptionOccurred()
                && !breadcrumbsResponse.hasExceptionOccurred()
                && !dashboardResponse.hasExceptionOccurred()
                && !submenuCreationInfoResponse.hasExceptionOccurred()
                && !menuItemCreationInfoResponse.hasExceptionOccurred()) {
            model.addAttribute(submenuBackingBeanName, submenuResponse.getSubmenu());
            model.addAttribute(breadcrumbNavigationBarBeanName, breadcrumbsResponse.getBreadcrumbNavigationBarData());
            model.addAttribute(submenuDashboardInfoBeanName, dashboardResponse.getSubmenuDashboardData());
            model.addAttribute(newSubmenuBackingBeanName, new NewSubmenu(
                    submenuCreationInfoResponse.getSupportedLanguages(),
                    messageSource));
            model.addAttribute(newMenuItemBackingBeanName, new NewMenuItem(
                    menuItemCreationInfoResponse.getSupportedLanguages(),
                    messageSource,
                    menuItemCreationInfoResponse.getCurrency()));
        }
    }

    private boolean isModelFullyPopulated(Model model) {
        return model.containsAttribute(submenuBackingBeanName)
                && model.containsAttribute(breadcrumbNavigationBarBeanName)
                && model.containsAttribute(submenuDashboardInfoBeanName)
                && model.containsAttribute(newSubmenuBackingBeanName)
                && model.containsAttribute(newMenuItemBackingBeanName);
    }

    // Cannot invoke model.asMap().clear() since it clears absolutely everything
    private void clearModel(Model model) {
        model.asMap().remove(submenuBackingBeanName);
        model.asMap().remove(breadcrumbNavigationBarBeanName);
        model.asMap().remove(submenuDashboardInfoBeanName);
        model.asMap().remove(newSubmenuBackingBeanName);
        model.asMap().remove(newMenuItemBackingBeanName);
    }
}
