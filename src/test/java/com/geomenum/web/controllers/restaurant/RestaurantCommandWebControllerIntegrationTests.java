/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.restaurant;

import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebControllerIntegrationTests;
import com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures.standardUpdatedRestaurant;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RestaurantCommandWebControllerIntegrationTests extends AbstractWebControllerIntegrationTests {

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

    public void saveRestaurant() throws Exception {
        insertStandardRestaurant();

        mockMvc.perform(post(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)).session(loggedSession())
                .sessionAttr(RestaurantQueryWebController.restaurantBackingBeanName, standardUpdatedRestaurant()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)));
    }

    public void saveRestaurantWithBindingErrors() throws Exception {
        insertStandardRestaurant();

        mockMvc.perform(post(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)).session(loggedSession())
                .sessionAttr(RestaurantQueryWebController.restaurantBackingBeanName, standardUpdatedRestaurant())
                .param("enabled", "")) // binding error
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.RESTAURANT))
                .andExpect(model().attributeExists(
                        RestaurantQueryWebController.restaurantBackingBeanName,
                        "mode"))
                .andExpect(model().attributeHasFieldErrors(RestaurantQueryWebController.restaurantBackingBeanName, "enabled"))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(request().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(forwardedUrl(null));
    }

    public void saveRestaurantWithException() throws Exception {
        mockMvc.perform(post(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)).session(loggedSession())
                .sessionAttr(RestaurantQueryWebController.restaurantBackingBeanName, standardUpdatedRestaurant()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.EXCEPTION))
                .andExpect(redirectedUrl(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)));
    }

    public void saveRestaurantWithNoRestaurant() throws Exception {
        mockMvc.perform(post(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)).session(loggedSession()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(null))
                .andExpect(redirectedUrl(null));
    }
}
