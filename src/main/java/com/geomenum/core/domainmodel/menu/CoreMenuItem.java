/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.geomenum.common.Currencies;
import com.geomenum.core.datamodel.tree.menu.MenuNodeContent;
import com.geomenum.core.domainmodel.CoreDomainModelMapper;
import com.geomenum.core.domainmodel.system.Image;
import com.geomenum.core.services.system.upload.UploadCoreService;
import com.geomenum.core.services.system.upload.UploadException;
import com.google.common.collect.ImmutableMap;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * A MenuItem represents a "reference" on the Menu, an "order-able" item. It contains
 * all the details provided by the restaurateur.
 */
public class CoreMenuItem implements MenuNodeContent {

    private static final Logger logger = LoggerFactory.getLogger(CoreMenuItem.class);

    public static final String DEFAULT_LOCALIZED_NAME_KEY = "menuItem.defaultLocalizedName";
    public static final String DEFAULT_LOCALIZED_DESCRIPTION_KEY = "menuItem.defaultLocalizedDescription";

    @NotNull
    private final UUID id = null;
    @NotEmpty
    private final Map<Locale, String> localizedNames = null;
    @NotNull
    private final Boolean enabled = null;
    @NotNull
    private final BigDecimal amount = null;
    @NotNull
    private Currency currency;
    @NotEmpty
    private final Map<Locale, String> localizedDescriptions = null;
    @NotNull
    private final URI imageURI = null;
    @NotNull
    private Boolean hasImage = null;

    /**
     * This field is a temporary field to store image properties during the saving process.
     * Do not expect the actual image to be present within this field.
     */
    private final Image image = null;

    // private ctor
    private CoreMenuItem() {}

    /**
     * Creates a new menu item from the base data contained in the given new menu item DTO.
     *
     * @param newMenuItemDTO the base data
     * @param menu the menu to create the menu item for
     * @return a new {@link CoreMenuItem}
     */
    public static CoreMenuItem createNewMenuItem(Map<Object, Object> newMenuItemDTO, CoreMenu menu) {
        UUID newMenuItemId = UUID.randomUUID();
        newMenuItemDTO.put("id", newMenuItemId);

        newMenuItemDTO.put("imageURI", getRelativeImageURI(menu.getRestaurantId(), menu.getId(), newMenuItemId.toString()));
        newMenuItemDTO.put("hasImage", Boolean.FALSE);

        return of(newMenuItemDTO);
    }

    /**
     * Merge this menu item with the given DTO.
     *
     * @param menuItemDTO the DTO to merge with
     * @return a new instance of {@link CoreMenuItem}
     */
    public CoreMenuItem merge(Map<Object, Object> menuItemDTO) {
        Map<Object, Object> mergedMenuItemDTO = toMap();
        mergedMenuItemDTO.putAll(menuItemDTO);
        return of(mergedMenuItemDTO);
    }

    @Override
    public void addLanguageSupport(Locale language, MessageSource messageSource) {
        localizedNames.put(language,
                messageSource.getMessage(DEFAULT_LOCALIZED_NAME_KEY, null, language));
        localizedDescriptions.put(language,
                messageSource.getMessage(DEFAULT_LOCALIZED_DESCRIPTION_KEY, null, language));
    }

    @Override
    public void removeLanguageSupport(Locale language) {
        localizedNames.remove(language);
        localizedDescriptions.remove(language);
    }

    private static URI getRelativeImageURI(String restaurantId, String menuId, String menuItemId) {
        return URI.create("restaurants/" + restaurantId + "/menus/" + menuId + "/menuItems/" + menuItemId + "/image.jpeg");
    }

    /**
     * Uploads the image of this menu item if any was given during the instantiation of this object.
     *
     * @param uploadCoreService an {@link UploadCoreService}
     * @return {@code true} if the menu item needs to be updated afterwards, {@code false} otherwise
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
            logger.error("Menu item image upload failed with the following exception (menu item id : " + id + ")", e);
            hasImage = Boolean.FALSE;
        }
        return true;
    }

    /**
     * Deletes the image of this menu item, if any.
     *
     * @param uploadCoreService an {@link UploadCoreService}
     * @return {@code true} if the menu item needs to be updated afterwards, {@code false} otherwise
     */
    public boolean deleteImageIfAny(UploadCoreService uploadCoreService) {
        if (!hasImage) {
            return false;
        }
        try {
            uploadCoreService.delete(imageURI);
        } catch (UploadException e) {
            // Since an image deletion is not a blocking matter, we'll just log an error for further investigation.
            logger.error("Menu item image deletion failed with the following exception (menu item id : " + id + ")", e);
        }
        hasImage = Boolean.FALSE;
        return true;
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param menuItemDTO a {@link Map} of properties
     * @return a {@link CoreMenuItem}
     */
    public static CoreMenuItem of(Map<Object, Object> menuItemDTO) {
        return CoreDomainModelMapper.fromMap(menuItemDTO, CoreMenuItem.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        Map<Object, Object> dto = CoreDomainModelMapper.toMap(this);
        dto.remove("image");
        return dto;
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof CoreMenuItem) {
            CoreMenuItem menuItem = (CoreMenuItem) obj;
            if(this.id.equals(menuItem.id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "id : " + id + " / "
                + "localizedNames : " + localizedNames;
    }

    //~ Getters & Setters ==============================================================================================

    public UUID getId() {
        return id;
    }

    public Map<Locale, String> getLocalizedNames() {
        return ImmutableMap.copyOf(localizedNames);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        if(!Currencies.CURRENCIES.contains(currency)) {
            throw new IllegalArgumentException("Currency " + currency + " is not available");
        }
        this.currency = currency;
    }

    public Map<Locale, String> getLocalizedDescriptions() {
        return ImmutableMap.copyOf(localizedDescriptions);
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
