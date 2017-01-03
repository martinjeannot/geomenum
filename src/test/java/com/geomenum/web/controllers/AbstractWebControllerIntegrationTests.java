/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers;

import com.geomenum.config.CoreTestConfiguration;
import com.geomenum.config.PersistenceTestConfiguration;
import com.geomenum.config.WebSecurityTestConfiguration;
import com.geomenum.config.WebTestConfiguration;
import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.menu.PersistenceMenuFixtures;
import com.geomenum.persistence.domainmodel.restaurant.PersistenceRestaurantFixtures;
import com.geomenum.persistence.domainmodel.system.PersistenceUserFixtures;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
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

@WebAppConfiguration
@ContextConfiguration(classes = {
        WebSecurityTestConfiguration.class,
        WebTestConfiguration.class,
        CoreTestConfiguration.class,
        PersistenceTestConfiguration.class})
@Test(groups = {INTEGRATION})
public abstract class AbstractWebControllerIntegrationTests extends AbstractTestNGSpringContextTests {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    protected MongoOperations mongoOps;

    @BeforeClass
    public void setUpBeforeClass() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    //~ SECURITY UTIL ==================================================================================================

    protected MockHttpSession loggedSession() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("morgan.sullivan@gmail.com", "Passw0rd");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return session;
    }

    //~ UI UTIL ========================================================================================================

    private List<Map<String, String>> breadcrumbNavigationBarStub;

    protected List<Map<String, String>> breadcrumbNavigationBarStub() {
        if(breadcrumbNavigationBarStub == null) {
            breadcrumbNavigationBarStub = Lists.newArrayList();
            breadcrumbNavigationBarStub.add(Maps.<String, String>newHashMap());
        }
        return breadcrumbNavigationBarStub;
    }

    //~ PERSISTENCE UTIL ===============================================================================================

    protected void insertStandardUser() {
        mongoOps.insert(PersistenceUserFixtures.standardUser());
    }

    protected void clearUsersCollection() {
        mongoOps.remove(new Query(), DBCollection.USERS);
    }

    protected void insertStandardMenu() {
        mongoOps.insert(PersistenceMenuFixtures.standardMenu());
    }

    protected void clearMenusCollection() {
        mongoOps.remove(new Query(), DBCollection.MENUS);
    }

    protected void insertStandardRestaurant() {
        mongoOps.insert(PersistenceRestaurantFixtures.standardRestaurant());
    }

    protected void clearRestaurantsCollection() {
        mongoOps.remove(new Query(), DBCollection.RESTAURANTS);
    }
}
