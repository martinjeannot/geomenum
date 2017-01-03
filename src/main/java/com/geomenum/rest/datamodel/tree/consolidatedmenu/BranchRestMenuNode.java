/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.datamodel.tree.consolidatedmenu;

import com.geomenum.rest.domainmodel.menu.RestSubmenu;
import com.geomenum.rest.domainmodel.menu.SubmenuResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BranchRestMenuNode extends AbstractRestMenuNode {

    public BranchRestMenuNode(Map<Object, Object> dto) {
        this(RestSubmenu.of((Map<Object, Object>) dto.get("menuNodeContent")));
        for(Map<Object, Object> child : (List<Map<Object, Object>>) dto.get("children")) {
            if(child.containsKey("children")) {
                this.addChild(new BranchRestMenuNode(child));
            } else {
                this.addChild(new LeafRestMenuNode(child));
            }
        }
    }

    public BranchRestMenuNode(RestSubmenu submenu) {
        super(submenu);
    }

    @Override
    public boolean allowsChildren() {
        return true;
    }

    @Override
    public ResourceSupport getResource(Locale locale) {
        SubmenuResource submenuResource = new SubmenuResource((RestSubmenu) getContent(), locale);
        for(RestMenuNode childNode : getChildrenAsRestMenuNode()) {
            if(childNode.getContent().getEnabled()) {
                submenuResource.addChild(childNode.getResource(locale));
            }
        }
        return submenuResource;
    }
}
