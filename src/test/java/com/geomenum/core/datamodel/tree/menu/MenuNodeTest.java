/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.datamodel.tree.menu;

import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static com.geomenum.config.TestingLevels.UNIT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

@Test(groups = {UNIT})
public class MenuNodeTest {

    public void findNodeById() {
        MenuNode root = MenuNodeFixtures.standardTree();
        MenuNode result;
        UUID testId;

        // test 1
        testId = MenuNodeFixtures.ROOT_ID;
        result = root.findNodeById(testId);
        assertEquals(result.getContent().getId(), testId);

        // test 2
        testId = MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID;
        result = root.findNodeById(testId);
        assertEquals(result.getContent().getId(), testId);

        // test 3
        testId = MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID;
        result = root.findNodeById(testId);
        assertEquals(result.getContent().getId(), testId);

        // test 4
        testId = MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID;
        result = root.findNodeById(testId);
        assertEquals(result.getContent().getId(), testId);
    }

    public void findNodeByIdWithUnknownId() {
        MenuNode root = MenuNodeFixtures.standardTree();
        MenuNode result;
        UUID testId;

        testId = UUID.randomUUID();
        result = root.findNodeById(testId);
        assertNull(result);
    }

    public void computeNodePathById() {
        MenuNode root = MenuNodeFixtures.standardTree();
        List<MenuNode> result;

        // test 1
        result = root.computeNodePathById(MenuNodeFixtures.ROOT_ID);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), root);

        // test 2
        result = root.computeNodePathById(MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0), root);
        assertEquals(result.get(1), root.findNodeById(MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID));

        // test 3
        result = root.computeNodePathById(MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
        assertEquals(result.size(), 3);
        assertEquals(result.get(0), root);
        assertEquals(result.get(1), root.findNodeById(MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID));
        assertEquals(result.get(2), root.findNodeById(MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID));

        // test 4
        result = root.computeNodePathById(MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID);
        assertEquals(result.size(), 3);
        assertEquals(result.get(0), root);
        assertEquals(result.get(1), root.findNodeById(MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID));
        assertEquals(result.get(2), root.findNodeById(MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID));
    }

    public void computeNodePathByIdWithUnknownId() {
        MenuNode root = MenuNodeFixtures.standardTree();
        List<MenuNode> result;
        UUID testId;

        testId = UUID.randomUUID();
        result = root.computeNodePathById(testId);
        assertNull(result);
    }
}
