/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.domainmodel.system.Email;
import com.geomenum.persistence.services.util.PersistenceIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CoreDomainModelFactory {

    @Autowired
    private PersistenceIdGenerator persistenceIdGenerator;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CoreUser createNewUserFromRegistrationForm(Map<Object, Object> registrationFormDTO) {
        return CoreUser.createNewUserFromRegistrationForm(registrationFormDTO, persistenceIdGenerator, passwordEncoder);
    }

    public CoreRestaurant createNewRestaurantFromRegistrationForm(Map<Object, Object> registrationFormDTO, CoreUser user) {
        return CoreRestaurant.createNewRestaurantFromRegistrationForm(registrationFormDTO, user, persistenceIdGenerator,
                messageSource);
    }

    public CoreMenu createNewMenu(CoreRestaurant restaurant) {
        return CoreMenu.createNewMenu(restaurant, messageSource);
    }

    public Email getRegistrationConfirmationMail(CoreUser recipient) {
        return Email.getRegistrationConfirmationMail(recipient, messageSource);
    }

    public Email getAccountReactivationMail(CoreUser recipient) {
        return Email.getAccountReactivationMail(recipient, messageSource);
    }
}
