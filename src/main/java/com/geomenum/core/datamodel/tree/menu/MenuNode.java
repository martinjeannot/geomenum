/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.datamodel.tree.menu;

import com.geomenum.common.datamodel.tree.generic.Node;
import com.geomenum.common.integration.Mappable;
import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.geomenum.core.services.system.upload.UploadCoreService;
import org.springframework.context.MessageSource;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * {@link com.geomenum.core.domainmodel.menu.CoreMenu} related {@link Node} interface.
 */
public interface MenuNode extends Node<MenuNodeContent>, Mappable {

    List<MenuNode> getChildrenAsMenuNode();

    MenuNode getParentAsMenuNode();

    MenuNode findNodeById(UUID id);

    MenuNodeContent findContentById(UUID id);

    CoreMenuItem findMenuItemById(UUID id);

    CoreSubmenu findSubmenuById(UUID id);

    List<MenuNode> computeNodePathById(UUID id);

    /**
     * Add support to the given language.
     *
     * @param language the new language to support
     * @param messageSource messageSource
     */
    void addLanguageSupport(Locale language, MessageSource messageSource);

    /**
     * Remove support of the given language.
     *
     * @param language the language to remove the support of
     */
    void removeLanguageSupport(Locale language);

    void changeCurrency(Currency currency);

    void deleteImageIfAny(UploadCoreService uploadCoreService);
}
