/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.menu;

import com.geomenum.rest.domainmodel.AbstractResource;

import java.util.Locale;

/**
 * {@link RestMenuItem} resource.
 */
public class MenuItemResource extends AbstractResource {

    private final String name;
    private final double amount;
    private final String currency;
    private final String description;
    private final String imageURL;
    private final boolean hasImage;

    /**
     * Needed to tell our client-side JSON-parser (currently Sencha Touch 2) that this resource represent
     * the content of a leaf node.
     */
    private final boolean leaf = true;

    public MenuItemResource(RestMenuItem menuItem, Locale locale) {
        Locale language = Locale.forLanguageTag(locale.getLanguage());

        name = menuItem.getLocalizedNames().get(language);
        amount = menuItem.getAmount().doubleValue();
        currency = menuItem.getCurrency().getCurrencyCode(); //menuItem.getCurrency().getSymbol(locale);
        description = menuItem.getLocalizedDescriptions().get(language);
        imageURL = menuItem.getImageURL().toString();
        hasImage = menuItem.getHasImage();
    }

    //~ Getters & Setters ==============================================================================================

    public boolean isLeaf() {
        return leaf;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public boolean getHasImage() {
        return hasImage;
    }
}
