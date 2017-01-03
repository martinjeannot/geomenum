/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.rest.datamodel.tree.consolidatedmenu.RestMenuNodeContent;
import com.geomenum.rest.domainmodel.RestDomainModelMapper;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.Identifiable;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;

/**
 * {@link com.geomenum.core.domainmodel.menu.CoreMenuItem}
 */
public class RestMenuItem implements RestMenuNodeContent, Identifiable<UUID> {

    @NotNull
    private final UUID id = null;
    @NotEmpty
    private final LinkedHashMap<Locale, String> localizedNames = null;
    @NotNull
    private final Boolean enabled = null;
    @NotNull
    private final BigDecimal amount = null;
    @NotNull
    private final Currency currency = null;
    @NotEmpty
    private final LinkedHashMap<Locale, String> localizedDescriptions = null;
    @NotNull
    private final URL imageURL;
    @NotNull
    private final Boolean hasImage = null;

    // private ctor
    @SuppressWarnings("unused")
    private RestMenuItem(@JsonProperty("imageURI") URI imageURI) throws MalformedURLException {
        this.imageURL = imageURI.toURL();
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param menuItemDTO a {@link Map} of properties
     * @return a {@link RestMenuItem}
     */
    public static RestMenuItem of(Map<Object, Object> menuItemDTO) {
        return RestDomainModelMapper.fromMap(menuItemDTO, RestMenuItem.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        return RestDomainModelMapper.toMap(this);
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof RestMenuItem) {
            RestMenuItem menuItem = (RestMenuItem) obj;
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
                + "localizedNames : " + localizedNames;
    }

    //~ Getters & Setters ==============================================================================================

    public UUID getId() {
        return id;
    }

    public LinkedHashMap<Locale, String> getLocalizedNames() {
        return localizedNames;
    }

    @Override
    public boolean getEnabled() {
        return enabled;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LinkedHashMap<Locale, String> getLocalizedDescriptions() {
        return localizedDescriptions;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public Boolean getHasImage() {
        return hasImage;
    }
}
