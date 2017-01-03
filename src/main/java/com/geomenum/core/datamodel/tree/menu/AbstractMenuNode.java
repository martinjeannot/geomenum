/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.datamodel.tree.menu;

import com.geomenum.common.datamodel.tree.generic.AbstractNode;
import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.context.MessageSource;

import java.util.*;

/**
 * This class enriches the {@link AbstractNode} implementation with some {@link MenuNode}-specific functionality.
 */
public abstract class AbstractMenuNode extends AbstractNode<MenuNodeContent> implements MenuNode {

    protected AbstractMenuNode(MenuNodeContent content) {
        super(content);
    }

    @Override
    public List<MenuNode> getChildrenAsMenuNode() {
        return (List<MenuNode>) getChildren();
    }

    @Override
    public MenuNode getParentAsMenuNode() {
        return (MenuNode) getParent();
    }

    @Override
    public Map<Object, Object> toMap() {
        if(allowsChildren()) {
            return ImmutableMap.<Object, Object>of(
                    "menuNodeContent", getContent().toMap(),
                    "children", buildMappableChildrenList());
        } else {
            return ImmutableMap.<Object, Object>of("menuNodeContent", getContent().toMap());
        }
    }

    private List<Map> buildMappableChildrenList() {
        ImmutableList.Builder<Map> builder = ImmutableList.builder();
        for(MenuNode child : getChildrenAsMenuNode()) {
            builder.add(child.toMap());
        }
        return builder.build();
    }

    @Override
    public MenuNode findNodeById(UUID id) {
        return findNodeById(id, this);
    }

    @Override
    public MenuNodeContent findContentById(UUID id) {
        return this.findNodeById(id, this).getContent();
    }

    @Override
    public CoreMenuItem findMenuItemById(UUID id) {
        MenuNodeContent menuItem = findContentById(id);
        return (CoreMenuItem) menuItem;
    }

    @Override
    public CoreSubmenu findSubmenuById(UUID id) {
        MenuNodeContent submenu = findContentById(id);
        return (CoreSubmenu) submenu;
    }

    @Override
    public List<MenuNode> computeNodePathById(UUID id) {
        return computeNodePathById(id, Lists.<MenuNode>newArrayList(this));
    }

    @Override
    public void addLanguageSupport(Locale language, MessageSource messageSource) {
        getContent().addLanguageSupport(language, messageSource);
        if(hasChildren()) {
            for(MenuNode child : getChildrenAsMenuNode()) {
                child.addLanguageSupport(language, messageSource);
            }
        }
    }

    @Override
    public void removeLanguageSupport(Locale language) {
        getContent().removeLanguageSupport(language);
        if(hasChildren()) {
            for(MenuNode child : getChildrenAsMenuNode()) {
                child.removeLanguageSupport(language);
            }
        }
    }

    /********************
     * WALKING THE TREE *
     ********************/

    private MenuNode findNodeById(UUID id, MenuNode currentNode) {
        MenuNode found = null;
        if(currentNode.getContent().getId().equals(id)) {
            found = currentNode;
        } else if(currentNode.allowsChildren()) {
            for(MenuNode child : currentNode.getChildrenAsMenuNode()) {
                found = findNodeById(id, child);
                if(found != null) {
                    break;
                }
            }
        }
        return found;
    }

    /**********************
     * COMPUTING THE PATH *
     **********************/

    private List<MenuNode> computeNodePathById(UUID id, List<MenuNode> currentNodePath) {
        List<MenuNode> computedPath = null;
        MenuNode currentNode = currentNodePath.get(currentNodePath.size() - 1);
        if(currentNode.getContent().getId().equals(id)) {
            computedPath = currentNodePath;
        } else if(currentNode.allowsChildren()) {
            for(MenuNode child : currentNode.getChildrenAsMenuNode()) {
                currentNodePath.add(child);
                computedPath = computeNodePathById(id, currentNodePath);
                if(computedPath != null) {
                    break;
                } else {
                    currentNodePath.remove(child);
                }
            }
        }
        return computedPath;
    }
}
