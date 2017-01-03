/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geomenum.common.Languages;
import com.geomenum.common.integration.Mappable;
import com.geomenum.common.validation.constraints.PersistenceIdNotBlank;
import com.geomenum.common.validation.constraintvalidators.LightPasswordValidator;
import com.geomenum.config.initializers.DefaultWebApplicationInitializer;
import com.geomenum.config.profiles.Development;
import com.geomenum.config.profiles.Production;
import com.geomenum.core.domainmodel.CoreDomainModelMapper;
import com.geomenum.core.domainmodel.bi.TimeTrackable;
import com.geomenum.core.security.crypto.CustomPasswordEncoder;
import com.geomenum.persistence.services.util.PersistenceIdGenerator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A User represents any entity being able to log in into the platform.
 */
public class CoreUser extends User implements Mappable, TimeTrackable {

    private static final long serialVersionUID = 9144507084817353840L;

    @PersistenceIdNotBlank
    private final String id = null;
    @PersistenceIdNotBlank
    private final String restaurantId = null;
    @NotBlank
    private final String firstName = null;
    @NotBlank
    private final String lastName = null;
    @NotNull
    private final Locale language = null;

    // Time-tracking fields
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private LocalDateTime deletionDate;

    // SYSTEM user
    static {
        final Map<Object, Object> systemDTO;
        systemDTO = Maps.newHashMap();
        List<String> systemUserCredentials;
        try {
            // todo refactor this thing according to profile set by annotation, such manual processing is error-prone
            String systemUserCredentialsPath = "security/";

            switch (DefaultWebApplicationInitializer.getCurrentProfile()) {
                case Development.PROFILE_NAME:
                    systemUserCredentialsPath = systemUserCredentialsPath + "gmailSystemUserCredentials.txt";
                    break;
                case Production.PROFILE_NAME:
                    systemUserCredentialsPath = systemUserCredentialsPath + "privateemailSystemUserCredentials.txt";
                    break;
                default: throw new IllegalStateException("Could not recognized current profile, if any.");
            }
            systemUserCredentials = Files.readAllLines(
                    Paths.get(CoreUser.class.getClassLoader().getResource(systemUserCredentialsPath).getPath()),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw new Error("[FATAL ERROR] CoreUser.SYSTEM INITIALIZATION FAILED !", e); // abort mission !
        }
        systemDTO.put("username", systemUserCredentials.get(0));
        systemDTO.put("password", systemUserCredentials.get(1));
        systemDTO.put("enabled", "true");
        systemDTO.put("accountNonExpired", "true");
        systemDTO.put("credentialsNonExpired", "true");
        systemDTO.put("accountNonLocked", "true");
        systemDTO.put("authorities", Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        systemDTO.put("id", "UT33f4kqllTyP6941W-wtN4RDlHhiC3FSO_Zdo9VG_E");
        systemDTO.put("restaurantId", "ZmwByn39mWJrmMK0WWxpfptjvtg6KiKoYyLLbdjo3FQ");
        systemDTO.put("firstName", "Admin");
        systemDTO.put("lastName", "SYSTEM");
        systemDTO.put("language", Locale.ENGLISH);
        SYSTEM = CoreUser.of(systemDTO);
    }
    public static final CoreUser SYSTEM;

    protected static final LightPasswordValidator lightPasswordValidator = new LightPasswordValidator();

    // private ctor
    @SuppressWarnings("unused")
    private CoreUser(@JsonProperty("username") String username,
                     @JsonProperty("password") String password,
                     @JsonProperty("enabled") Boolean enabled,
                     @JsonProperty("accountNonExpired") Boolean accountNonExpired,
                     @JsonProperty("credentialsNonExpired") Boolean credentialsNonExpired,
                     @JsonProperty("accountNonLocked") Boolean accountNonLocked,
                     @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities) {
        super(username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
    }

    /**
     * Creates a new user using data from the registration form.
     *
     * @param registrationFormDTO the registration form
     * @param persistenceIdGenerator persistence ID generator
     * @return a new {@link CoreUser}
     */
    public static CoreUser createNewUserFromRegistrationForm(Map<Object, Object> registrationFormDTO,
                                                             PersistenceIdGenerator persistenceIdGenerator,
                                                             PasswordEncoder passwordEncoder) {
        Map<Object, Object> newUserDTO = Maps.newHashMap();
        newUserDTO.put("id", persistenceIdGenerator.generate());
        newUserDTO.put("restaurantId", persistenceIdGenerator.generate());

        // populating the DTO from registration form
        newUserDTO.put("username", registrationFormDTO.get("username"));
        newUserDTO.put("firstName", registrationFormDTO.get("firstName"));
        newUserDTO.put("lastName", registrationFormDTO.get("lastName"));
        if(Languages.SUPPORTED_LANGUAGES.contains(LocaleContextHolder.getLocale())) {
            newUserDTO.put("language", LocaleContextHolder.getLocale());
        } else {
            newUserDTO.put("language", Locale.ENGLISH);
        }

        // password encryption
        newUserDTO.put("password", passwordEncoder.encode((CharSequence) registrationFormDTO.get("password")));

        // populating the DTO from default values
        newUserDTO.put("enabled", false); // account will be enabled on mail confirmation
        newUserDTO.put("accountNonExpired", true);
        newUserDTO.put("credentialsNonExpired", true);
        newUserDTO.put("accountNonLocked", true);
        newUserDTO.put("authorities", Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER")));

        return of(newUserDTO);
    }

    /**
     * Confirms this user's account.<br/>
     * As the {@code enabled} property is {@code final} we have to build a copy of {@code this} user and return
     * the modified copy to be updated.
     *
     * @return the user with his account enabled
     */
    public CoreUser confirmAccount() {
        if(isEnabled()) {
            // account already confirmed
            return this;
        }
        Map<Object, Object> userDTO = this.toMap();
        userDTO.put("enabled", true); // enable account

        return of(userDTO);
    }

    /**
     * Merges this user with the given DTO.
     *
     * @param userDTO the DTO to merge with
     * @param passwordEncoder a {@link CustomPasswordEncoder}
     * @return a new instance of {@link CoreUser}
     */
    public CoreUser merge(Map<Object, Object> userDTO, CustomPasswordEncoder passwordEncoder) {
        // username
        if(!getUsername().equals(userDTO.get("username"))) {
            userDTO.put("enabled", false); // disable account
        }

        // password
        // FIXME : Jackson serializing char array as String
        char[] oldPassword = ((String) userDTO.get("oldPassword")).toCharArray();
        char[] newPassword = ((String) userDTO.get("newPassword")).toCharArray();
        if(oldPassword != null && oldPassword.length != 0 && lightPasswordValidator.isValid(oldPassword, null)
                && newPassword != null && newPassword.length != 0 && lightPasswordValidator.isValid(newPassword, null)) {
            if(passwordEncoder.matches(oldPassword, getPassword())) {
                userDTO.put("password", passwordEncoder.encode(newPassword));
            } else {
                throw new IllegalArgumentException("Trying to change password with wrong password");
            }
        }

        // language
        Locale language = Locale.forLanguageTag((String) userDTO.get("language"));
        if(!Languages.SUPPORTED_LANGUAGES.contains(language)) {
            userDTO.remove("language");
        }

        Map<Object, Object> mergedUserDTO = toMap();
        mergedUserDTO.putAll(userDTO);
        return of(mergedUserDTO);
    }

    //~ TimeTrackable ==================================================================================================

    @Override
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public void setCreationDateToNow() {
        if(creationDate != null) {
            throw new IllegalStateException("Cannot reset the creation date");
        }
        setCreationDate(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    private void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public void setLastUpdateDateToNow() {
        setLastUpdateDate(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getDeletionDate() {
        return deletionDate;
    }

    private void setDeletionDate(LocalDateTime deletionDate) {
        this.deletionDate = deletionDate;
    }

    @Override
    public void setDeletionDateToNow() {
        if(deletionDate != null) {
            throw new IllegalStateException("Cannot reset the deletion date");
        }
        setDeletionDate(LocalDateTime.now());
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param userDTO a {@link Map} of properties
     * @return a {@link CoreUser}
     */
    public static CoreUser of(Map<Object, Object> userDTO) {
        return CoreDomainModelMapper.fromMap(userDTO, CoreUser.class);
    }

    @Override
    public Map<Object, Object> toMap() {
        return CoreDomainModelMapper.toMap(this);
    }

    //~ Object =========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof CoreUser) {
            CoreUser user = (CoreUser) obj;
            if(super.equals(user)
                    && this.id.equals(user.id)
                    && this.restaurantId.equals(user.restaurantId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + restaurantId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "id : " + id + "/"
                + "restaurantId : " + restaurantId + "/"
                + "firstName : " + firstName + "/"
                + "lastName : " + lastName;
    }

    //~ Getters & Setters ==============================================================================================

    public String getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
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
}
