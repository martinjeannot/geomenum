/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.datamodel.tree.generic;

import java.util.List;

/**
 * Any class implementing this Node interface enables itself to be part of a tree data structure.
 *
 * @param <T> the type of data contained by the node.
 */
public interface Node<T> {

    /**
     * Returns the content encapsulated by this node.
     *
     * @return an object of type <T>
     */
    T getContent();

    /**
     * Set the content encapsulated by this node.
     *
     * @param newContent the new data content to set
     */
    void setContent(T newContent);

    /**
     * Returns the parent node of this node.
     *
     * @return a {@link Node}
     */
    Node<T> getParent();

    /**
     * Returns the children of this node.
     *
     * @return an {@link com.google.common.collect.ImmutableList} of nodes.
     */
    List<? extends Node<T>> getChildren();

    /**
     * Returns true if the node has 1 or more children (= non-empty branch node),
     * false if it has no children (= empty branch node or leaf node).
     *
     * @see #allowsChildren()
     * @return true/false
     */
    boolean hasChildren();

    /**
     * Returns true if the node <b>can</b> have children, false otherwise.
     * To be use in conjunction with {@link #hasChildren()} to solve the "file vs. empty directory" problem.
     *
     * @see #hasChildren()
     * @return true/false
     */
    boolean allowsChildren();

    /**
     * Returns the number of children of this node.
     *
     * @return an int
     */
    int getChildCount();

    /**
     * Returns true of this node is the root of the tree, false otherwise.
     *
     * @return true/false
     */
    boolean isRoot();

    /**
     * Add the child node given as a parameter to the children of this node.
     *
     * @param child the child node
     * @return true if the child has been added, false otherwise
     */
    boolean addChild(Node<T> child);

    /**
     * Returns true if the node given as a parameter is a child of this node, false otherwise.
     *
     * @param child a potential child node
     * @return true/false
     */
    boolean hasChild(Node<T> child);


    /**
     * Removes the child given as a parameter to the children of this node.
     * The removed child also become an orphan.
     *
     * @param child the child node to remove
     * @return true if the operation succeeded, false otherwise
     */
    boolean removeChild(Node<T> child);

    /**
     * Replaces one child (oldChild) by another (newChild).
     * This operation is equivalent to adding the newChild and removing the
     * oldChild except that the order is preserved (i.e, the newChild takes the
     * exact same place previously occupied by the oldChild).
     *
     * @param newChild the new child node to replace oldChild
     * @param oldChild the old node to be replaced by newChild
     * @return true if the operation succeeded, false otherwise
     */
    boolean replaceChild(Node<T> newChild, Node<T> oldChild);

    /**
     * Since order matters, this method makes child1 and child2 exchange their place with each other.
     *
     * @param child1 a child node
     * @param child2 a child node
     * @return true if the operation succeeded, false otherwise
     */
    boolean swapChildren(Node<T> child1, Node<T> child2);

    /**
     * Since order matters, this method moves the child node given as a parameter 1 index closer
     * to the beginning of the list, <b>if possible</b>.
     *
     * @param child the child node to move up
     * @return true if the operation succeeded, false otherwise
     */
    boolean moveUpChild(Node<T> child);

    /**
     * Since order matters, this method moves the child node given as a parameter 1 index further
     * from the beginning of the list, <b>if possible</b>.
     *
     * @param child the child node to move up
     * @return true if the operation succeeded, false otherwise
     */
    boolean moveDownChild(Node<T> child);

    /**
     * Moves this node 1 depth-level closer to the root,
     * adding it to its grandparent's children.
     *
     * @return true if the operation succeeded, false otherwise
     */
    boolean moveToUpperLevel();

    /**
     * Moves this node 1 depth-level further from the root,
     * adding it to the children of the given sibling {@link Node}.
     *
     * @param sibling the sibling node, future parent of this node
     * @return true if the operation succeeded, false otherwise
     */
    boolean moveToLowerLevel(Node<T> sibling);
}
