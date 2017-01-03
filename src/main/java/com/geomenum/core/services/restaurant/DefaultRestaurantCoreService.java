/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.restaurant;

import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.common.integration.Mappable;
import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.core.services.DefaultGenericCoreService;
import com.geomenum.core.services.menu.MenuCoreService;
import com.geomenum.core.services.system.upload.UploadCoreService;
import com.geomenum.persistence.servicefacades.GenericPersistenceServiceFacade;
import com.geomenum.persistence.servicefacades.restaurant.RestaurantPersistenceServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * {@link RestaurantCoreService} default implementation.
 */
@Service
public class DefaultRestaurantCoreService extends DefaultGenericCoreService<CoreRestaurant> implements RestaurantCoreService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRestaurantCoreService.class);

    private final RestaurantPersistenceServiceFacade restaurantPersistenceService;

    private final MenuCoreService menuCoreService;

    private final MessageSource messageSource;

    private final UploadCoreService uploadCoreService;

    @Autowired
    public DefaultRestaurantCoreService(RestaurantPersistenceServiceFacade restaurantPersistenceService,
                                        MenuCoreService menuCoreService,
                                        MessageSource messageSource,
                                        UploadCoreService uploadCoreService) {
        this.restaurantPersistenceService = restaurantPersistenceService;
        this.menuCoreService = menuCoreService;
        this.messageSource = messageSource;
        this.uploadCoreService = uploadCoreService;
    }

    @Override
    public CoreRestaurant update(Map<Object, Object> restaurantDTO) {
        // original restaurant retrieval
        CoreRestaurant originalRestaurant = findById((String) restaurantDTO.get("id"));
        Objects.requireNonNull(originalRestaurant, "Cannot find restaurant with id : " + restaurantDTO.get("id"));

        CoreRestaurant restaurantToUpdate = originalRestaurant.merge(restaurantDTO);

        // location verification
        if (!originalRestaurant.getLocation().getFormattedAddress().equals(
                restaurantToUpdate.getLocation().getFormattedAddress())) {
            CoreRestaurant existingRestaurant = findByFormattedAddress(
                    restaurantToUpdate.getLocation().getFormattedAddress());
            if(existingRestaurant != null) {
                throw new IllegalArgumentException("Cannot update restaurant with id : " + restaurantToUpdate.getId()
                        + " (attempt to update to an already registered location : "
                        + restaurantToUpdate.getLocation().getFormattedAddress() + ")");
            }
        }

        // image upload
        restaurantToUpdate.uploadImageIfAny(uploadCoreService);

        // menu sync
        CoreMenu menu = menuCoreService.findById(restaurantToUpdate.getMenuId());
        if (menu.synchronizeWithRestaurant(restaurantToUpdate)) {
            menuCoreService.update(menu);
        }

        return update(restaurantToUpdate);
    }

    @Override
    public CoreRestaurant addLanguageSupport(String restaurantId, Locale language) {
        CoreRestaurant restaurant = findById(restaurantId);
        CoreMenu menu = menuCoreService.findById(restaurant.getMenuId());

        restaurant.addLanguageSupport(language, menu, messageSource);

        menuCoreService.update(menu);
        return update(restaurant);
    }

    @Override
    public CoreRestaurant removeLanguageSupport(String restaurantId, Locale language) {
        CoreRestaurant restaurant = findById(restaurantId);
        CoreMenu menu = menuCoreService.findById(restaurant.getMenuId());

        restaurant.removeLanguageSupport(language, menu);

        menuCoreService.update(menu);
        return update(restaurant);
    }

    @Override
    public CoreRestaurant findByFormattedAddress(String formattedAddress) {
        if (formattedAddress == null || formattedAddress.isEmpty()) {
            throw new IllegalArgumentException("Cannot find restaurant with null or empty formatted address");
        }
        return restaurantPersistenceService.findByFormattedAddress(formattedAddress);
    }

    @Override
    public List<Map<Object, Object>> findEnabledByAnyCriteria(double locationLatitude, double locationLongitude, double maxDistance,
                                                              Cuisine cuisine) {
        List<Map<Object, Object>> results = restaurantPersistenceService.findEnabledByAnyCriteria(
                locationLatitude, locationLongitude, maxDistance,
                cuisine);
        for (Map<Object, Object> result : results) {
            result.put("restaurantDTO", ((Mappable) result.get("restaurant")).toMap());
            result.remove("restaurant");
        }
        return results;
    }

    @Override
    protected CoreRestaurant cascadeDelete(String id) {
        Objects.requireNonNull(id, "Cannot delete a restaurant with null id");
        CoreRestaurant restaurant = findById(id);
        Objects.requireNonNull(restaurant, "Cannot find the restaurant to delete (id : " + id + ")");

        // delete additional resources
        restaurant.deleteImageIfAny(uploadCoreService);

        // cascade delete to menu
        menuCoreService.delete(restaurant.getMenuId(), true);

        return delete(id);
    }

    //~ DefaultGenericCoreService ======================================================================================

    @Override
    protected GenericPersistenceServiceFacade<CoreRestaurant> getPersistenceService() {
        return restaurantPersistenceService;
    }
}
