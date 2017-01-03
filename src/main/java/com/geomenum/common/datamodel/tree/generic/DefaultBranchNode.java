/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.datamodel.tree.generic;

public class DefaultBranchNode<T> extends AbstractNode<T> {

    public DefaultBranchNode(T content) {
        super(content);
    }

    @Override
    public boolean allowsChildren() {
        return true;
    }
}
