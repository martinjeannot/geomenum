/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.system;

import com.geomenum.web.controllers.system.registration.SignUpWebController;
import org.springframework.context.MessageSource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * This class represents an email that can be sent by our application.
 */
public class Email {

    // Registration confirmation
    private static final String CONFIRMATION_MAIL_SUBJECT_KEY = "registration.confirmationMailSubject";
    private static final String CONFIRMATION_MAIL_BODY_KEY = "registration.confirmationMailText";
    // Account reactivation
    private static final String REACTIVATION_MAIL_SUBJECT_KEY ="user.reactivationMailSubject";
    private static final String REACTIVATION_MAIL_BODY_KEY ="user.reactivationMailText";

    private final CoreUser sender;
    private final CoreUser recipient;
    private String subject;
    private String body;

    public Email(CoreUser recipient, CoreUser sender) {
        this.recipient = recipient;
        this.sender = sender;
    }

    /**
     * Defaults the sender to our application.
     *
     * @param recipient the mail recipient
     */
    public Email(CoreUser recipient) {
        this(recipient, CoreUser.SYSTEM);
    }

    /**
     * Builds the registration confirmation mail to send upon user's registration.
     *
     * @param recipient the newly created user
     * @param messageSource a messageSource
     * @return the registration confirmation mail
     */
    public static Email getRegistrationConfirmationMail(CoreUser recipient, MessageSource messageSource) {
        Email confirmationMail = new Email(recipient);
        confirmationMail.setSubject(
                messageSource.getMessage(
                        CONFIRMATION_MAIL_SUBJECT_KEY,
                        null,
                        recipient.getLanguage()));
        confirmationMail.setBody(
                messageSource.getMessage(
                        CONFIRMATION_MAIL_BODY_KEY,
                        new Object[] {
                                recipient.getFirstName(),
                                recipient.getLastName(),
                                getAccountConfirmationLink(recipient.getId())},
                        recipient.getLanguage()));
        return confirmationMail;
    }

    public static Email getAccountReactivationMail(CoreUser recipient, MessageSource messageSource) {
        Email confirmationMail = new Email(recipient);
        confirmationMail.setSubject(
                messageSource.getMessage(
                        REACTIVATION_MAIL_SUBJECT_KEY,
                        null,
                        recipient.getLanguage()));
        confirmationMail.setBody(
                messageSource.getMessage(
                        REACTIVATION_MAIL_BODY_KEY,
                        new Object[] {
                                recipient.getFirstName(),
                                recipient.getLastName(),
                                getAccountConfirmationLink(recipient.getId())},
                        recipient.getLanguage()));
        return confirmationMail;
    }

    private static String getAccountConfirmationLink(String userId) {
        return linkTo(SignUpWebController.class).withSelfRel().getHref() + "?confirm=" + userId;
    }

    // GETTERS & SETTERS

    public CoreUser getSender() {
        return sender;
    }

    public CoreUser getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
