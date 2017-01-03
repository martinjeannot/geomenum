/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.common.Currencies;
import com.geomenum.common.Languages;
import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.PersistenceIdNotBlank;
import com.geomenum.core.datamodel.tree.menu.BranchMenuNode;
import com.geomenum.core.datamodel.tree.menu.MenuNode;
import com.geomenum.core.domainmodel.CoreDomainModelMapper;
import com.geomenum.core.domainmodel.bi.TimeTrackable;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.springframework.context.MessageSource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * This class represents the whole Menu of a restaurant.<br/>
 * It holds the full hierarchy of submenus (as branch nodes) and menu items (as leaf nodes). This hierarchy is currently
 * represented as a tree accessible by its root node ({@link #getRoot()}).
 */
public class CoreMenu implements Mappable, TimeTrackable {

    @PersistenceIdNotBlank
    private final String id = null;
    @PersistenceIdNotBlank
    private final String restaurantId = null;
    @NotNull
    @Valid
    private final MenuNode root = null;
    @NotNull
    private Boolean enabled;
    @NotEmpty
    private final Set<Locale> supportedLanguages = null;
    @NotNull
    private Currency currency;

    // Time-tracking fields
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private LocalDateTime deletionDate;

    /**
     * Creates a new menu for a given restaurant.
     *
     * @param restaurant the related restaurant
     * @param messageSource a {@link MessageSource}
     * @return a new {@link CoreMenu}
     */
    public static CoreMenu createNewMenu(CoreRestaurant restaurant, MessageSource messageSource) {
        Map<Object, Object> newMenuDTO = Maps.newHashMap();
        newMenuDTO.put("id", restaurant.getMenuId());
        newMenuDTO.put("restaurantId", restaurant.getId());

        LinkedHashSet<Locale> supportedLanguages = Sets.newLinkedHashSet();
        supportedLanguages.addAll(restaurant.getSupportedLanguages());
        newMenuDTO.put("supportedLanguages", supportedLanguages);

        // populating the DTO from default values
        MenuNode root = new BranchMenuNode(CoreSubmenu.createNewSubmenuAsRoot(supportedLanguages, messageSource));
        newMenuDTO.put("menuTree", root.toMap());
        newMenuDTO.put("enabled", false);
        newMenuDTO.put("currency", restaurant.getCurrency());

        return of(newMenuDTO);
    }

    /**
     * Synchronizes this menu with the given restaurant.<br/>
     * Returns {@code true} if the menu needs to be updated afterwards, false otherwise.
     *
     * @param restaurant the restaurant to sync with
     * @return a boolean
     */
    public boolean synchronizeWithRestaurant(CoreRestaurant restaurant) {
        boolean updateNeeded = false;
        Objects.requireNonNull(restaurant);
        if(!restaurant.getId().equals(getRestaurantId()) || !restaurant.getMenuId().equals(getId())) {
            throw new IllegalArgumentException("Cannot synchronize menu " + getId() + " with restaurant " + restaurant.getId());
        }
        // enabled
        if(!restaurant.getEnabled().equals(enabled)) {
            enabled = restaurant.getEnabled();
            updateNeeded = true;
        }
        // currency
        if(!restaurant.getCurrency().equals(currency)) {
            currency = restaurant.getCurrency();
            root.changeCurrency(restaurant.getCurrency());
            updateNeeded = true;
        }
        return updateNeeded;
    }

    /**
     * Add support to the given language.
     *
     * @param language the new language to support
     * @param messageSource messageSource
     */
    public void addLanguageSupport(Locale language, MessageSource messageSource) {
        if(supportedLanguages.contains(language)) {
            throw new IllegalArgumentException("Cannot support an already supported language : " + language);
        }
        if(!Languages.LANGUAGES.contains(language)) {
            throw new IllegalArgumentException("Cannot add support to language " + language + " (language not available)");
        }

        supportedLanguages.add(language);
        root.addLanguageSupport(language, messageSource);
    }

    /**
     * Remove support of the given language.
     *
     * @param language the language to remove the support of
     */
    public void removeLanguageSupport(Locale language) {
        if(supportedLanguages.size() == 1) {
            throw new IllegalArgumentException("Cannot remove the only supported language");
        }
        if(!supportedLanguages.contains(language)) {
            throw new IllegalArgumentException("Cannot remove support of language " + language + " (language not supported)");
        }

        supportedLanguages.remove(language);
        root.removeLanguageSupport(language);
    }

    //~ TimeTrackable ==================================================================================================

    @Override
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public void setCreationDateToNow() {
        if(creationDate != null) {
            throw new IllegalStateException("Cannot reset the creation date");
        }
        setCreationDate(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    private void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public void setLastUpdateDateToNow() {
        setLastUpdateDate(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getDeletionDate() {
        return deletionDate;
    }

    private void setDeletionDate(LocalDateTime deletionDate) {
        this.deletionDate = deletionDate;
    }

    @Override
    public void setDeletionDateToNow() {
        if(deletionDate != null) {
            throw new IllegalStateException("Cannot reset the deletion date");
        }
        setDeletionDate(LocalDateTime.now());
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param menuDTO a {@link Map} of properties
     * @return a {@link CoreMenu}
     */
    public static CoreMenu of(Map<Object, Object> menuDTO) {
        return CoreDomainModelMapper.fromMap(menuDTO, CoreMenu.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        return CoreDomainModelMapper.toMap(this);
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof CoreMenu) {
            CoreMenu menu = (CoreMenu) obj;
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

    private void setEnabled(Boolean enabled) {
        Objects.requireNonNull(enabled);
        this.enabled = enabled;
    }

    private void setCurrency(Currency currency) {
        if(!Currencies.CURRENCIES.contains(currency)) {
            throw new IllegalArgumentException("Currency " + currency + " is not available");
        }
        this.currency = currency;
    }

    @JsonProperty("menuTree")
    public MenuNode getRoot() {
        return root;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Set<Locale> getSupportedLanguages() {
        return Collections.unmodifiableSet(supportedLanguages);
    }

    public Currency getCurrency() {
        return currency;
    }
}
