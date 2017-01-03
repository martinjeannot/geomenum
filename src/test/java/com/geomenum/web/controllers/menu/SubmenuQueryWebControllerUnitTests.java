/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.AbstractWebControllerUnitTests;
import com.geomenum.web.domainmodel.menu.WebSubmenuFixtures;
import com.geomenum.web.domainmodel.menu.WebSubmenuMatcher;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuResponse;
import com.geomenum.web.requestsresponses.menu.submenu.GetSubmenuAndBreadcrumbsAndDashboardResponseFixtures;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static com.geomenum.web.domainmodel.menu.WebSubmenuFixtures.standardSubmenu;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubmenuQueryWebControllerUnitTests extends AbstractWebControllerUnitTests {

    private static final String SUBMENU_RESOURCE = "/WEB-INF/views/menu/submenu.html";

    @InjectMocks
    private SubmenuQueryWebController controller;

    @Override
    protected AbstractWebController getController() {
        return controller;
    }

    public void viewSubmenu() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetSubmenuRequest.class), eq(GetSubmenuResponse.class)))
                .thenReturn(GetSubmenuAndBreadcrumbsAndDashboardResponseFixtures.successResponse());

        mockMvc.perform(get(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.SUBMENU))
                .andExpect(model().size(3))
                .andExpect(model().attributeExists(
                        SubmenuQueryWebController.submenuBackingBeanName,
                        SubmenuQueryWebController.breadcrumbNavigationBarBeanName,
                        SubmenuQueryWebController.submenuDashboardInfoBeanName))
                .andExpect(model().attribute(SubmenuQueryWebController.submenuBackingBeanName, new WebSubmenuMatcher(standardSubmenu())))
                .andExpect(forwardedUrl(SUBMENU_RESOURCE));
    }

    public void viewSubmenuWithNoSubmenu() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetSubmenuRequest.class), eq(GetSubmenuResponse.class)))
                .thenReturn(GetSubmenuAndBreadcrumbsAndDashboardResponseFixtures.defaultResponse());

        mockMvc.perform(get(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(ERROR_RESOURCE));
    }

    public void editSubmenu() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetSubmenuRequest.class), eq(GetSubmenuResponse.class)))
                .thenReturn(GetSubmenuAndBreadcrumbsAndDashboardResponseFixtures.successResponse());

        mockMvc.perform(get(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID) + "?mode=edit"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.SUBMENU))
                .andExpect(model().size(4))
                .andExpect(model().attributeExists(
                        SubmenuQueryWebController.submenuBackingBeanName,
                        SubmenuQueryWebController.breadcrumbNavigationBarBeanName,
                        SubmenuQueryWebController.submenuDashboardInfoBeanName,
                        "mode"))
                .andExpect(model().attribute(SubmenuQueryWebController.submenuBackingBeanName, new WebSubmenuMatcher(standardSubmenu())))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(forwardedUrl(SUBMENU_RESOURCE));
    }

    public void editSubmenuWithNoSubmenu() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetSubmenuRequest.class), eq(GetSubmenuResponse.class)))
                .thenReturn(GetSubmenuAndBreadcrumbsAndDashboardResponseFixtures.defaultResponse());

        mockMvc.perform(get(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID) + "?mode=edit"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(ERROR_RESOURCE));
    }
}
