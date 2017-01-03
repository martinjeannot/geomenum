/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.r2d2.common.RequestDispatcher;
import com.geomenum.r2d2.common.RequestDispatcherFactory;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.geomenum.config.TestingLevels.UNIT;

@Test(groups = {UNIT})
public abstract class AbstractWebControllerUnitTests {

    protected static final String ERROR_RESOURCE = "/WEB-INF/views/error.html";

    protected MockMvc mockMvc;

    @Mock
    private RequestDispatcherFactory requestDispatcherFactory;

    @Mock
    protected RequestDispatcher requestDispatcher;

    protected abstract AbstractWebController getController();

    @BeforeClass
    protected void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(requestDispatcherFactory.createRequestDispatcher()).thenReturn(requestDispatcher);
        mockMvc = MockMvcBuilders.standaloneSetup(getController())
                .setViewResolvers(viewResolver())
                .build();
    }

    private InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
