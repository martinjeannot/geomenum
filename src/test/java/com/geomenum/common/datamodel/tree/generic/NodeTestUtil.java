/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.datamodel.tree.generic;

import static org.testng.Assert.*;

/**
 * {@link Node} utility class for tests.
 */
public class NodeTestUtil {

    public static <T> void assertRootNode(Node<T> node, int expectedChildCount) {
        assertNotNull(node);
        assertTrue(node.isRoot());
        assertBranchNode(node, expectedChildCount);
    }

    public static <T> void assertBranchNodeWithContent(Node<T> node, int expectedChildCount, T content) {
        assertBranchNode(node, expectedChildCount);
        assertEquals(node.getContent(), content);
    }

    private static <T> void assertBranchNode(Node<T> node, int expectedChildCount) {
        assertNotNull(node);
        assertTrue(node.allowsChildren());
        if(expectedChildCount == 0) {
            assertEquals(node.getChildCount(), 0);
            assertFalse(node.hasChildren());
        } else {
            assertEquals(node.getChildCount(), expectedChildCount);
            assertTrue(node.hasChildren());
        }
    }

    public static <T> void assertLeafNodeWithContent(Node<T> node, T content) {
        assertLeafNode(node);
        assertEquals(node.getContent(), content);
    }

    private static <T> void assertLeafNode(Node<T> node) {
        assertNotNull(node);
        assertFalse(node.isRoot());
        assertFalse(node.allowsChildren());
        assertFalse(node.hasChildren());
        assertEquals(0, node.getChildCount());
    }

    public static <T> void assertParentChildRelation(Node<T> parent, Node<T> child) {
        // assert parent --> child relation
        assertTrue(parent.hasChild(child));
        // assert child --> parent relation
        assertEquals(child.getParent(), parent);
    }

    public static <T> void assertNoParentChildRelation(Node<T> parent, Node<T> child) {
        // assert no parent --> child relation
        assertFalse(parent.hasChild(child));
        // assert no child --> parent relation
        assertNotEquals(child.getParent(), parent);
    }
}
