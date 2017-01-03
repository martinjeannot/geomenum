/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.restaurant;

import com.geomenum.common.Countries;
import com.geomenum.common.Languages;
import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.PersistenceIdNotBlank;
import com.geomenum.core.domainmodel.CoreDomainModelMapper;
import com.geomenum.core.domainmodel.bi.TimeTrackable;
import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.domainmodel.system.Image;
import com.geomenum.core.services.system.upload.UploadCoreService;
import com.geomenum.core.services.system.upload.UploadException;
import com.geomenum.persistence.services.util.PersistenceIdGenerator;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.*;

public class CoreRestaurant implements Mappable, TimeTrackable {

    private static final Logger logger = LoggerFactory.getLogger(CoreRestaurant.class);

    private static final String DEFAULT_LOCALIZED_DESCRIPTION_KEY = "restaurant.defaultLocalizedDescription";

    @PersistenceIdNotBlank
    private final String id = null;
    @PersistenceIdNotBlank
    private final String menuId = null;
    @NotBlank
    private final String name = null;
    @NotNull
    private final Boolean enabled = null;
    @NotNull
    @Valid
    private final CoreLocation location = null;
    @NotEmpty
    private final Set<Locale> supportedLanguages = null;
    @NotNull
    private final Currency currency = null;
    @NotEmpty
    private final Map<Locale, String> localizedDescriptions = null;
    @NotNull
    private final Cuisine cuisine = null;
    @NotEmpty
    private final boolean[] openingDays = null;
    @NotEmpty
    private final LocalTime[] openingHours = null;
    @NotBlank
    private final String phoneNumber = null;
    @NotNull
    private final URI imageURI = null;
    @NotNull
    private Boolean hasImage = null;

    // Time-tracking fields
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private LocalDateTime deletionDate;

    /**
     * This field is a temporary field to store image properties during the saving process.
     * Do not expect the actual image to be present within this field.
     */
    private final Image image = null;

    /**
     * Creates a new restaurant using data from the registration form.
     *
     * @param registrationFormDTO the registration form
     * @param user the restaurant owner
     * @param persistenceIdGenerator persistence ID generator
     * @param messageSource a {@link MessageSource}
     * @return a new {@link CoreRestaurant}
     */
    public static CoreRestaurant createNewRestaurantFromRegistrationForm(Map<Object, Object> registrationFormDTO,
                                                                         CoreUser user,
                                                                         PersistenceIdGenerator persistenceIdGenerator,
                                                                         MessageSource messageSource) {
        Map<Object, Object> newRestaurantDTO = Maps.newHashMap();
        newRestaurantDTO.put("id", user.getRestaurantId());
        newRestaurantDTO.put("menuId", persistenceIdGenerator.generate());

        // populating the DTO from registration form
        newRestaurantDTO.put("name", registrationFormDTO.get("restaurantName"));

        Map<Object, Object> locationDTO = Maps.newHashMap();
        locationDTO.put("latitude", registrationFormDTO.get("latitude"));
        locationDTO.put("longitude", registrationFormDTO.get("longitude"));
        locationDTO.put("address", registrationFormDTO.get("restaurantAddress"));
        locationDTO.put("city", registrationFormDTO.get("restaurantCity"));
        locationDTO.put("postalCode", registrationFormDTO.get("restaurantPostalCode"));
        locationDTO.put("countryCode", registrationFormDTO.get("restaurantCountryCode"));
        locationDTO.put("formattedAddress", registrationFormDTO.get("formattedAddress"));
        newRestaurantDTO.put("location", locationDTO);

        String restaurantCountryCode = (String) registrationFormDTO.get("restaurantCountryCode");
        Locale firstLanguage = getLanguageFromCountryCode(restaurantCountryCode);
        LinkedHashSet<Locale> supportedLanguages = Sets.newLinkedHashSet();
        supportedLanguages.add(firstLanguage);
        newRestaurantDTO.put("supportedLanguages", supportedLanguages);
        newRestaurantDTO.put("currency", getCurrencyFromCountryCode(restaurantCountryCode));

        // populating the DTO with default values
        newRestaurantDTO.put("enabled", false);
        Map<Locale, String> localizedDescriptions = Maps.newLinkedHashMap();
        localizedDescriptions.put(firstLanguage,
                messageSource.getMessage(DEFAULT_LOCALIZED_DESCRIPTION_KEY, null, firstLanguage));
        newRestaurantDTO.put("localizedDescriptions", localizedDescriptions);
        newRestaurantDTO.put("cuisine", Cuisine.GLOBAL_INTERNATIONAL);
        newRestaurantDTO.put("openingDays", new boolean[14]);
        newRestaurantDTO.put("openingHours", new LocalTime[4]);
        newRestaurantDTO.put("phoneNumber", Countries.getCountries().get(restaurantCountryCode).getInternationalDialingCode());
        newRestaurantDTO.put("imageURI", getRelativeImageURI(user.getRestaurantId()));
        newRestaurantDTO.put("hasImage", Boolean.FALSE);

        return of(newRestaurantDTO);
    }

