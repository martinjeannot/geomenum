/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.restaurant;

import com.geomenum.rest.controllers.menu.MenuQueryRestController;
import com.geomenum.rest.controllers.restaurant.RestaurantQueryRestController;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.ResourceSupport;

import java.util.Locale;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Short {@link RestRestaurant} resource.
 */
public class ShortRestaurantResource extends ResourceSupport {

    private final String name;
    private final String cuisine;

    /**
     * Distance from the requested location when one was provided.<br/>
     * This field is not always populated (only when this restaurant resource is issued from a request specifying
     * location parameters like latitude and longitude).
     */
    private double distance;

    public ShortRestaurantResource(RestRestaurant restaurant, Locale locale, MessageSource messageSource) {
        if(!restaurant.getSupportedLanguages().contains(locale)) {
            if(restaurant.getSupportedLanguages().contains(Locale.ENGLISH)) {
                locale = Locale.ENGLISH;
            } else {
                locale = restaurant.getSupportedLanguages().iterator().next();
            }
        }

        name = restaurant.getName();
        cuisine = messageSource.getMessage(restaurant.getCuisine().getMessageKey(), null, locale);

        // links
        add(linkTo(RestaurantQueryRestController.class, restaurant.getId()).withSelfRel());
        add(linkTo(MenuQueryRestController.class, restaurant.getId(), restaurant.getMenuId()).withRel("menu"));
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        // not the most accurate rounding technique but one of the fastest
        this.distance = (double) Math.round(distance * 1000) / 1000;
    }
}
