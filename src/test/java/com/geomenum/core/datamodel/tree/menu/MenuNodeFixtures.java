/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.datamodel.tree.menu;

import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.core.domainmodel.menu.CoreMenuItemFixtures;
import com.geomenum.core.domainmodel.menu.CoreSubmenu;
import com.geomenum.core.domainmodel.menu.CoreSubmenuFixtures;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class MenuNodeFixtures {

    public static UUID ROOT_ID = UUID.fromString("91e934ce-2cbb-4be8-a8af-c0a8230fea26");
    public static UUID BRANCH_NODE1_DEPTH1_ID = UUID.fromString("1afdea36-e66c-46f8-a0a3-b6aa9d4dae57");
    public static UUID BRANCH_NODE2_DEPTH1_ID = UUID.fromString("fca346e3-5641-4731-a8a0-d1fd11727c88");
    public static UUID LEAF_NODE1_DEPTH2_ID = UUID.fromString("a1018378-8e0e-4ec0-a691-c3f98f6c629b");
    public static UUID LEAF_NODE2_DEPTH2_ID = UUID.fromString("14cff163-bd2c-42cb-b3b1-6fb29a75076c");
    public static UUID LEAF_NODE3_DEPTH2_ID = UUID.fromString("632eba30-a850-4aca-9e05-8da2e2c1d61c");
    public static UUID LEAF_NODE4_DEPTH2_ID = UUID.fromString("2d635744-3692-4a62-9ac9-188bb5823cdd");


    /**
     * Creates a standard tree of height 2.<br/>
     * It has a root node with 2 children, each of these having themselves 2 children.
     *
     * @return the root node of the tree
     */
    public static MenuNode standardTree() {
        MenuNode leaf_1_1 = newLeafMenuNode(LEAF_NODE1_DEPTH2_ID);
        MenuNode leaf_1_2 = newLeafMenuNode(LEAF_NODE2_DEPTH2_ID);
        MenuNode branch_1 = newBranchMenuNode(BRANCH_NODE1_DEPTH1_ID);
        branch_1.addChild(leaf_1_1);
        branch_1.addChild(leaf_1_2);

        MenuNode leaf_2_1 = newLeafMenuNode(LEAF_NODE3_DEPTH2_ID);
        MenuNode leaf_2_2 = newLeafMenuNode(LEAF_NODE4_DEPTH2_ID);
        MenuNode branch_2 = newBranchMenuNode(BRANCH_NODE2_DEPTH1_ID);
        branch_2.addChild(leaf_2_1);
        branch_2.addChild(leaf_2_2);

        MenuNode root = newRootNode(ROOT_ID);
        root.addChild(branch_1);
        root.addChild(branch_2);

        return root;
    }

    /********************
     *   NODE FACTORY   *
     ********************/

    public static MenuNode newRootNode(UUID id) {
        Map<Object, Object> rootNodeDTO = Maps.newHashMap();
        rootNodeDTO.put("id", id);
        rootNodeDTO.put("localizedNames", ImmutableMap.of(Locale.ENGLISH, "root"));
        rootNodeDTO.put("enabled", true);
        rootNodeDTO.put("localizedDescriptions", ImmutableMap.of(Locale.ENGLISH, ""));
        return new BranchMenuNode(CoreSubmenu.of(rootNodeDTO));
    }

    public static MenuNode newBranchMenuNode(UUID id) {
        return new BranchMenuNode(CoreSubmenu.of(CoreSubmenuFixtures.newDto(id)));
    }

    public static MenuNode newLeafMenuNode(UUID id) {
        return new LeafMenuNode(CoreMenuItem.of(CoreMenuItemFixtures.newDTO(id)));
    }
}
