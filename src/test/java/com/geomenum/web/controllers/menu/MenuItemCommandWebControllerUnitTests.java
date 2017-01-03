/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.AbstractWebControllerUnitTests;
import com.geomenum.web.domainmodel.menu.WebMenuItemFixtures;
import com.geomenum.web.requestsresponses.menu.menuitem.SaveMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.SaveMenuItemResponse;
import com.geomenum.web.requestsresponses.menu.menuitem.SaveMenuItemResponseFixtures;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.web.HttpSessionRequiredException;
import org.testng.annotations.Test;

import static com.geomenum.web.domainmodel.menu.WebMenuItemFixtures.standardUpdatedMenuItem;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MenuItemCommandWebControllerUnitTests extends AbstractWebControllerUnitTests {

    private static final String MENU_ITEM_RESOURCE = "/WEB-INF/views/menu/menu_item.html";

    @InjectMocks
    private MenuItemCommandWebController controller;

    @Override
    protected AbstractWebController getController() {
        return controller;
    }

    public void saveMenuItem() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(SaveMenuItemRequest.class), eq(SaveMenuItemResponse.class)))
                .thenReturn(SaveMenuItemResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID))
                .sessionAttr(MenuItemQueryWebController.menuItemBackingBeanName, standardUpdatedMenuItem()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)));
    }

    public void saveMenuItemWithBindingErrors() throws Exception {
        mockMvc.perform(post(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID))
                .sessionAttr(MenuItemQueryWebController.menuItemBackingBeanName, standardUpdatedMenuItem())
                .param("enabled", "")) // binding error
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.MENU_ITEM))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists(
                        MenuItemQueryWebController.menuItemBackingBeanName,
                        "mode"))
                .andExpect(model().attributeHasFieldErrors(MenuItemQueryWebController.menuItemBackingBeanName, "enabled"))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(request().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(forwardedUrl(MENU_ITEM_RESOURCE));
    }

    public void saveMenuItemWithException() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(SaveMenuItemRequest.class), eq(SaveMenuItemResponse.class)))
                .thenReturn(SaveMenuItemResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID))
                .sessionAttr(MenuItemQueryWebController.menuItemBackingBeanName, standardUpdatedMenuItem()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.EXCEPTION))
                .andExpect(redirectedUrl(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)));
    }

    @Test(expectedExceptions = HttpSessionRequiredException.class,
            expectedExceptionsMessageRegExp = "Expected session attribute 'menuItem'")
    public void saveMenuItemWithNoMenuItem() throws Exception {
        mockMvc.perform(post(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)));
    }
}
