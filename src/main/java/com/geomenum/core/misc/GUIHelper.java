/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.misc;

import com.geomenum.core.datamodel.tree.menu.MenuNode;
import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.web.WebURLPath;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.*;

/**
 * This helper class has 2 main uses related to our "submenu" and "menu item" screens :
 * - build the breadcrumb navigation bar
 * - build the submenu dashboard
 */
public class GUIHelper {

    private final String menuId;
    private final MenuNode target;
    private final MenuNode root;
    private Locale language;

    private GUIHelper(MenuNode target, String menuId, Locale language, MenuNode root) {
        this.target = target;
        this.menuId = menuId;
        this.language = language;
        this.root = root;
    }

    public static GUIHelper guiHelper(MenuNode targetNode, String menuId, Locale language) {
        MenuNode root = targetNode;
        while(!root.isRoot()) {
            root = root.getParentAsMenuNode();
        }
        return new GUIHelper(targetNode, menuId, language, root);
    }

    public static GUIHelper guiHelper(UUID targetId, String menuId, Locale language, MenuNode root) {
        return new GUIHelper(root.findNodeById(targetId), menuId, language, root);
    }

    public static GUIHelper guiHelper(UUID targetId, CoreMenu menu) {
        Locale language;
        if(menu.getSupportedLanguages().contains(LocaleContextHolder.getLocale())) {
            language = LocaleContextHolder.getLocale();
        } else if(menu.getSupportedLanguages().contains(Locale.ENGLISH)) {
            language = Locale.ENGLISH;
        } else {
            language = menu.getSupportedLanguages().iterator().next();
        }
        return new GUIHelper(menu.getRoot().findNodeById(targetId), menu.getId(), language, menu.getRoot());
    }

    public List<Map<String, String>> buildBreadcrumbNavigationBar() {
        List<MenuNode> nodePath = root.computeNodePathById(target.getContent().getId());
        ImmutableList.Builder<Map<String, String>> navBar = ImmutableList.builder();
        for(MenuNode node : nodePath) {
            navBar.add(simpleLinkAsMap(node));
        }
        return navBar.build();
    }

    public List<Map<String, Object>> buildSubmenuDashboardInfo() {
        if(!target.allowsChildren()) {
            return null;
        }
        ImmutableList.Builder<Map<String, Object>> menuDashboardInfo = ImmutableList.builder();
        for(MenuNode child : target.getChildrenAsMenuNode()) {
            ImmutableMap.Builder<String, Object> childInfo = ImmutableMap.builder();
            childInfo.putAll(simpleLinkAsMap(child));
            childInfo.put("id", child.getContent().getId().toString());
            childInfo.put("allowsChildren", child.allowsChildren());
            childInfo.put("siblings", buildSiblings(child));
            menuDashboardInfo.add(childInfo.build());
        }
        return menuDashboardInfo.build();
    }

    private Map<String, String> simpleLinkAsMap(MenuNode node) {
        Map<String, String> link = Maps.newHashMapWithExpectedSize(2);
        link.put("url",
                node.allowsChildren() ? WebURLPath.getSubmenuURL(menuId, node.getContent().getId().toString())
                        : WebURLPath.getMenuItemURL(menuId, node.getContent().getId().toString()));
        if(node.isRoot()) {
            link.put("text", "Menu"); // todo to localize eventually
        } else {
            link.put("text", node.getContent().getLocalizedNames().get(language));
        }
        return link;
    }

    private List<Map<String, String>> buildSiblings(MenuNode target) {
        List<Map<String, String>> siblings =
                Lists.newArrayListWithCapacity(target.getParentAsMenuNode().getChildrenAsMenuNode().size());
        for(MenuNode sibling : target.getParentAsMenuNode().getChildrenAsMenuNode()) {
            if(!Objects.equals(target, sibling) && sibling.allowsChildren()) {
                siblings.add(ImmutableMap.of(
                        "id", sibling.getContent().getId().toString(),
                        "text", sibling.getContent().getLocalizedNames().get(language)));
            }
        }
        return siblings;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }
}
