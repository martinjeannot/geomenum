/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.system.registration;

/**
 * Fixtures for {@link RegistrationForm}
 */
public class RegistrationFormFixtures {

    public static RegistrationForm standardRegistrationForm() {
        RegistrationForm registrationForm = new RegistrationForm();
        // User
        registrationForm.setFirstName("Lisa");
        registrationForm.setFirstName("Morris");
        registrationForm.setUsername("lisa.morris-9odyenii@yopmail.com");
        registrationForm.setPassword(new char[]{'p', 'a', 's', 's', 'w', '0', 'r', 'd'});
        // Restaurant
        registrationForm.setRestaurantName("Chez Morris");
        registrationForm.setRestaurantCountryCode("FR");
        registrationForm.setRestaurantAddress("29 rue du 8 mai 1945");
        registrationForm.setRestaurantPostalCode("92370");
        registrationForm.setRestaurantCity("Chaville");
        // hidden fields
        registrationForm.setLatitude(48.8064362);
        registrationForm.setLongitude(2.1890269000000444);
        registrationForm.setFormattedAddress("29 Rue du 8 Mai 1945, 92370 Chaville, France");

        return registrationForm;
    }
}
