/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.menu;

import com.geomenum.common.integration.Mappable;
import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.PersistenceDomainModelMapper;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Currency;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;

/**
 * Persistence {@link com.geomenum.core.domainmodel.menu.CoreMenu}
 */
@Document(collection = DBCollection.MENUS)
public class PersistenceMenu implements Mappable {

    @Id
    @NotNull
    private final ObjectId id = null;
    @Indexed
    @NotNull
    private final ObjectId restaurantId = null;
    @NotEmpty
    private final Map<Object, Object> menuTree = null;
    @NotNull
    private final Boolean enabled = null;
    @NotEmpty
    private final LinkedHashSet<Locale> supportedLanguages = null;
    @NotNull
    private final Currency currency = null;

    // Time-tracking fields
    @NotNull
    private final LocalDateTime creationDate = null;
    private final LocalDateTime lastUpdateDate = null;
    private final LocalDateTime deletionDate = null;


    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param menuDTO a {@link Map} of properties
     * @return a {@link PersistenceMenu}
     */
    public static PersistenceMenu of(Map<Object, Object> menuDTO) {
        return PersistenceDomainModelMapper.fromMap(menuDTO, PersistenceMenu.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        return PersistenceDomainModelMapper.toMap(this);
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof PersistenceMenu) {
            PersistenceMenu menu = (PersistenceMenu) obj;
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

    public ObjectId getId() {
        return id;
    }

    public ObjectId getRestaurantId() {
        return restaurantId;
    }

    public Map<Object, Object> getMenuTree() {
        return menuTree;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public LinkedHashSet<Locale> getSupportedLanguages() {
        return supportedLanguages;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public LocalDateTime getDeletionDate() {
        return deletionDate;
    }
}
