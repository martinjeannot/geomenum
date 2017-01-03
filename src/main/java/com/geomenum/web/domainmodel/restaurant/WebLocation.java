/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.restaurant;

import com.geomenum.common.integration.Mappable;
import com.geomenum.web.domainmodel.WebDomainModelMapper;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

public class WebLocation implements Mappable {

    private double latitude;
    private double longitude;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String countryCode;
    @NotBlank
    private String formattedAddress;

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param locationDTO a {@link Map} of properties
     * @return a {@link WebLocation}
     */
    public static WebLocation of(Map<Object, Object> locationDTO) {
        return WebDomainModelMapper.fromMap(locationDTO, WebLocation.class);
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
        if(obj instanceof WebLocation) {
            WebLocation location = (WebLocation) obj;
            if(this.latitude == location.latitude
                    && this.longitude == location.longitude) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) (Double.doubleToLongBits(latitude) ^ (Double.doubleToLongBits(latitude) >>> 32));
        result = 31 * result + (int) (Double.doubleToLongBits(longitude) ^ (Double.doubleToLongBits(longitude) >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "latitude : " + latitude + " / "
                + "longitude : " + longitude;
    }

    //~ Getters & Setters ==============================================================================================

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
