/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.web.domainmodel.WebDomainModelMapper;

import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Web {@link com.geomenum.core.domainmodel.menu.CoreMenuItem}
 */
public class WebMenuItem extends NewMenuItem {

    @NotNull
    private final UUID id = null;
    @NotNull
    private final URL imageURL;
    @NotNull
    private final Boolean hasImage = null;

    // private ctor
    @SuppressWarnings("unused")
    private WebMenuItem(@JsonProperty("localizedNames") Map<Locale, String> localizedNames,
                        @JsonProperty("currency") Currency currency,
                        @JsonProperty("localizedDescriptions") Map<Locale, String> localizedDescriptions,
                        @JsonProperty("imageURI") URI imageURI) throws MalformedURLException {
        super(localizedNames, currency, localizedDescriptions);
        this.imageURL = imageURI.toURL();
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param menuItemDTO a {@link Map} of properties
     * @return a {@link WebMenuItem}
     */
    public static WebMenuItem of(Map<Object, Object> menuItemDTO) {
        return WebDomainModelMapper.fromMap(menuItemDTO, WebMenuItem.class);
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof WebMenuItem) {
            WebMenuItem menuItem = (WebMenuItem) obj;
            if(this.id.equals(menuItem.id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "id : " + id + " / "
                + "localizedNames : " + getLocalizedNames();
    }

    //~ Getters & Setters ==============================================================================================

    public UUID getId() {
        return id;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public Boolean getHasImage() {
        return hasImage;
    }
}
