package com.geomenum.core.services.system.registration;

import java.util.Map;

/**
 * Service involved in the registration process.
 */
public interface RegistrationCoreService {

    /**
     * Launches a new registration process eventually leading to the creation of a new User, a new Restaurant,
     * a new Menu and issuing a confirmation mail.
     *
     * @param registrationForm the registration form data
     */
    void launchRegistrationProcess(Map<Object, Object> registrationForm);

    /**
     * Confirms a user account.
     *
     * @param userId the ID of the user to confirm
     */
    void confirmUserAccount(String userId);
}
