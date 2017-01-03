/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNodeContent;
import com.geomenum.core.domainmodel.CoreDomainModelMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.MessageSource;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * A Submenu represents any "intermediate" level in a Menu. It is not "orderable", it's more a subset of a Menu.
 */
public class CoreSubmenu implements MenuNodeContent {

    public static final String DEFAULT_LOCALIZED_NAME_KEY = "submenu.defaultLocalizedName";
    private static final String ROOT_NAME_KEY = "submenu.rootName";

    @NotNull
    private final UUID id = null;
    @NotEmpty
    private final LinkedHashMap<Locale, String> localizedNames = null;
    @NotNull
    private final Boolean enabled = null;

    // private ctor
    private CoreSubmenu() {}

    /**
     * Creates a new submenu from the base data contained in the given new submenu DTO.
     *
     * @param newSubmenuDTO the base data
     * @return a new {@link CoreSubmenu}
     */
    public static CoreSubmenu createNewSubmenu(Map<Object, Object> newSubmenuDTO) {
        newSubmenuDTO.put("id", UUID.randomUUID());

        return of(newSubmenuDTO);
    }

    /**
     * Creates a new submenu filled with default values meant to be used as a menu root.
     *
     * @param supportedLanguages collection of supported languages
     * @param messageSource a {@link MessageSource}
     * @return a new {@link CoreSubmenu}
     */
    public static CoreSubmenu createNewSubmenuAsRoot(Collection<Locale> supportedLanguages,
                                                     MessageSource messageSource) {
        Map<Object, Object> newRootSubmenuDTO = Maps.newHashMap();
        newRootSubmenuDTO.put("id", UUID.randomUUID());

        LinkedHashMap<Locale, String> localizedNames = Maps.newLinkedHashMap();
        for(Locale language : supportedLanguages) {
            localizedNames.put(language,
                    messageSource.getMessage(ROOT_NAME_KEY, null, language));
        }
        newRootSubmenuDTO.put("localizedNames", localizedNames);

        newRootSubmenuDTO.put("enabled", true);

        return of(newRootSubmenuDTO);
    }

    /**
     * Merge this submenu with the given DTO.
     *
     * @param submenuDTO the DTO to merge with
     * @return a new instance of {@link CoreSubmenu}
     */
    public CoreSubmenu merge(Map<Object, Object> submenuDTO) {
        Map<Object, Object> mergedSubmenuDTO = toMap();
        mergedSubmenuDTO.putAll(submenuDTO);
        return of(mergedSubmenuDTO);
    }

    @Override
    public void addLanguageSupport(Locale language, MessageSource messageSource) {
        localizedNames.put(language,
                messageSource.getMessage(DEFAULT_LOCALIZED_NAME_KEY, null, language));
    }

    @Override
    public void removeLanguageSupport(Locale language) {
        localizedNames.remove(language);
    }

    //~ Mappable =======================================================================================================

    /**
     * Static factory.
     *
     * @param submenuDTO a {@link Map} of properties
     * @return a {@link CoreSubmenu}
     */
    public static CoreSubmenu of(Map<Object, Object> submenuDTO) {
        return CoreDomainModelMapper.fromMap(submenuDTO, CoreSubmenu.class);
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
        if(obj instanceof CoreSubmenu) {
            CoreSubmenu submenu = (CoreSubmenu) obj;
            if(this.id.equals(submenu.id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "] "
                + "id : " + id + " / "
                + "localizedNames : " + localizedNames;
    }

    //~ Getters & Setters ==============================================================================================

    public UUID getId() {
        return id;
    }

    public Map<Locale, String> getLocalizedNames() {
        return ImmutableMap.copyOf(localizedNames);
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
