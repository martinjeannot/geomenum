/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.PersistenceIdNotBlank;
import com.geomenum.web.domainmodel.WebDomainModelMapper;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalTime;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;

import static com.geomenum.common.Countries.Country;
import static com.geomenum.common.Countries.getCountries;

/**
 * Web {@link com.geomenum.core.domainmodel.restaurant.CoreRestaurant}
 */
public class WebRestaurant implements Mappable {

    @PersistenceIdNotBlank
    private final String id = null;
    @PersistenceIdNotBlank
    private final String menuId = null;

    // GENERAL
    @NotBlank
    private String name;
    @NotNull
    private Boolean enabled;
    @NotEmpty
    private final LinkedHashSet<Locale> supportedLanguages = null;
    @NotNull
    private Currency currency;
    @NotEmpty
    private LinkedHashMap<Locale, String> localizedDescriptions;
    @NotNull
    private Cuisine cuisine;
    @NotEmpty
    private boolean[] openingDays = new boolean[14];
    @NotEmpty
    private LocalTime[] openingHours = new LocalTime[4];
    @NotNull
    private String localPhoneNumber;
    @NotBlank
    private String internationalDialingCountryCode;
    @NotNull
    private final URL imageURL;
    @NotNull
    private final Boolean hasImage = null;

    // LOCATION
    @NotNull
    @Valid
    private final WebLocation location = null;

    // image upload
    private MultipartFile image;

    // private ctor
    @SuppressWarnings("unused")
    private WebRestaurant(@JsonProperty("imageURI") URI imageURI) throws MalformedURLException {
        this.imageURL = imageURI.toURL();
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param restaurantDTO a {@link Map} of properties
     * @return a {@link WebRestaurant}
     */
    public static WebRestaurant of(Map<Object, Object> restaurantDTO) {
        // phone number (localPhoneNumber & internationalDialingCountryCode)
        String phoneNumber = (String) restaurantDTO.get("phoneNumber");
        String[] phoneNumberParts = phoneNumber.split(" ");
        String internationalDialingCode = phoneNumberParts[0];
        String localPhoneNumber = phoneNumberParts.length > 1 ?
                phoneNumber.substring(internationalDialingCode.length() + 1) : "";
        restaurantDTO.put("localPhoneNumber", localPhoneNumber);
        internationalDialingCode = internationalDialingCode.substring(1);
        for(Country country : getCountries().values()) {
            if(country.getInternationalDialingCode().equals(internationalDialingCode)) {
                internationalDialingCode = country.getIsoCode();
                break;
            }
        }
        restaurantDTO.put("internationalDialingCountryCode", internationalDialingCode);

        return WebDomainModelMapper.fromMap(restaurantDTO, WebRestaurant.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        Map<Object, Object> dto = WebDomainModelMapper.toMap(this);

        // phone number (localPhoneNumber & internationalDialingCountryCode)
        String internationalDialingCode = getCountries().get(internationalDialingCountryCode).getInternationalDialingCode();
        dto.put("phoneNumber", "+" + internationalDialingCode + " " + localPhoneNumber);

        return dto;
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof WebRestaurant) {
            WebRestaurant restaurant = (WebRestaurant) obj;
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
                + "id : " + id + " / "
                + "menuId : " + menuId + " / "
                + "name : " + name;
    }

    //~ Getters & Setters ==============================================================================================

    public String getId() {
        return id;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LinkedHashSet<Locale> getSupportedLanguages() {
        return supportedLanguages;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LinkedHashMap<Locale, String> getLocalizedDescriptions() {
        return localizedDescriptions;
    }

    public void setLocalizedDescriptions(LinkedHashMap<Locale, String> localizedDescriptions) {
        this.localizedDescriptions = localizedDescriptions;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public boolean[] getOpeningDays() {
        return openingDays;
    }

    public void setOpeningDays(boolean[] openingDays) {
        this.openingDays = openingDays;
    }

    public LocalTime[] getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(LocalTime[] openingHours) {
        this.openingHours = openingHours;
    }

    public String getLocalPhoneNumber() {
        return localPhoneNumber;
    }

    public void setLocalPhoneNumber(String localPhoneNumber) {
        this.localPhoneNumber = localPhoneNumber;
    }

    public String getInternationalDialingCountryCode() {
        return internationalDialingCountryCode;
    }

    public void setInternationalDialingCountryCode(String internationalDialingCountryCode) {
        this.internationalDialingCountryCode = internationalDialingCountryCode;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Boolean getHasImage() {
        return hasImage;
    }

    public WebLocation getLocation() {
        return location;
    }

    /**
     * Needed for templating purposes.
     * @return null
     */
    @SuppressWarnings("unused")
    @JsonIgnore
    public Object getTempAddedLanguage() {
        return null;
    }
}
