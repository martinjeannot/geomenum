/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.restaurant;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Persistence {@link com.geomenum.core.domainmodel.restaurant.CoreLocation}
 */
public class PersistenceLocation {

    @NotNull
    private final Double latitude = null;
    @NotNull
    private final Double longitude = null;
    @NotBlank
    private final String address = null;
    @NotBlank
    private final String city = null;
    @NotBlank
    private final String postalCode = null;
    @NotBlank
    private final String countryCode = null;
    @NotBlank
    private final String formattedAddress = null;

    //~ Getters & Setters ==============================================================================================

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }
}
