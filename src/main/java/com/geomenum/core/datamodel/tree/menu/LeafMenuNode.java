/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.datamodel.tree.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.core.services.system.upload.UploadCoreService;

import java.util.Currency;
import java.util.Map;

public class LeafMenuNode extends AbstractMenuNode implements MenuNode {

    public LeafMenuNode(Map<Object, Object> dto) {
        this(CoreMenuItem.of((Map<Object, Object>) dto.get("menuNodeContent")));
    }

    public LeafMenuNode(CoreMenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public boolean allowsChildren() {
        return false;
    }

    @Override
    public void changeCurrency(Currency currency) {
        CoreMenuItem menuItem = (CoreMenuItem) getContent();
        menuItem.setCurrency(currency);
    }

    @Override
    public void deleteImageIfAny(UploadCoreService uploadCoreService) {
        CoreMenuItem menuItem = (CoreMenuItem) getContent();
        menuItem.deleteImageIfAny(uploadCoreService);
    }
}
