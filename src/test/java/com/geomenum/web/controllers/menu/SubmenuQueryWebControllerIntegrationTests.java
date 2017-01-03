/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebControllerIntegrationTests;
import com.geomenum.web.domainmodel.menu.WebSubmenuFixtures;
import com.geomenum.web.domainmodel.menu.WebSubmenuMatcher;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.web.domainmodel.menu.WebSubmenuFixtures.standardSubmenu;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubmenuQueryWebControllerIntegrationTests extends AbstractWebControllerIntegrationTests {

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

    public void viewSubmenu() throws Exception {
        insertStandardMenu();

        mockMvc.perform(get(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.SUBMENU))
                .andExpect(model().attributeExists(
                        SubmenuQueryWebController.submenuBackingBeanName,
                        SubmenuQueryWebController.breadcrumbNavigationBarBeanName,
                        SubmenuQueryWebController.submenuDashboardInfoBeanName))
                .andExpect(model().attribute(SubmenuQueryWebController.submenuBackingBeanName, new WebSubmenuMatcher(standardSubmenu())))
                .andExpect(forwardedUrl(null));
    }

    public void viewSubmenuWithNoSubmenu() throws Exception {
        mockMvc.perform(get(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(forwardedUrl(null));
    }

    public void editSubmenu() throws Exception {
        insertStandardMenu();

        mockMvc.perform(get(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID) + "?mode=edit").session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.SUBMENU))
                .andExpect(model().attributeExists(
                        SubmenuQueryWebController.submenuBackingBeanName,
                        SubmenuQueryWebController.breadcrumbNavigationBarBeanName,
                        SubmenuQueryWebController.submenuDashboardInfoBeanName,
                        "mode"))
                .andExpect(model().attribute(SubmenuQueryWebController.submenuBackingBeanName, new WebSubmenuMatcher(standardSubmenu())))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(forwardedUrl(null));
    }

    public void editSubmenuWithNoSubmenu() throws Exception {
        mockMvc.perform(get(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID) + "?mode=edit").session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(forwardedUrl(null));
    }
}
