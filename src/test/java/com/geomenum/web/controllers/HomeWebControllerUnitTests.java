/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.web.View;
import org.mockito.InjectMocks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeWebControllerUnitTests extends AbstractWebControllerUnitTests {

    private static final String PRIVATE_HOME_RESOURCE = "/WEB-INF/views/private_home.html";

    @InjectMocks
    private PrivateHomeWebController controller;

    @Override
    protected AbstractWebController getController() {
        return controller;
    }

    public void home() throws Exception {
        mockMvc.perform(get("/home"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.PRIVATE_HOME))
                .andExpect(forwardedUrl(PRIVATE_HOME_RESOURCE));
    }
}
