/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.datamodel.tree.generic;

import org.testng.annotations.Test;

import java.util.List;

import static com.geomenum.common.datamodel.tree.generic.NodeTestUtil.*;
import static com.geomenum.config.TestingLevels.UNIT;
import static org.testng.Assert.*;

@Test(groups = {UNIT})
public class NodeTest {

    /*******************
     *   NODE BASICS   *
     *******************/

    public void branchNodeCreation() {
        Node<Integer> branchNode = new DefaultBranchNode<>(0);

        assertBranchNodeWithContent(branchNode, 0, 0);
    }

    public void leafNodeCreation() {
        Node<Integer> rootNode = new DefaultBranchNode<>(0);
        Node<Integer> leafNode = new DefaultLeafNode<>(1);
        // This is mandatory because of the isRoot test. The root node of a tree is a branch node
        // and even in the case of an empty tree, the root node will remain a branch node and cannot
        // be both a branch and a leaf node at the same time.
        rootNode.addChild(leafNode);

        assertLeafNodeWithContent(leafNode, 1);
    }

    public void setContent() {
        Node<Integer> branchNode = new DefaultBranchNode<>(0);
        Node<Integer> leafNode = new DefaultLeafNode<>(1);
        branchNode.addChild(leafNode); // leafNode cannot be root
        branchNode.setContent(15);
        leafNode.setContent(30);

        assertBranchNodeWithContent(branchNode, 1, 15);
        assertLeafNodeWithContent(leafNode, 30);
    }

    /*****************
     *   ADD CHILD   *
     *****************/

    public void addChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode = new DefaultLeafNode<>(1);

