/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.restaurant;

import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.AbstractWebControllerUnitTests;
import com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures;
import com.geomenum.web.domainmodel.restaurant.WebRestaurantMatcher;
import com.geomenum.web.requestsresponses.restaurant.GetRestaurantRequest;
import com.geomenum.web.requestsresponses.restaurant.GetRestaurantResponse;
import com.geomenum.web.requestsresponses.restaurant.GetRestaurantResponseFixtures;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures.standardRestaurant;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RestaurantQueryWebControllerUnitTests extends AbstractWebControllerUnitTests {

    private static final String RESTAURANT_RESOURCE = "/WEB-INF/views/restaurant/restaurant.html";

    @InjectMocks
    private RestaurantQueryWebController controller;

    @Override
    protected AbstractWebController getController() {
        return controller;
    }

    public void viewRestaurant() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetRestaurantRequest.class), eq(GetRestaurantResponse.class)))
                .thenReturn(GetRestaurantResponseFixtures.successResponse());

        mockMvc.perform(get(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.RESTAURANT))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists(
                        RestaurantQueryWebController.restaurantBackingBeanName))
                .andExpect(model().attribute(RestaurantQueryWebController.restaurantBackingBeanName, new WebRestaurantMatcher(standardRestaurant())))
                .andExpect(forwardedUrl(RESTAURANT_RESOURCE));
    }

    public void viewRestaurantWithNoRestaurant() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetRestaurantRequest.class), eq(GetRestaurantResponse.class)))
                .thenReturn(GetRestaurantResponseFixtures.defaultResponse());

        mockMvc.perform(get(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(ERROR_RESOURCE));
    }

    public void editRestaurant() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetRestaurantRequest.class), eq(GetRestaurantResponse.class)))
                .thenReturn(GetRestaurantResponseFixtures.successResponse());

        mockMvc.perform(get(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID) + "?mode=edit"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.RESTAURANT))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists(
                        RestaurantQueryWebController.restaurantBackingBeanName,
                        "mode"))
                .andExpect(model().attribute(RestaurantQueryWebController.restaurantBackingBeanName, new WebRestaurantMatcher(standardRestaurant())))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(forwardedUrl(RESTAURANT_RESOURCE));
    }

    public void editRestaurantWithNoRestaurant() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(GetRestaurantRequest.class), eq(GetRestaurantResponse.class)))
                .thenReturn(GetRestaurantResponseFixtures.defaultResponse());

        mockMvc.perform(get(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID) + "?mode=edit"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.ERROR))
                .andExpect(model().size(0))
                .andExpect(forwardedUrl(ERROR_RESOURCE));
    }
}
