/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebControllerIntegrationTests;
import com.geomenum.web.domainmodel.menu.WebSubmenuFixtures;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.web.domainmodel.menu.WebSubmenuFixtures.standardUpdatedSubmenu;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubmenuCommandWebControllerIntegrationTests extends AbstractWebControllerIntegrationTests {

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

    public void saveSubmenu() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .sessionAttr(SubmenuQueryWebController.submenuBackingBeanName, standardUpdatedSubmenu()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void saveSubmenuWithBindingErrors() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .sessionAttr(SubmenuQueryWebController.submenuBackingBeanName, standardUpdatedSubmenu())
                .sessionAttr(SubmenuQueryWebController.breadcrumbNavigationBarBeanName, breadcrumbNavigationBarStub())
                .param("enabled", "")) // binding error
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.SUBMENU))
                .andExpect(model().attributeExists(
                        SubmenuQueryWebController.submenuBackingBeanName,
                        "mode"))
                .andExpect(model().attributeHasFieldErrors(SubmenuQueryWebController.submenuBackingBeanName, "enabled"))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(request().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(forwardedUrl(null));
    }

    public void saveSubmenuWithException() throws Exception {
        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .sessionAttr(SubmenuQueryWebController.submenuBackingBeanName, standardUpdatedSubmenu()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.EXCEPTION))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void saveSubmenuWithNoSubmenu() throws Exception {
        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(null))
                .andExpect(redirectedUrl(null));
    }
}