        assertTrue(parentNode.addChild(childNode));
        assertBranchNodeWithContent(parentNode, 1, 0);
        assertLeafNodeWithContent(childNode, 1);
        assertParentChildRelation(parentNode, childNode);
    }

    public void addAlreadyAddedChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode = new DefaultLeafNode<>(1);

        assertTrue(parentNode.addChild(childNode));
        assertFalse(parentNode.addChild(childNode));
        assertBranchNodeWithContent(parentNode, 1, 0);
        assertParentChildRelation(parentNode, childNode);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Child cannot be null")
    public void addNullChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode = null;

        parentNode.addChild(childNode);
    }

    public void addChildToLeafNode() {
        Node<Integer> parentLeafNode = new DefaultLeafNode<>(0);
        Node<Integer> childNode = new DefaultLeafNode<>(1);

        assertFalse(parentLeafNode.addChild(childNode));
        assertNoParentChildRelation(parentLeafNode, childNode);
    }

    /********************
     *   REMOVE CHILD   *
     ********************/

    public void removeChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode);

        assertTrue(parentNode.removeChild(childNode));
        assertBranchNodeWithContent(parentNode, 0, 0);
        assertNull(childNode.getParent());
        assertNoParentChildRelation(parentNode, childNode);
    }

    public void removeAlreadyRemovedChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode);
        parentNode.removeChild(childNode);

        assertFalse(parentNode.removeChild(childNode));
        assertNoParentChildRelation(parentNode, childNode);
    }

    public void removeNonAddedChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode = new DefaultLeafNode<>(1);

        assertFalse(parentNode.removeChild(childNode));
        assertNoParentChildRelation(parentNode, childNode);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Child cannot be null")
    public void removeNullChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode = null;

        parentNode.removeChild(childNode);
    }

    /*********************
     *   REPLACE CHILD   *
     *********************/

    public void replaceChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);
        parentNode.addChild(childNode1);

        assertTrue(parentNode.replaceChild(childNode2, childNode1));
        assertBranchNodeWithContent(parentNode, 1, 0);
        assertParentChildRelation(parentNode, childNode2);
        assertNoParentChildRelation(parentNode, childNode1);
        assertNull(childNode1.getParent());
    }

    public void replaceNonAddedChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);

        assertFalse(parentNode.replaceChild(childNode2, childNode1));
        assertBranchNodeWithContent(parentNode, 0, 0);
        assertNoParentChildRelation(parentNode, childNode1);
        assertNoParentChildRelation(parentNode, childNode2);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Child cannot be null")
    public void replaceNullChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        Node<Integer> childNode2 = null;
        parentNode.addChild(childNode1);

        parentNode.replaceChild(childNode2, childNode1);
    }

    /*********************
     *   SWAP CHILDREN   *
     *********************/

    public void swapChildren() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);
        parentNode.addChild(childNode2);

        assertTrue(parentNode.swapChildren(childNode1, childNode2));
        assertBranchNodeWithContent(parentNode, 2, 0);
        List<? extends Node<Integer>> children = parentNode.getChildren();
        childNode1 = children.get(0);
        childNode2 = children.get(1);
        assertParentChildRelation(parentNode, childNode1);
        assertParentChildRelation(parentNode, childNode2);
        assertLeafNodeWithContent(childNode1, 2);
        assertLeafNodeWithContent(childNode2, 1);
    }

    public void swapNonAddedChildren() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);

        assertFalse(parentNode.swapChildren(childNode1, childNode2));
        assertBranchNodeWithContent(parentNode, 1, 0);
        assertParentChildRelation(parentNode, childNode1);
        assertNoParentChildRelation(parentNode, childNode2);
        assertLeafNodeWithContent(childNode1, 1);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Child cannot be null")
    public void swapNullChildren() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode1);
        Node<Integer> childNode2 = null;

        parentNode.swapChildren(childNode1, childNode2);
    }

    /*********************
     *   MOVE CHILD UP   *
     *********************/

    public void moveUpChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);
        parentNode.addChild(childNode2);

        assertTrue(parentNode.moveUpChild(childNode2));
        assertEquals(parentNode.getChildren().get(0), childNode2);
        assertEquals(parentNode.getChildren().get(1), childNode1);
        assertParentChildRelation(parentNode, childNode1);
        assertParentChildRelation(parentNode, childNode2);
    }

    public void moveUpChildWithFirstChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);
        parentNode.addChild(childNode2);

        assertFalse(parentNode.moveUpChild(childNode1));
        assertEquals(parentNode.getChildren().get(0), childNode1);
        assertEquals(parentNode.getChildren().get(1), childNode2);
        assertParentChildRelation(parentNode, childNode1);
        assertParentChildRelation(parentNode, childNode2);
    }

    public void moveUpNonAddedChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);
        parentNode.addChild(childNode2);

        assertFalse(parentNode.moveUpChild(childNode1));
        assertEquals(parentNode.getChildren().get(0), childNode2);
        assertParentChildRelation(parentNode, childNode2);
        assertNoParentChildRelation(parentNode, childNode1);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Child cannot be null")
    public void moveUpNullChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = null;

        parentNode.moveUpChild(childNode1);
    }

    /***********************
     *   MOVE CHILD DOWN   *
     ***********************/

    public void moveDownChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);
        parentNode.addChild(childNode2);

        assertTrue(parentNode.moveDownChild(childNode1));
        assertEquals(parentNode.getChildren().get(0), childNode2);
        assertEquals(parentNode.getChildren().get(1), childNode1);
        assertParentChildRelation(parentNode, childNode1);
        assertParentChildRelation(parentNode, childNode2);
    }

    public void moveDownChildWithLastChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);
        parentNode.addChild(childNode2);

        assertFalse(parentNode.moveDownChild(childNode2));
        assertEquals(parentNode.getChildren().get(0), childNode1);
        assertEquals(parentNode.getChildren().get(1), childNode2);
        assertParentChildRelation(parentNode, childNode1);
        assertParentChildRelation(parentNode, childNode2);
    }

    public void moveDownNonAddedChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = new DefaultLeafNode<>(1);
        parentNode.addChild(childNode1);
        Node<Integer> childNode2 = new DefaultLeafNode<>(2);

        assertFalse(parentNode.moveDownChild(childNode2));
        assertEquals(parentNode.getChildren().get(0), childNode1);
        assertParentChildRelation(parentNode, childNode1);
        assertNoParentChildRelation(parentNode, childNode2);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Child cannot be null")
    public void moveDownNullChild() {
        Node<Integer> parentNode = new DefaultBranchNode<>(0);
        Node<Integer> childNode1 = null;

        parentNode.moveDownChild(childNode1);
    }

    /****************************
     *   MOVE TO UPPER LEVEL   *
     ****************************/

    public void moveToUpperLevel() {
        Node<Integer> root = new DefaultBranchNode<>(0);
        Node<Integer> parentNode = new DefaultBranchNode<>(1);
        Node<Integer> childNode = new DefaultLeafNode<>(2);
        parentNode.addChild(childNode);
        root.addChild(parentNode);

        assertTrue(childNode.moveToUpperLevel());
        assertRootNode(root, 2);
        assertParentChildRelation(root, childNode);
        assertNoParentChildRelation(parentNode, childNode);
        assertBranchNodeWithContent(parentNode, 0, 1);
    }

    public void moveRootToUpperLevel() {
        Node<Integer> root = new DefaultBranchNode<>(0);

        assertFalse(root.moveToUpperLevel());
        assertRootNode(root, 0);
    }

    public void moveToRootLevel() {
        Node<Integer> root = new DefaultBranchNode<>(0);
        Node<Integer> parentNode = new DefaultBranchNode<>(1);
        root.addChild(parentNode);

        assertFalse(parentNode.moveToUpperLevel());
        assertRootNode(root, 1);
        assertParentChildRelation(root, parentNode);
    }

    /***************************
     *   MOVE TO LOWER LEVEL   *
     ***************************/

    public void moveToLowerLevel() {
        Node<Integer> root = new DefaultBranchNode<>(0);
        Node<Integer> parentNode1 = new DefaultBranchNode<>(1);
        Node<Integer> parentNode2 = new DefaultBranchNode<>(2);
        root.addChild(parentNode1);
        root.addChild(parentNode2);

        assertTrue(parentNode2.moveToLowerLevel(parentNode1));
        assertParentChildRelation(root, parentNode1);
        assertParentChildRelation(parentNode1, parentNode2);
        assertNoParentChildRelation(root, parentNode2);
    }

    public void moveRootToLowerLevel() {
        Node<Integer> root = new DefaultBranchNode<>(0);
        Node<Integer> parentNode1 = new DefaultBranchNode<>(1);
        root.addChild(parentNode1);

        assertFalse(root.moveToLowerLevel(parentNode1));
        assertRootNode(root, 1);
        assertParentChildRelation(root, parentNode1);
    }

    public void moveToLowerLeafNode() {
        Node<Integer> root = new DefaultBranchNode<>(0);
        Node<Integer> parentLeafNode1 = new DefaultLeafNode<>(1);
        Node<Integer> parentNode2 = new DefaultBranchNode<>(2);
        root.addChild(parentLeafNode1);
        root.addChild(parentNode2);

        assertFalse(parentNode2.moveToLowerLevel(parentLeafNode1));
        assertRootNode(root, 2);
        assertParentChildRelation(root, parentLeafNode1);
        assertParentChildRelation(root, parentNode2);
    }

    public void moveToLowerUnknowNode() {
        Node<Integer> root = new DefaultBranchNode<>(0);
        Node<Integer> parentNode1 = new DefaultBranchNode<>(1);
        Node<Integer> childNode1 = new DefaultLeafNode<>(11);
        Node<Integer> parentNode2 = new DefaultBranchNode<>(2);
        root.addChild(parentNode1);
        root.addChild(parentNode2);
        parentNode1.addChild(childNode1);

        assertFalse(childNode1.moveToLowerLevel(parentNode2));
        assertParentChildRelation(parentNode1, childNode1);
        assertBranchNodeWithContent(parentNode2, 0, 2);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Sibling cannot be null")
    public void moveToLowerNullNode() {
        Node<Integer> root = new DefaultBranchNode<>(0);
        Node<Integer> parentNode = null;

        root.moveToLowerLevel(parentNode);
    }
}
