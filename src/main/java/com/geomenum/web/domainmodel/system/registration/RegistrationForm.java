/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.system.registration;

import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.LightPassword;
import com.geomenum.common.validation.constraints.NoDisposableEmail;
import com.geomenum.web.domainmodel.WebDomainModelMapper;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class RegistrationForm implements Mappable {

    // USER
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @NoDisposableEmail
    private String username;
    @NotEmpty
    @LightPassword
    private char[] password;
    // RESTAURANT
    @NotBlank
    private String restaurantName;
    @NotBlank
    private String restaurantCountryCode;
    @NotBlank
    private String restaurantAddress;
    @NotBlank
    private String restaurantPostalCode;
    @NotBlank
    private String restaurantCity;
    // hidden fields
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotBlank
    private String formattedAddress;

    //~ Mappable =======================================================================================================

    @Override
    public Map<Object, Object> toMap() {
        return WebDomainModelMapper.toMap(this);
    }

    //~ Getters & Setters ==============================================================================================

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantCountryCode() {
        return restaurantCountryCode;
    }

    public void setRestaurantCountryCode(String restaurantCountryCode) {
        this.restaurantCountryCode = restaurantCountryCode;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPostalCode() {
        return restaurantPostalCode;
    }

    public void setRestaurantPostalCode(String restaurantPostalCode) {
        this.restaurantPostalCode = restaurantPostalCode;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
