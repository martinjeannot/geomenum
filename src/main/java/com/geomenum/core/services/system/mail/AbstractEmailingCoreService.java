/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.system.mail;

import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.domainmodel.system.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

abstract class AbstractEmailingCoreService implements EmailingCoreService {

    protected abstract Properties getHostProps();

    @Override
    public void sendEmail(Email email) {
        if(CoreUser.SYSTEM.equals(email.getSender())) {
            Session session = Session.getInstance(getHostProps(),
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(CoreUser.SYSTEM.getUsername(), CoreUser.SYSTEM.getPassword());
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(CoreUser.SYSTEM.getUsername()));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient().getUsername()));
                message.setSubject(email.getSubject());
                message.setText(email.getBody());

                Transport.send(message);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new UnsupportedOperationException("Cannot send a mail as another user than SYSTEM at the moment");
        }
    }
}
