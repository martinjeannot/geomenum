/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.config.CoreTestConfiguration;
import com.geomenum.config.PersistenceTestConfiguration;
import com.geomenum.config.WebSecurityTestConfiguration;
import com.geomenum.config.WebTestConfiguration;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.geomenum.config.TestingLevels.INTEGRATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ContextConfiguration(classes = {
        WebSecurityTestConfiguration.class,
        WebTestConfiguration.class,
        CoreTestConfiguration.class,
        PersistenceTestConfiguration.class})
@Test(groups = {INTEGRATION})
public class LoginWebControllerSecurityTest extends AbstractTestNGSpringContextTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @BeforeClass
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
    }

    public void urlSecuredFromAnonymous() throws Exception {
        mockMvc.perform(get(WebURLPath.PRIVATE_HOME))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    public void urlAccessibleFromAuthenticated() throws Exception {
        // header needed by the ui
        Map<String, List<Map<String, String>>> headerStub = Maps.newHashMap();
        headerStub.put("menu", Lists.<Map<String, String>>newArrayList());
        headerStub.put("restaurant", Lists.<Map<String, String>>newArrayList());

        Authentication authentication = new UsernamePasswordAuthenticationToken("morgan.sullivan@gmail.com", "Passw0rd");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        mockMvc.perform(get(WebURLPath.PRIVATE_HOME).session(session)
                .sessionAttr(AbstractWebController.headerBeanName, headerStub)) // needed by the ui
                .andExpect(status().isOk())
                .andExpect(view().name(View.PRIVATE_HOME));
    }

    public void successfulAuthentication() throws Exception {
        mockMvc.perform(post(WebURLPath.LOGIN)
                .param("username", "morgan.sullivan@gmail.com")
                .param("password", "Passw0rd"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl(WebURLPath.PRIVATE_HOME));
    }

    public void failedAuthentication() throws Exception {
        mockMvc.perform(post(WebURLPath.LOGIN)
                .param("username", "darth.vader@deathstar.com")
                .param("password", "skYwalker"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl(WebURLPath.LOGIN + "?error"));
    }

    public void failedAuthenticationRedirection() throws Exception {
        mockMvc.perform(get(WebURLPath.LOGIN).param("error", ""))
                .andExpect(status().isMovedTemporarily())
                .andExpect(flash().attribute(RequestAttribute.Name.LOGIN_MESSAGE, RequestAttribute.Value.ERROR))
                .andExpect(redirectedUrl(WebURLPath.LOGIN));
    }

    public void logout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl(WebURLPath.LOGIN + "?logout"));
    }

    public void logoutRedirection() throws Exception {
        mockMvc.perform(get(WebURLPath.LOGIN).param("logout", ""))
                .andExpect(status().isMovedTemporarily())
                .andExpect(flash().attribute(RequestAttribute.Name.LOGIN_MESSAGE, RequestAttribute.Value.LOGOUT))
                .andExpect(redirectedUrl(WebURLPath.PUBLIC_HOME));
    }
}
