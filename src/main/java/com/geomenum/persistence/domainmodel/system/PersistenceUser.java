/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.persistence.domainmodel.system;

import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.NoDisposableEmail;
import com.geomenum.persistence.DBCollection;
import com.geomenum.persistence.domainmodel.PersistenceDomainModelMapper;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Persistence {@link com.geomenum.core.domainmodel.system.CoreUser}
 */
@Document(collection = DBCollection.USERS)
public class PersistenceUser implements Mappable {

    @Id
    @NotNull
    private final ObjectId id = null;
    @Indexed
    @NotNull
    private final ObjectId restaurantId = null;
    @NotBlank
    @NoDisposableEmail
    private final String username = null;
    @NotBlank
    private final String password = null;
    @NotEmpty
    private final List<String> authorities = null;
    @NotNull
    private final Boolean accountNonExpired = null;
    @NotNull
    private final Boolean accountNonLocked = null;
    @NotNull
    private final Boolean credentialsNonExpired = null;
    @NotNull
    private final Boolean enabled = null;
    @NotBlank
    private final String firstName = null;
    @NotBlank
    private final String lastName = null;
    @NotNull
    private final Locale language = null;

    // Time-tracking fields
    @NotNull
    private final LocalDateTime creationDate = null;
    private final LocalDateTime lastUpdateDate = null;
    private final LocalDateTime deletionDate = null;

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param userDTO a {@link Map} of properties
     * @return a {@link PersistenceUser}
     */
    public static PersistenceUser of(Map<Object, Object> userDTO) {
        return PersistenceDomainModelMapper.fromMap(userDTO, PersistenceUser.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        return PersistenceDomainModelMapper.toMap(this);
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof PersistenceUser) {
            PersistenceUser user = (PersistenceUser) obj;
            if(this.id.equals(user.id)
                    && this.restaurantId.equals(user.restaurantId)
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
        result = 31 * result + restaurantId.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "username : " + username + "/"
                + "id : " + id + "/"
                + "restaurantId : " + restaurantId + "/"
                + "firstName : " + firstName + "/"
                + "lastName : " + lastName;
    }

    //~ Getters & Setters ==============================================================================================

    public ObjectId getId() {
        return id;
    }

    public ObjectId getRestaurantId() {
        return restaurantId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Locale getLanguage() {
        return language;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public LocalDateTime getDeletionDate() {
        return deletionDate;
    }
}
