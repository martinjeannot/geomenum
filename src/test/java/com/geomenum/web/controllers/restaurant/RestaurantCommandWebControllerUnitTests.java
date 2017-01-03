/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.restaurant;

import com.geomenum.web.RequestAttribute;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.AbstractWebControllerUnitTests;
import com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures;
import com.geomenum.web.requestsresponses.restaurant.SaveRestaurantRequest;
import com.geomenum.web.requestsresponses.restaurant.SaveRestaurantResponse;
import com.geomenum.web.requestsresponses.restaurant.SaveRestaurantResponseFixtures;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.web.HttpSessionRequiredException;
import org.testng.annotations.Test;

import static com.geomenum.web.domainmodel.restaurant.WebRestaurantFixtures.standardUpdatedRestaurant;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RestaurantCommandWebControllerUnitTests extends AbstractWebControllerUnitTests {

    private static final String RESTAURANT_RESOURCE = "/WEB-INF/views/restaurant/restaurant.html";

    @InjectMocks
    private RestaurantCommandWebController controller;

    @Override
    protected AbstractWebController getController() {
        return controller;
    }

    public void saveRestaurant() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(SaveRestaurantRequest.class), eq(SaveRestaurantResponse.class)))
                .thenReturn(SaveRestaurantResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID))
                .sessionAttr(RestaurantQueryWebController.restaurantBackingBeanName, standardUpdatedRestaurant()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)));
    }

    public void saveRestaurantWithBindingErrors() throws Exception {
        mockMvc.perform(post(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID))
                .sessionAttr(RestaurantQueryWebController.restaurantBackingBeanName, standardUpdatedRestaurant())
                .param("enabled", "")) // binding error
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(View.RESTAURANT))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists(
                        RestaurantQueryWebController.restaurantBackingBeanName,
                        "mode"))
                .andExpect(model().attributeHasFieldErrors(RestaurantQueryWebController.restaurantBackingBeanName, "enabled"))
                .andExpect(model().attribute("mode", "edit"))
                .andExpect(request().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(forwardedUrl(RESTAURANT_RESOURCE));
    }

    public void saveRestaurantWithException() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(SaveRestaurantRequest.class), eq(SaveRestaurantResponse.class)))
                .thenReturn(SaveRestaurantResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID))
                .sessionAttr(RestaurantQueryWebController.restaurantBackingBeanName, standardUpdatedRestaurant()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SAVE_MESSAGE, RequestAttribute.Value.EXCEPTION))
                .andExpect(redirectedUrl(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)));
    }

    @Test(expectedExceptions = HttpSessionRequiredException.class,
            expectedExceptionsMessageRegExp = "Expected session attribute 'restaurant'")
    public void saveRestaurantWithNoRestaurant() throws Exception {
        mockMvc.perform(post(WebURLPath.getRestaurantURL(WebRestaurantFixtures.ID)));
    }
}
