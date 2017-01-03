/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.PersistenceIdNotBlank;
import com.geomenum.rest.domainmodel.RestDomainModelMapper;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.hateoas.Identifiable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;

public class RestRestaurant implements Mappable, Identifiable<String> {

    @PersistenceIdNotBlank
    private final String id = null;
    @PersistenceIdNotBlank
    private final String menuId = null;
    @NotBlank
    private final String name = null;
    @NotNull
    @Valid
    private final RestLocation location = null;
    @NotEmpty
    private final LinkedHashSet<Locale> supportedLanguages = null;
    @NotEmpty
    private final LinkedHashMap<Locale, String> localizedDescriptions = null;
    @NotNull
    private final Cuisine cuisine = null;
    @NotBlank
    private final String phoneNumber = null;
    @NotEmpty
    private final boolean[] openingDays = new boolean[14];
    @NotEmpty
    private final LocalTime[] openingHours = new LocalTime[4];
    @NotNull
    private final URL imageURL;
    @NotNull
    private final Boolean hasImage = null;

    // private ctor
    @SuppressWarnings("unused")
    private RestRestaurant(@JsonProperty("imageURI") URI imageURI) throws MalformedURLException {
        this.imageURL = imageURI.toURL();
    }

    /**
     * Returns {@code true} if the restaurant is open at the given timestamp, {@code false} otherwise (i.e closed).<br/>
     * Timezones are not taken into consideration so results will only be valid within the same timezone.
     *
     * @param timestamp the timestamp to parse
     * @return a boolean
     */
    public boolean isOpen(long timestamp) {
        LocalDateTime date = new LocalDateTime(timestamp);
        long millisOfDay = date.getMillisOfDay();
        int dayOfWeek = date.getDayOfWeek();
        if (openingHours[0] != null && openingHours[1] != null
                && openingHours[0].getMillisOfDay() < millisOfDay
                && openingHours[1].getMillisOfDay() > millisOfDay) {
            // Lunch checks
            switch (dayOfWeek) {
                case DateTimeConstants.MONDAY:
                    return openingDays[0];
                case DateTimeConstants.TUESDAY:
                    return openingDays[2];
                case DateTimeConstants.WEDNESDAY:
                    return openingDays[4];
                case DateTimeConstants.THURSDAY:
                    return openingDays[6];
                case DateTimeConstants.FRIDAY:
                    return openingDays[8];
                case DateTimeConstants.SATURDAY:
                    return openingDays[10];
                case DateTimeConstants.SUNDAY:
                    return openingDays[12];
            }
        } else if (openingHours[2] != null && openingHours[3] != null
                && openingHours[2].getMillisOfDay() < millisOfDay
                && openingHours[3].getMillisOfDay() > millisOfDay) {
            // Dinner checks
            switch (dayOfWeek) {
                case DateTimeConstants.MONDAY:
                    return openingDays[1];
                case DateTimeConstants.TUESDAY:
                    return openingDays[3];
                case DateTimeConstants.WEDNESDAY:
                    return openingDays[5];
                case DateTimeConstants.THURSDAY:
                    return openingDays[7];
                case DateTimeConstants.FRIDAY:
                    return openingDays[9];
                case DateTimeConstants.SATURDAY:
                    return openingDays[11];
                case DateTimeConstants.SUNDAY:
                    return openingDays[13];
            }
        }
        return false;
    }

    /**
     * Returns the openings days and hours as a String array.
     *
     * @param closedMessage the localized message if restaurant is closed
     * @return a String array
     */
    @JsonIgnore
    public String[] getOpeningDaysAndHours(String closedMessage) {
        String[] openingDaysAndHours = new String[14];
        for (int i = 0; i < 7; i++) {
            // lunches
            if (openingDays[i * 2]
                    && openingHours[0] != null
                    && openingHours[1] != null) {
                openingDaysAndHours[i * 2] = openingHours[0].toString("HH:mm", Locale.ENGLISH) +
                        " - " + openingHours[1].toString("HH:mm", Locale.ENGLISH);
            } else {
                openingDaysAndHours[i * 2] = closedMessage;
            }
            // dinners
            if (openingDays[i * 2 + 1]
                    && openingHours[2] != null
                    && openingHours[3] != null) {
                openingDaysAndHours[i * 2 + 1] = openingHours[2].toString("HH:mm", Locale.ENGLISH) +
                        " - " + openingHours[3].toString("HH:mm", Locale.ENGLISH);
            } else {
                openingDaysAndHours[i * 2 + 1] = closedMessage;
            }
        }
        return openingDaysAndHours;
    }

    /**
     * Returns the image URL for this restaurant.
     *
     * @return the URL as a {@code String}
     */
    /*@JsonIgnore
    public String getImageURL() {
        return "";
    }*/

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param restaurantDTO a {@link Map} of properties
     * @return a {@link RestRestaurant}
     */
    public static RestRestaurant of(Map<Object, Object> restaurantDTO) {
        return RestDomainModelMapper.fromMap(restaurantDTO, RestRestaurant.class);
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
        if(obj instanceof RestRestaurant) {
            RestRestaurant restaurant = (RestRestaurant) obj;
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

    public RestLocation getLocation() {
        return location;
    }

    public LinkedHashSet<Locale> getSupportedLanguages() {
        return supportedLanguages;
    }

    public LinkedHashMap<Locale, String> getLocalizedDescriptions() {
        return localizedDescriptions;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean[] getOpeningDays() {
        return openingDays;
    }

    public LocalTime[] getOpeningHours() {
        return openingHours;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public Boolean getHasImage() {
        return hasImage;
    }
}
