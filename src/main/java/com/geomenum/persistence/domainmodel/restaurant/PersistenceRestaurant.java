/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.common.integration.Mappable;
import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.PersistenceDomainModelMapper;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.*;

/**
 * Persistence {@link com.geomenum.core.domainmodel.restaurant.CoreRestaurant}
 */
@Document(collection = DBCollection.RESTAURANTS)
public class PersistenceRestaurant implements Mappable {

    @Id
    @NotNull
    private final ObjectId id = null;
    @Indexed
    @NotNull
    private final ObjectId menuId = null;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    @NotNull
    private final Point geolocation;
    @NotNull
    @Valid
    private final PersistenceLocation location;
    @NotBlank
    private final String name = null;
    @NotNull
    private final Boolean enabled = null;
    @NotEmpty
    private final LinkedHashSet<Locale> supportedLanguages = null;
    @NotNull
    private final Currency currency = null;
    @NotEmpty
    private final LinkedHashMap<Locale, String> localizedDescriptions = null;
    @NotNull
    private final Cuisine cuisine = null;
    @NotEmpty
    private final boolean[] openingDays = new boolean[14];
    @NotEmpty
    private final LocalTime[] openingHours = new LocalTime[4];
    @NotBlank
    private final String phoneNumber = null;
    @NotNull
    private final URI imageURI = null;
    @NotNull
    private final Boolean hasImage = null;

    // Time-tracking fields
    @NotNull
    private final LocalDateTime creationDate = null;
    private final LocalDateTime lastUpdateDate = null;
    private final LocalDateTime deletionDate = null;

    // private ctor
    @SuppressWarnings("unused")
    private PersistenceRestaurant(@JsonProperty("location") PersistenceLocation location) {
        this.location = location;
        this.geolocation = new Point(location.getLatitude(), location.getLongitude());
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param restaurantDTO a {@link Map} of properties
     * @return a {@link PersistenceRestaurant}
     */
    public static PersistenceRestaurant of(Map<Object, Object> restaurantDTO) {
        return PersistenceDomainModelMapper.fromMap(restaurantDTO, PersistenceRestaurant.class);
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
        if(obj instanceof PersistenceRestaurant) {
            PersistenceRestaurant restaurant = (PersistenceRestaurant) obj;
            if(this.id.equals(restaurant.id)
                    && this.menuId.equals(restaurant.menuId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + menuId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "id : " + id + "/"
                + "menuId : " + menuId + "/"
                + "name : " + name;
    }

    //~ Getters & Setters ==============================================================================================

    public ObjectId getId() {
        return id;
    }

    public ObjectId getMenuId() {
        return menuId;
    }

    public Point getGeolocation() {
        return geolocation;
    }

    public PersistenceLocation getLocation() {
        return location;
    }

    public String getName() {
        return name;
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

    public LinkedHashMap<Locale, String> getLocalizedDescriptions() {
        return localizedDescriptions;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public boolean[] getOpeningDays() {
        return openingDays;
    }

    public LocalTime[] getOpeningHours() {
        return openingHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public URI getImageURI() {
        return imageURI;
    }

    public Boolean getHasImage() {
        return hasImage;
    }
}
