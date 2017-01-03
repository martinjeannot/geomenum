/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.domainmodel.menu;

import com.google.common.collect.Lists;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Locale;

/**
 * {@link RestSubmenu} resource.
 */
public class SubmenuResource extends ResourceSupport {

    private final String name;

    private final List<? super ResourceSupport> children = Lists.newArrayList();

    public SubmenuResource(RestSubmenu submenu, Locale locale) {
        Locale language = Locale.forLanguageTag(locale.getLanguage());

        name = submenu.getLocalizedNames().get(language);
    }

    public <T extends ResourceSupport> void addChild(T resource) {
        children.add(resource);
    }

    //~ Getters & Setters ==============================================================================================


    public String getName() {
        return name;
    }

    public List<? super ResourceSupport> getChildren() {
        return children;
    }
}
