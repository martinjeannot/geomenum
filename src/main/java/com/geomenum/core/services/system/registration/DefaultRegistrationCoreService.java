/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.registration;

import com.geomenum.common.validation.constraintvalidators.PersistenceIdValidator;
import com.geomenum.core.domainmodel.CoreDomainModelFactory;
import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.domainmodel.system.Email;
import com.geomenum.core.services.menu.MenuCoreService;
import com.geomenum.core.services.restaurant.RestaurantCoreService;
import com.geomenum.core.services.system.UserCoreService;
import com.geomenum.core.services.system.mail.EmailingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * {@link RegistrationCoreService} default implementation.
 */
@Service
public class DefaultRegistrationCoreService implements RegistrationCoreService {

    @Autowired
    private CoreDomainModelFactory domainModelFactory;

    @Autowired
    private UserCoreService userCoreService;

    @Autowired
    private RestaurantCoreService restaurantCoreService;

    @Autowired
    private MenuCoreService menuCoreService;

    @Autowired
    private EmailingCoreService emailingCoreService;

    protected static final PersistenceIdValidator PERSISTENCE_ID_VALIDATOR = new PersistenceIdValidator();

    @Override
    public void launchRegistrationProcess(Map<Object, Object> registrationForm) {
        // check if new user is not an existing user
        if(userCoreService.findUserByUsername((String) registrationForm.get("username")) != null) {
            throw new IllegalArgumentException("Cannot register an existing user with username : "
                    + registrationForm.get("username"));
        }

        // check if new restaurant is not an existing restaurant
        if(restaurantCoreService.findByFormattedAddress((String) registrationForm.get("formattedAddress")) != null) {
            throw new IllegalArgumentException("Cannot register an existing restaurant with formatted address : "
                    + registrationForm.get("formattedAddress"));
        }

        // create new user
        CoreUser newUser = domainModelFactory.createNewUserFromRegistrationForm(registrationForm);

        // create a new restaurant
        CoreRestaurant newRestaurant = domainModelFactory.createNewRestaurantFromRegistrationForm(registrationForm, newUser);

        // create a new menu
        CoreMenu newMenu = domainModelFactory.createNewMenu(newRestaurant);

        // [DB TNX]
        userCoreService.create(newUser);
        restaurantCoreService.create(newRestaurant);
        menuCoreService.create(newMenu);
        // [/DB TNX]

        // send confirmation email
        Email confirmationMail = domainModelFactory.getRegistrationConfirmationMail(newUser);
        emailingCoreService.sendEmail(confirmationMail);
    }

    @Override
    public void confirmUserAccount(String userId) {
        if(!PERSISTENCE_ID_VALIDATOR.isValid(userId, null)) {
            throw new IllegalArgumentException("User confirmation aborted - Invalid user ID : " + userId);
        }
        CoreUser user = userCoreService.findById(userId);
        if(user != null) {
            CoreUser confirmedUser = user.confirmAccount();
            userCoreService.update(confirmedUser);
        } else {
            throw new IllegalArgumentException("User confirmation aborted - Cannot find user with ID : " + userId);
        }
    }
}