    /**
     * Merge this restaurant with the given DTO.
     *
     * @param restaurantDTO the DTO to merge with
     * @return a new instance of {@link CoreRestaurant}
     */
    public CoreRestaurant merge(Map<Object, Object> restaurantDTO) {
        // TODO we don't verify anything (related to currencies, countries, languages, ...) here because
        // everything will be verified by JSR-303 in the future
        // So just re-validating the restaurant by re-instantiating it seems good enough
        // TODO apply same strategy for other domain object eg user
        Map<Object, Object> mergedRestaurantDTO = toMap();
        mergedRestaurantDTO.putAll(restaurantDTO);
        return of(mergedRestaurantDTO);
    }

    private static Locale getLanguageFromCountryCode(String countryCode) {
        switch (countryCode) {
            case "FR" :
                return Locale.FRENCH;
            default:
                return Locale.ENGLISH;
        }
    }

    private static Currency getCurrencyFromCountryCode(String countryCode) {
        Locale tempLocale = Locale.forLanguageTag("en-" + countryCode);
        Currency currency = Currency.getInstance(tempLocale);
        if(currency == null) {
            currency = Currency.getInstance("USD");
        }
        return currency;
    }

    /**
     * Add support to the given language.
     *
     * @param language the new language to support
     * @param menu the linked menu
     * @param messageSource messageSource
     */
    public void addLanguageSupport(Locale language, CoreMenu menu, MessageSource messageSource) {
        if(supportedLanguages.contains(language)) {
            throw new IllegalArgumentException("Cannot support an already supported language : " + language);
        }
        if(!Languages.LANGUAGES.contains(language)) {
            throw new IllegalArgumentException("Cannot add support to language " + language + " (language not available)");
        }

        supportedLanguages.add(language);
        localizedDescriptions.put(language, messageSource.getMessage(DEFAULT_LOCALIZED_DESCRIPTION_KEY, null, language));

        // add new language support to the menu as well
        menu.addLanguageSupport(language, messageSource);
    }

    /**
     * Remove support of the given language.
     *
     * @param language the language to remove the support of
     * @param menu the linked menu
     */
    public void removeLanguageSupport(Locale language, CoreMenu menu) {
        if(supportedLanguages.size() == 1) {
            throw new IllegalArgumentException("Cannot remove the only supported language");
        }
        if(!supportedLanguages.contains(language)) {
            throw new IllegalArgumentException("Cannot remove support of language " + language + " (language not supported)");
        }

        supportedLanguages.remove(language);
        localizedDescriptions.remove(language);

        // remove language support to the menu as well
        menu.removeLanguageSupport(language);
    }

    private static URI getRelativeImageURI(String restaurantId) {
        return URI.create("restaurants/" + restaurantId + "/image.jpeg");
    }

    /**
     * Uploads the image of this restaurant if any was given during the instantiation of this object.
     *
     * @param uploadCoreService an {@link UploadCoreService}
     * @return {@code true} if the restaurant needs to be updated afterwards, {@code false} otherwise
     */
    public boolean uploadImageIfAny(UploadCoreService uploadCoreService) {
        if (image == null || image.isEmpty()) {
            return false;
        }
        try {
            uploadCoreService.upload(image, imageURI);
            hasImage = Boolean.TRUE;
        } catch (UploadException e) {
            // Since an image upload is not a blocking matter, we'll just log an error for further investigation.
            logger.error("Restaurant image upload failed with the following exception (restaurant id : " + id + ")", e);
            hasImage = Boolean.FALSE;
        }
        return true;
    }

    /**
     * Deletes the image of this restaurant, if any.
     *
     * @param uploadCoreService an {@link UploadCoreService}
     * @return {@code true} if the restaurant needs to be updated afterwards, {@code false} otherwise
     */
    public boolean deleteImageIfAny(UploadCoreService uploadCoreService) {
        if (!hasImage) {
            return false;
        }
        try {
            uploadCoreService.delete(imageURI);
        } catch (UploadException e) {
            // Since an image deletion is not a blocking matter, we'll just log an error for further investigation.
            logger.error("Restaurant image deletion failed with the following exception (restaurant id : " + id + ")", e);
        }
        hasImage = Boolean.FALSE;
        return true;
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
     * @param restaurantDTO a {@link Map} of properties
     * @return a {@link CoreRestaurant}
     */
    public static CoreRestaurant of(Map<Object, Object> restaurantDTO) {
        return CoreDomainModelMapper.fromMap(restaurantDTO, CoreRestaurant.class);
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
        if(obj instanceof CoreRestaurant) {
            CoreRestaurant restaurant = (CoreRestaurant) obj;
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

    public String getId() {
        return id;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public CoreLocation getLocation() {
        return location;
    }

    public Set<Locale> getSupportedLanguages() {
        return Collections.unmodifiableSet(supportedLanguages);
    }

    public Currency getCurrency() {
        return currency;
    }

    public Map<Locale, String> getLocalizedDescriptions() {
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

    public URI getImageURI() {
        return imageURI;
    }

    public Boolean getHasImage() {
        return hasImage;
    }

    public Image getImage() {
        return image;
    }
}
