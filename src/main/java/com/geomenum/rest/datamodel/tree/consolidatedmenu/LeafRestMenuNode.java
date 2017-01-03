/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.datamodel.tree.consolidatedmenu;

import com.geomenum.rest.domainmodel.menu.MenuItemResource;
import com.geomenum.rest.domainmodel.menu.RestMenuItem;
import org.springframework.hateoas.ResourceSupport;

import java.util.Locale;
import java.util.Map;

public class LeafRestMenuNode extends AbstractRestMenuNode {

    public LeafRestMenuNode(Map<Object, Object> dto) {
        this(RestMenuItem.of((Map<Object, Object>) dto.get("menuNodeContent")));
    }

    public LeafRestMenuNode(RestMenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public boolean allowsChildren() {
        return false;
    }

    @Override
    public ResourceSupport getResource(Locale locale) {
        return new MenuItemResource((RestMenuItem) getContent(), locale);
    }
}
