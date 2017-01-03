/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.web.View;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.geomenum.config.TestingLevels.UNIT;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@Test(groups = {UNIT})
public class WelcomeWebControllerUnitTests {

    private static final String PUBLIC_HOME_RESOURCE = "/WEB-INF/views/public_home.html";

    private MockMvc mockMvc;

    @InjectMocks
    private PublicHomeWebController controller;

    @BeforeClass
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(controller)
                .setViewResolvers(viewResolver())
                .build();
    }

    public void root() throws Exception {
        mockMvc.perform(get("/"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.PUBLIC_HOME))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(PUBLIC_HOME_RESOURCE));
    }

    //~ UTIL ===========================================================================================================

    private InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
