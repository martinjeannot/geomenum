/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system;

import com.geomenum.core.domainmodel.CoreDomainModelFactory;
import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.domainmodel.system.Email;
import com.geomenum.core.security.crypto.CustomPasswordEncoder;
import com.geomenum.core.services.DefaultGenericCoreService;
import com.geomenum.core.services.restaurant.RestaurantCoreService;
import com.geomenum.core.services.system.mail.EmailingCoreService;
import com.geomenum.persistence.servicefacades.GenericPersistenceServiceFacade;
import com.geomenum.persistence.servicefacades.system.UserPersistenceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * {@link UserCoreService} default implementation.
 */
@Service
public class DefaultUserCoreService extends DefaultGenericCoreService<CoreUser> implements UserCoreService {

    private final UserPersistenceServiceFacade userPersistenceService;

    private final RestaurantCoreService restaurantCoreService;

    private final EmailingCoreService emailingCoreService;

    private final CoreDomainModelFactory domainModelFactory;

    private final CustomPasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserCoreService(UserPersistenceServiceFacade userPersistenceService,
                                  RestaurantCoreService restaurantCoreService,
                                  EmailingCoreService emailingCoreService,
                                  CoreDomainModelFactory domainModelFactory,
                                  CustomPasswordEncoder passwordEncoder) {
        this.userPersistenceService = userPersistenceService;
        this.restaurantCoreService = restaurantCoreService;
        this.emailingCoreService = emailingCoreService;
        this.domainModelFactory = domainModelFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CoreUser findUserByUsername(String username) {
        if(username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Cannot find user with null or empty username");
        }
        return userPersistenceService.findByUsername(username);
    }

    @Override
    public CoreUser update(Map<Object, Object> userDTO) {
        CoreUser originalUser = findById((String) userDTO.get("id"));
        Objects.requireNonNull(originalUser, "Cannot find user with id : " + userDTO.get("id"));
        CoreUser updatedUser = originalUser.merge(userDTO, passwordEncoder);

        update(updatedUser);

        // account re-confirmation check
        if(!updatedUser.isEnabled()) {
            // send confirmation email
            Email accountReactivationMail = domainModelFactory.getAccountReactivationMail(updatedUser);
            emailingCoreService.sendEmail(accountReactivationMail);
        }

        return updatedUser;
    }

    @Override
    public void deleteUserAccount(String username) {
        CoreUser userToDelete = findUserByUsername(username);
        Objects.requireNonNull(userToDelete, "Cannot find the user to delete (username : " + username + ")");
        cascadeDelete(userToDelete.getId());
    }

    @Override
    protected CoreUser cascadeDelete(String id) {
        Objects.requireNonNull(id, "Cannot delete an user with null id");
        CoreUser user = findById(id);
        Objects.requireNonNull(user, "Cannot find the user to delete (id : " + id + ")");

        // cascade delete to restaurant
        restaurantCoreService.delete(user.getRestaurantId(), true);

        return delete(user.getId());
    }

    //~ DefaultGenericCoreService ======================================================================================

    @Override
    protected GenericPersistenceServiceFacade<CoreUser> getPersistenceService() {
        return userPersistenceService;
    }
}
