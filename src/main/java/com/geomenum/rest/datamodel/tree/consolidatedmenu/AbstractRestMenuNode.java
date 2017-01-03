/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.rest.datamodel.tree.consolidatedmenu;

import com.geomenum.common.datamodel.tree.generic.AbstractNode;

import java.util.List;

/**
 * This class enriches the {@link AbstractNode} implementation with some {@link RestMenuNode}-specific functionality.
 */
public abstract class AbstractRestMenuNode extends AbstractNode<RestMenuNodeContent> implements RestMenuNode {

    protected AbstractRestMenuNode(RestMenuNodeContent content) {
        super(content);
    }

    @Override
    public List<RestMenuNode> getChildrenAsRestMenuNode() {
        return (List<RestMenuNode>) getChildren();
    }
}
