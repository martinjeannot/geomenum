/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.mail;

import com.geomenum.config.profiles.Development;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * {@link EmailingCoreService} implementation using Gmail service.
 */
@Service
@Development
public class GmailEmailingCoreService extends AbstractEmailingCoreService {

    private static final Properties GMAIL_PROPS = new Properties();

    public GmailEmailingCoreService() {
        GMAIL_PROPS.setProperty("mail.smtp.auth", "true");
        GMAIL_PROPS.setProperty("mail.smtp.starttls.enable", "true");
        GMAIL_PROPS.setProperty("mail.smtp.host", "smtp.gmail.com");
        GMAIL_PROPS.setProperty("mail.smtp.port", "587");
    }

    @Override
    protected Properties getHostProps() {
        return GMAIL_PROPS;
    }
}
