/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.restaurant;

import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebControllerIntegrationTests;
import com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures;
import com.geomenum.web.domainmodel.restaurant.WebRestaurantMatcher;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures.standardRestaurant;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RestaurantQueryWebControllerIntegrationTests extends AbstractWebControllerIntegrationTests {

    @BeforeClass
    public void setUpBeforeClass() {
        super.setUpBeforeClass();
        insertStandardMenu();
    }

    @AfterClass
    public void tearDownAfterClass() {
        clearMenusCollection();
        clearRestaurantsCollection();
    }

    @BeforeMethod
    public void setUpBeforeMethod() {
        clearRestaurantsCollection();
    }

    public void viewRestaurant() throws Exception {
        insertStandardRestaurant();

        mockMvc.perform(get(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.RESTAURANT))
                .andExpect(model().attributeExists(
                        RestaurantQueryWebController.restaurantBackingBeanName))
                .andExpect(model().attribute(RestaurantQueryWebController.restaurantBackingBeanName, new WebRestaurantMatcher(standardRestaurant())))
                .andExpect(forwardedUrl(null));
    }

    public void viewRestaurantWithNoRestaurant() throws Exception {
        mockMvc.perform(get(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(forwardedUrl(null));
    }

    public void editRestaurant() throws Exception {
        insertStandardRestaurant();

        mockMvc.perform(get(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID) + "?mode=edit").session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.RESTAURANT))
                .andExpect(model().attributeExists(
                        RestaurantQueryWebController.restaurantBackingBeanName,
                        "mode"))
                .andExpect(model().attribute(RestaurantQueryWebController.restaurantBackingBeanName, new WebRestaurantMatcher(standardRestaurant())))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(forwardedUrl(null));
    }

    public void editRestaurantWithNoRestaurant() throws Exception {
        mockMvc.perform(get(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID) + "?mode=edit").session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(forwardedUrl(null));
    }
}
