/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.menu;

import com.geomenum.rest.controllers.menu.MenuQueryRestController;
import com.geomenum.rest.controllers.restaurant.RestaurantQueryRestController;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * {@link RestMenu} resource.
 */
public class MenuResource extends ResourceSupport {

    /**
     * FIXME
     * This field was initially named 'content' but due to technical difficulties with our client side framework (Sencha
     * Touch 2 - TreeStore, root property not functioning ?), we're forced to render this menu resource as the root node of
     * the menu tree itself, hence the field name 'children' (field which is actually the real root of the menu tree).
     */
    private final List<? super ResourceSupport> children;

    public MenuResource(RestMenu menu, Locale locale) {
        if(!menu.getSupportedLanguages().contains(Locale.forLanguageTag(locale.getLanguage()))) {
            if(menu.getSupportedLanguages().contains(Locale.ENGLISH)) {
                locale = Locale.ENGLISH;
            } else {
                locale = menu.getSupportedLanguages().iterator().next();
            }
        }

        children = menu.getMenuTreeAsResource(locale);

        // links
        add(linkTo(MenuQueryRestController.class, menu.getRestaurantId(), menu.getId()).withSelfRel());
        add(linkTo(RestaurantQueryRestController.class, menu.getRestaurantId()).withRel("restaurant"));
    }

    //~ Getters & Setters ==============================================================================================

    public List<? super ResourceSupport> getChildren() {
        return children;
    }
}
