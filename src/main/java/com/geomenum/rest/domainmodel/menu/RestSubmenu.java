/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.menu;

import com.geomenum.rest.datamodel.tree.consolidatedmenu.RestMenuNodeContent;
import com.geomenum.rest.domainmodel.RestDomainModelMapper;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.Identifiable;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * {@link com.geomenum.core.domainmodel.menu.CoreSubmenu}
 */
public class RestSubmenu implements RestMenuNodeContent, Identifiable<UUID> {

    @NotNull
    private final UUID id = null;
    @NotEmpty
    private final LinkedHashMap<Locale, String> localizedNames = null;
    @NotNull
    private final Boolean enabled = null;

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param submenuDTO a {@link Map} of properties
     * @return a {@link RestSubmenu}
     */
    public static RestSubmenu of(Map<Object, Object> submenuDTO) {
        return RestDomainModelMapper.fromMap(submenuDTO, RestSubmenu.class);
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
        if(obj instanceof RestSubmenu) {
            RestSubmenu submenu = (RestSubmenu) obj;
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
}
