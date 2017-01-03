/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebControllerIntegrationTests;
import com.geomenum.web.domainmodel.menu.WebMenuItemFixtures;
import com.geomenum.web.domainmodel.menu.WebMenuItemMatcher;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.web.domainmodel.menu.WebMenuItemFixtures.standardMenuItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MenuItemQueryWebControllerIntegrationTests extends AbstractWebControllerIntegrationTests {

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

    public void viewMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(get(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.MENU_ITEM))
                .andExpect(model().attributeExists(
                        MenuItemQueryWebController.menuItemBackingBeanName,
                        MenuItemQueryWebController.breadcrumbNavigationBarBeanName))
                .andExpect(model().attribute(MenuItemQueryWebController.menuItemBackingBeanName, new WebMenuItemMatcher(standardMenuItem())))
                .andExpect(forwardedUrl(null));
    }

    public void viewMenuItemWithNoMenuItem() throws Exception {
        mockMvc.perform(get(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(forwardedUrl(null));
    }

    public void editMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(get(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID) + "?mode=edit").session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.MENU_ITEM))
                .andExpect(model().attributeExists(
                        MenuItemQueryWebController.menuItemBackingBeanName,
                        MenuItemQueryWebController.breadcrumbNavigationBarBeanName,
                        "mode"))
                .andExpect(model().attribute(MenuItemQueryWebController.menuItemBackingBeanName, new WebMenuItemMatcher(standardMenuItem())))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(forwardedUrl(null));
    }

    public void editMenuItemWithNoMenuItem() throws Exception {
        mockMvc.perform(get(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID) + "?mode=edit").session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(forwardedUrl(null));
    }
}
