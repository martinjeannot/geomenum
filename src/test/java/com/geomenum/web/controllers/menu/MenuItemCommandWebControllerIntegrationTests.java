/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebControllerIntegrationTests;
import com.geomenum.web.domainmodel.menu.WebMenuItemFixtures;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.web.domainmodel.menu.WebMenuItemFixtures.standardUpdatedMenuItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MenuItemCommandWebControllerIntegrationTests extends AbstractWebControllerIntegrationTests {

    @BeforeClass
    public void setUpBeforeClass() {
        super.setUpBeforeClass();
        insertStandardRestaurant();
    }

    @AfterClass
    public void tearDownAfterClass() {
        clearMenusCollection();
        clearRestaurantsCollection();
    }

    @BeforeMethod
    public void setUpBeforeMethod() {
        clearMenusCollection();
    }

    public void saveMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)).session(loggedSession())
                .sessionAttr(MenuItemQueryWebController.menuItemBackingBeanName, standardUpdatedMenuItem()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)));
    }

    public void saveMenuItemWithBindingErrors() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)).session(loggedSession())
                .sessionAttr(MenuItemQueryWebController.menuItemBackingBeanName, standardUpdatedMenuItem())
                .param("enabled", "")) // binding error
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.MENU_ITEM))
                .andExpect(model().attributeExists(
                        MenuItemQueryWebController.menuItemBackingBeanName,
                        "mode"))
                .andExpect(model().attributeHasFieldErrors(MenuItemQueryWebController.menuItemBackingBeanName, "enabled"))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(request().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(forwardedUrl(null));
    }

    public void saveMenuItemWithException() throws Exception {
        mockMvc.perform(post(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)).session(loggedSession())
                .sessionAttr(MenuItemQueryWebController.menuItemBackingBeanName, standardUpdatedMenuItem()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.EXCEPTION))
                .andExpect(redirectedUrl(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)));
    }

    public void saveMenuItemWithNoMenuItem() throws Exception {
        mockMvc.perform(post(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(null))
                .andExpect(redirectedUrl(null));
    }
}
