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
import com.geomenum.web.domainmodel.menu.WebSubmenuFixtures;
import com.geomenum.web.requestsresponses.menu.submenu.SaveSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.SaveSubmenuResponse;
import com.geomenum.web.requestsresponses.menu.submenu.SaveSubmenuResponseFixtures;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.web.HttpSessionRequiredException;
import org.testng.annotations.Test;

import static com.geomenum.web.domainmodel.menu.WebSubmenuFixtures.standardUpdatedSubmenu;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubmenuCommandWebControllerUnitTests extends AbstractWebControllerUnitTests {

    private static final String SUBMENU_RESOURCE = "/WEB-INF/views/menu/submenu.html";

    @InjectMocks
    private SubmenuCommandWebController controller;

    @Override
    protected AbstractWebController getController() {
        return controller;
    }

    public void saveSubmenu() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(SaveSubmenuRequest.class), eq(SaveSubmenuResponse.class)))
                .thenReturn(SaveSubmenuResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .sessionAttr(SubmenuQueryWebController.submenuBackingBeanName, standardUpdatedSubmenu()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void saveSubmenuWithBindingErrors() throws Exception {
        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .sessionAttr(SubmenuQueryWebController.submenuBackingBeanName, standardUpdatedSubmenu())
                .param("enabled", "")) // binding error
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.SUBMENU))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists(
                        SubmenuQueryWebController.submenuBackingBeanName,
                        "mode"))
                .andExpect(model().attributeHasFieldErrors(SubmenuQueryWebController.submenuBackingBeanName, "enabled"))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(request().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(forwardedUrl(SUBMENU_RESOURCE));
    }

    public void saveSubmenuWithException() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(SaveSubmenuRequest.class), eq(SaveSubmenuResponse.class)))
                .thenReturn(SaveSubmenuResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .sessionAttr(SubmenuQueryWebController.submenuBackingBeanName, standardUpdatedSubmenu()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.EXCEPTION))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    @Test(expectedExceptions = HttpSessionRequiredException.class,
            expectedExceptionsMessageRegExp = "Expected session attribute 'submenu'")
    public void saveSubmenuWithNoSubmenu() throws Exception {
        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }
}
