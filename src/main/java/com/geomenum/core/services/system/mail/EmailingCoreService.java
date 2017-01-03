package com.geomenum.core.services.system.mail;

import com.geomenum.core.domainmodel.system.Email;

/**
 * Emailing service.<br/>
 * Mainly used during the registration process at the moment but could be used for other tasks in the future.
 */
public interface EmailingCoreService {

    void sendEmail(Email email);
}
