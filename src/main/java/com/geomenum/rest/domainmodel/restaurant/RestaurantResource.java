/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.restaurant;

import com.geomenum.rest.controllers.menu.MenuQueryRestController;
import com.geomenum.rest.controllers.restaurant.RestaurantQueryRestController;
import com.geomenum.rest.domainmodel.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * {@link RestRestaurant} resource.
 */
public class RestaurantResource extends AbstractResource {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantResource.class);

    private final String CLOSED_MESSAGE_KEY = "restaurant.closed";

    private final String name;
    private final String address;
    private final String description;
    private final String cuisine;
    private final String phoneNumber;
    private final boolean open;
    private final String[] openingDaysAndHours;
    private final String imageURL;
    private final boolean hasImage;

    public RestaurantResource(RestRestaurant restaurant, Locale locale, MessageSource messageSource, long timestamp) {
        if(!restaurant.getSupportedLanguages().contains(Locale.forLanguageTag(locale.getLanguage()))) {
            if(restaurant.getSupportedLanguages().contains(Locale.ENGLISH)) {
                locale = Locale.ENGLISH;
            } else {
                locale = restaurant.getSupportedLanguages().iterator().next();
            }
        }
        Locale language = Locale.forLanguageTag(locale.getLanguage());

        name = restaurant.getName();
        address = restaurant.getLocation().getFormattedAddress();
        description = restaurant.getLocalizedDescriptions().get(language);
        cuisine = messageSource.getMessage(restaurant.getCuisine().getMessageKey(), null, locale);
        phoneNumber = restaurant.getPhoneNumber();
        open = restaurant.isOpen(timestamp);
        openingDaysAndHours = restaurant.getOpeningDaysAndHours(
                messageSource.getMessage(CLOSED_MESSAGE_KEY, null, locale));
        imageURL = restaurant.getImageURL().toString();
        hasImage = restaurant.getHasImage();

        // links
        add(linkTo(RestaurantQueryRestController.class, restaurant.getId()).withSelfRel());
        add(linkTo(MenuQueryRestController.class, restaurant.getId(), restaurant.getMenuId()).withRel("menu"));
    }

    //~ Getters & Setters ==============================================================================================

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isOpen() {
        return open;
    }

    public String[] getOpeningDaysAndHours() {
        return openingDaysAndHours;
    }

    public String getImageURL() {
        return imageURL;
    }

    public boolean getHasImage() {
        return hasImage;
    }
}
