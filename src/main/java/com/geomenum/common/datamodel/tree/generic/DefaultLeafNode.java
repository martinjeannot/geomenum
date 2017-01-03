/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.datamodel.tree.generic;

public class DefaultLeafNode<T> extends AbstractNode<T> {

    public DefaultLeafNode(T content) {
        super(content);
    }

    @Override
    public boolean allowsChildren() {
        return false;
    }
}
