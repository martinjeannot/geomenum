/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.system;

import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.LightPassword;
import com.geomenum.common.validation.constraints.NoDisposableEmail;
import com.geomenum.common.validation.constraints.PersistenceIdNotBlank;
import com.geomenum.web.domainmodel.WebDomainModelMapper;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Map;

public class WebUser implements Mappable {

    @PersistenceIdNotBlank
    private final String id = null;
    // ACCOUNT
    @NotBlank
    @NoDisposableEmail
    private String username;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private Locale language;
    // PASSWORD
    @LightPassword
    private char[] oldPassword;
    @LightPassword
    private char[] newPassword;

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param userDTO a {@link Map} of properties
     * @return a {@link WebUser}
     */
    public static WebUser of(Map<Object, Object> userDTO) {
        return WebDomainModelMapper.fromMap(userDTO, WebUser.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        return WebDomainModelMapper.toMap(this);
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof WebUser) {
            WebUser user = (WebUser) obj;
            if(this.id.equals(user.id)
                    && this.username.equals(user.username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "username : " + username + "/"
                + "id : " + id + "/"
                + "firstName : " + firstName + "/"
                + "lastName : " + lastName;
    }

    //~ Getters & Setters ==============================================================================================

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public char[] getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(char[] oldPassword) {
        this.oldPassword = oldPassword;
    }

    public char[] getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(char[] newPassword) {
        this.newPassword = newPassword;
    }
}
