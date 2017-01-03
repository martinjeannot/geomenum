/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.web.domainmodel.WebDomainModelMapper;

import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Web {@link com.geomenum.core.domainmodel.menu.CoreSubmenu}
 */
public class WebSubmenu extends NewSubmenu {

    @NotNull
    private final UUID id = null;

    // private ctor
    @SuppressWarnings("unused")
    private WebSubmenu(@JsonProperty("localizedNames") Map<Locale, String> localizedNames,
                       @JsonProperty("enabled") Boolean enabled) {
        super(localizedNames, enabled);
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param submenuDTO a {@link Map} of properties
     * @return a {@link WebSubmenu}
     */
    public static WebSubmenu of(Map<Object, Object> submenuDTO) {
        return WebDomainModelMapper.fromMap(submenuDTO, WebSubmenu.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        return WebDomainModelMapper.toMap(this);
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof WebSubmenu) {
            WebSubmenu submenu = (WebSubmenu) obj;
            if(this.id.equals(submenu.id)) {
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
}
