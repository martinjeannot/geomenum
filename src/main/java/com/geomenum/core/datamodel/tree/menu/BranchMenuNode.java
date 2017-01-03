/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.datamodel.tree.menu;

import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.geomenum.core.services.system.upload.UploadCoreService;

import java.util.Currency;
import java.util.List;
import java.util.Map;

public class BranchMenuNode extends AbstractMenuNode implements MenuNode {

    public BranchMenuNode(Map<Object, Object> dto) {
        this(CoreSubmenu.of((Map<Object, Object>) dto.get("menuNodeContent")));
        for (Map<Object, Object> child : (List<Map<Object, Object>>) dto.get("children")) {
            if (child.containsKey("children")) {
                this.addChild(new BranchMenuNode(child));
            } else {
                this.addChild(new LeafMenuNode(child));
            }
        }
    }

    public BranchMenuNode(CoreSubmenu submenu) {
        super(submenu);
    }

    @Override
    public boolean allowsChildren() {
        return true;
    }

    @Override
    public void changeCurrency(Currency currency) {
        if (hasChildren()) {
            for (MenuNode child : getChildrenAsMenuNode()) {
                child.changeCurrency(currency);
            }
        }
    }

    @Override
    public void deleteImageIfAny(UploadCoreService uploadCoreService) {
        if (hasChildren()) {
            for (MenuNode child : getChildrenAsMenuNode()) {
                child.deleteImageIfAny(uploadCoreService);
            }
        }
    }
}
