/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.AbstractWebControllerUnitTests;
import com.geomenum.web.domainmodel.menu.WebMenuItemFixtures;
import com.geomenum.web.domainmodel.menu.WebMenuItemMatcher;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemResponse;
import com.geomenum.web.requestsresponses.menu.menuitem.GetMenuItemAndBreadcrumbsResponseFixtures;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static com.geomenum.web.domainmodel.menu.WebMenuItemFixtures.standardMenuItem;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MenuItemQueryWebControllerUnitTests extends AbstractWebControllerUnitTests {

    private static final String MENU_ITEM_RESOURCE = "/WEB-INF/views/menu/menu_item.html";

    @InjectMocks
    private MenuItemQueryWebController controller;

    @Override
    protected AbstractWebController getController() {
        return controller;
    }

    public void viewMenuItem() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetMenuItemRequest.class), eq(GetMenuItemResponse.class)))
                .thenReturn(GetMenuItemAndBreadcrumbsResponseFixtures.successResponse());

        mockMvc.perform(get(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.MENU_ITEM))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists(
                        MenuItemQueryWebController.menuItemBackingBeanName,
                        MenuItemQueryWebController.breadcrumbNavigationBarBeanName))
                .andExpect(model().attribute(MenuItemQueryWebController.menuItemBackingBeanName, new WebMenuItemMatcher(standardMenuItem())))
                .andExpect(forwardedUrl(MENU_ITEM_RESOURCE));
    }

    public void viewMenuItemWithNoMenuItem() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetMenuItemRequest.class), eq(GetMenuItemResponse.class)))
                .thenReturn(GetMenuItemAndBreadcrumbsResponseFixtures.defaultResponse());

        mockMvc.perform(get(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(ERROR_RESOURCE));
    }

    public void editMenuItem() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetMenuItemRequest.class), eq(GetMenuItemResponse.class)))
                .thenReturn(GetMenuItemAndBreadcrumbsResponseFixtures.successResponse());

        mockMvc.perform(get(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID) + "?mode=edit"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.MENU_ITEM))
                .andExpect(model().size(3))
                .andExpect(model().attributeExists(
                        MenuItemQueryWebController.menuItemBackingBeanName,
                        MenuItemQueryWebController.breadcrumbNavigationBarBeanName,
                        "mode"))
                .andExpect(model().attribute(MenuItemQueryWebController.menuItemBackingBeanName, new WebMenuItemMatcher(standardMenuItem())))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(forwardedUrl(MENU_ITEM_RESOURCE));
    }

    public void editMenuItemWithNoMenuItem() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetMenuItemRequest.class), eq(GetMenuItemResponse.class)))
                .thenReturn(GetMenuItemAndBreadcrumbsResponseFixtures.defaultResponse());

        mockMvc.perform(get(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID) + "?mode=edit"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(ERROR_RESOURCE));
    }
}
