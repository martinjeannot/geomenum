/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.PersistenceIdNotBlank;
import com.geomenum.rest.datamodel.tree.consolidatedmenu.RestMenuNode;
import com.geomenum.rest.domainmodel.RestDomainModelMapper;
import com.google.common.collect.Lists;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * {@link com.geomenum.core.domainmodel.menu.CoreMenu}
 */
public class RestMenu implements Mappable, Identifiable<String> {

    @PersistenceIdNotBlank
    private final String id = null;
    @PersistenceIdNotBlank
    private final String restaurantId = null;
    @NotNull
    @Valid
    private final RestMenuNode root = null;
    @NotEmpty
    private final LinkedHashSet<Locale> supportedLanguages = null;

    public List<? super ResourceSupport> getMenuTreeAsResource(Locale locale) {
        List<? super ResourceSupport> menuTree = Lists.newArrayList();
        for(RestMenuNode childNode : root.getChildrenAsRestMenuNode()) {
            if(childNode.getContent().getEnabled()) {
                menuTree.add(childNode.getResource(locale));
            }
        }
        return menuTree;
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param menuDTO a {@link Map} of properties
     * @return a {@link RestMenu}
     */
    public static RestMenu of(Map<Object, Object> menuDTO) {
        return RestDomainModelMapper.fromMap(menuDTO, RestMenu.class);
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
        if(obj instanceof RestMenu) {
            RestMenu menu = (RestMenu) obj;
            if(this.id.equals(menu.id)
                    && this.restaurantId.equals(menu.restaurantId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + restaurantId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "id : " + id + "/"
                + "restaurantId : " + restaurantId;
    }

    //~ Getters & Setters ==============================================================================================

    public String getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    @JsonProperty("menuTree")
    public RestMenuNode getRoot() {
        return root;
    }

    public LinkedHashSet<Locale> getSupportedLanguages() {
        return supportedLanguages;
    }
}
