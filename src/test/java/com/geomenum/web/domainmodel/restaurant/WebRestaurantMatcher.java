/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.restaurant;

import com.geomenum.web.controllers.restaurant.RestaurantQueryWebController;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class WebRestaurantMatcher extends BaseMatcher<WebRestaurant> {

    private WebRestaurant expectedRestaurant;

    public WebRestaurantMatcher(WebRestaurant expectedRestaurant) {
        this.expectedRestaurant = expectedRestaurant;
    }

    @Override
    public boolean matches(Object o) {
        if(o instanceof WebRestaurant) {
            WebRestaurant actualRestaurant = (WebRestaurant) o;
            if(expectedRestaurant.getId().equals(actualRestaurant.getId())
                    && expectedRestaurant.getMenuId().equals(actualRestaurant.getMenuId())
                    && expectedRestaurant.getEnabled().equals(actualRestaurant.getEnabled())
                    && expectedRestaurant.getName().equals(actualRestaurant.getName())
                    && expectedRestaurant.getLocation().equals(actualRestaurant.getLocation())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(RestaurantQueryWebController.restaurantBackingBeanName + " : " + expectedRestaurant.toString());
    }
}
