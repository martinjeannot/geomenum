/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.datamodel.tree.generic;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * This class provides a common {@link Node} implementation to all kinds of concrete nodes.
 */
public abstract class AbstractNode<T> implements Node<T> {

    private T content;
    private Node<T> parent;
    private final List<Node<T>> children;

    protected AbstractNode(T content) {
        this.content = content;
        if(allowsChildren()) {
            children = Lists.newArrayList();
        } else {
            children = null;
        }
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public void setContent(T newContent) {
        this.content = newContent;
    }

    @Override
    public Node<T> getParent() {
        return parent;
    }

    /**
     * Sets the node given as a parameter as the parent node of this node.
     * This method should be used with extreme caution, that's the reason why it is not exposed via the {@link Node} API.
     *
     * @param parent the parent node to set
     */
    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    @Override
    public List<? extends Node<T>> getChildren() {
        return ImmutableList.copyOf(children);
    }

    @Override
    public boolean isRoot() {
        return getParent() == null;
    }

    @Override
    public boolean hasChildren() {
        return getChildCount() != 0;
    }

    @Override
    public int getChildCount() {
        if(children == null) {
            return 0;
        } else {
            return children.size();
        }
    }

    @Override
    public boolean hasChild(Node<T> child) {
        Objects.requireNonNull(child, "Child cannot be null");
        if(child.getParent() == null) {
            return false;
        } else {
            return child.getParent().equals(this) && children.contains(child);
        }
    }

    @Override
    public boolean addChild(Node<T> child) {
        if(allowsChildren() && !hasChild(child)) {
            if(child instanceof AbstractNode) {
                // the only currently supported implementation is our own
                if(children.add(child)) {
                    if(child.getParent() != null
                            && !child.getParent().removeChild(child)) {
                        throw new IllegalStateException();
                    }
                    ((AbstractNode<T>) child).setParent(this);
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new UnsupportedOperationException();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean removeChild(Node<T> child) {
        if(hasChild(child)) {
            if(child instanceof AbstractNode) {
                // the only currently supported implementation is our own
                if(children.remove(child)) {
                    ((AbstractNode<T>) child).setParent(null);
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new UnsupportedOperationException();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean replaceChild(Node<T> newChild, Node<T> oldChild) {
        if(!hasChild(newChild) && hasChild(oldChild)) {
            if(newChild instanceof AbstractNode && oldChild instanceof AbstractNode) {
                // the only currently supported implementation is our own
                Node<T> oldElement = children.set(children.indexOf(oldChild), newChild);
                if(oldChild.equals(oldElement)) {
                    ((AbstractNode<T>) newChild).setParent(this);
                    ((AbstractNode<T>) oldChild).setParent(null);
                    return true;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw new UnsupportedOperationException();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean swapChildren(Node<T> child1, Node<T> child2) {
        if(hasChild(child1) && hasChild(child2) && !child1.equals(child2)) {
            if(children.indexOf(child1) < children.indexOf(child2)) {
                Node<T> oldChild2 = children.set(children.indexOf(child2), child1);
                if(child2.equals(oldChild2)) {
                    Node<T> oldChild1 = children.set(children.indexOf(child1), child2);
                    if(child1.equals(oldChild1)) {
                        return true;
                    } else {
                        throw new IllegalStateException();
                    }
                } else {
                    throw new IllegalStateException();
                }
            } else {
                Node<T> oldChild1 = children.set(children.indexOf(child1), child2);
                if(child1.equals(oldChild1)) {
                    Node<T> oldChild2 = children.set(children.indexOf(child2), child1);
                    if(child2.equals(oldChild2)) {
                        return true;
                    } else {
                        throw new IllegalStateException();
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean moveUpChild(Node<T> child) {
        if(hasChild(child) && children.indexOf(child) > 0) {
            Node<T> upperChild = children.get(children.indexOf(child) - 1);
            return swapChildren(child, upperChild);
        } else {
            return false;
        }
    }

    @Override
    public boolean moveDownChild(Node<T> child) {
        if(hasChild(child) && children.indexOf(child) < getChildCount() - 1) {
            Node<T> lowerChild = children.get(children.indexOf(child) + 1);
            return swapChildren(child, lowerChild);
        } else {
            return false;
        }
    }

    @Override
    public boolean moveToUpperLevel() {
        if(!isRoot() && !getParent().isRoot()) {
            return getParent().getParent().addChild(this);
        } else {
            return false;
        }
    }

    @Override
    public boolean moveToLowerLevel(Node<T> sibling) {
        Objects.requireNonNull(sibling, "Sibling cannot be null");
        if(!isRoot()
                && sibling.allowsChildren()
                && sibling.getParent().hasChild(this)) {
            return sibling.addChild(this);
        } else {
            return false;
        }
    }
}
