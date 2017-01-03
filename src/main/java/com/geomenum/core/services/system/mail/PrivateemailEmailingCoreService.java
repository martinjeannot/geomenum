/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.mail;

import com.geomenum.config.profiles.Production;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * {@link EmailingCoreService} implementation using Namecheap's private email host.
 */
@Service
@Production
public class PrivateemailEmailingCoreService extends AbstractEmailingCoreService {

    private static final Properties PRIVATEEMAIL_PROPS = new Properties();

    public PrivateemailEmailingCoreService() {
        PRIVATEEMAIL_PROPS.setProperty("mail.smtp.auth", "true");
        PRIVATEEMAIL_PROPS.setProperty("mail.smtp.starttls.enable", "true");
        PRIVATEEMAIL_PROPS.setProperty("mail.smtp.host", "mail.privateemail.com");
        PRIVATEEMAIL_PROPS.setProperty("mail.smtp.port", "25");
    }

    @Override
    protected Properties getHostProps() {
        return PRIVATEEMAIL_PROPS;
    }
}
