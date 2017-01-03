/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import com.geomenum.common.integration.Mappable;
import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.geomenum.web.domainmodel.WebDomainModelMapper;
import com.google.common.collect.Maps;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.MessageSource;

import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to initiate the creation of a new submenu. It serves as a form backing-bean for our controllers.
 */
public class NewSubmenu implements Mappable {

    @NotEmpty
    private final Map<Locale, String> localizedNames;
    @NotNull
    private Boolean enabled;

    public NewSubmenu(Set<Locale> supportedLanguages, MessageSource messageSource) {
        localizedNames = Maps.newLinkedHashMap();
        for (Locale language : supportedLanguages) {
            localizedNames.put(language,
                    messageSource.getMessage(CoreSubmenu.DEFAULT_LOCALIZED_NAME_KEY, null, language));
        }
        enabled = true;
    }

    public NewSubmenu(Map<Locale, String> localizedNames, Boolean enabled) {
        this.localizedNames = localizedNames;
        this.enabled = enabled;
    }

    //~ Mappable =======================================================================================================

    @Override
    public Map<Object, Object> toMap() {
        return WebDomainModelMapper.toMap(this);
    }

    //~ Getters & Setters ==============================================================================================

    public Map<Locale, String> getLocalizedNames() {
        return localizedNames;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
