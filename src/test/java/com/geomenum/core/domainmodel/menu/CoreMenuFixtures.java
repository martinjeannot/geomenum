/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNodeFixtures;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurantFixtures;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static com.geomenum.common.Fixtures.localDateTime;

/**
 * Fixtures for {@link CoreMenu}.
 */
public class CoreMenuFixtures {

    public static String ID = "4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY";
    public static String RESTAURANT_ID = CoreRestaurantFixtures.ID;
    public static String UPDATED_RESTAURANT_ID = "JhLy5b2L2cckN6R7C4moIS5Z05QyTZrBCaWUm_KLOM4";

    public static CoreMenu standardMenu() {
        return CoreMenu.of(newDto());
    }

    public static CoreMenu standardUpdatedMenu() {
        return CoreMenu.of(updatedDto());
    }

    public static Map<Object, Object> newDtoWithoutId() {
        Map<Object, Object> menuDto = Maps.newHashMap();
        menuDto.put("restaurantId", RESTAURANT_ID);
        menuDto.put("menuTree", buildMenuTree());
        menuDto.put("enabled", true);
        menuDto.put("supportedLanguages", Lists.newArrayList(Locale.ENGLISH.toString(), Locale.FRENCH.toString()));
        menuDto.put("currency", "USD");
        // time-tracking fields
        menuDto.put("creationDate", null);
        menuDto.put("lastUpdateDate", null);
        menuDto.put("deletionDate", null);
        return menuDto;
    }

    public static Map<Object, Object> newDto() {
        Map<Object, Object> menuDto = newDtoWithoutId();
        menuDto.put("id", ID);
        return menuDto;
    }

    public static Map<Object, Object> invalidDto() {
        Map<Object, Object> menuDto = newDto();
        menuDto.remove("enabled");
        return menuDto;
    }

    public static Map<Object, Object> updatedDto() {
        Map<Object, Object> menuDto = newDto();
        menuDto.put("restaurantId", UPDATED_RESTAURANT_ID);
        menuDto.put("enabled", false);
        menuDto.put("supportedLanguages", Lists.newArrayList(Locale.ENGLISH.toString(), Locale.GERMAN.toString(),
                Locale.ITALIAN.toString()));
        menuDto.put("currency", "EUR");
        // time-tracking fields
        menuDto.put("creationDate", localDateTime);
        return menuDto;
    }

    private static Map<Object, Object> buildMenuTree() {
        Map<Object, Object> leafNode1Depth2 = standardLeafDto(MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID);
        Map<Object, Object> leafNode2Depth2 = standardLeafDto(MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
        Map<Object, Object> leafNode3Depth2 = standardLeafDto(MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID);
        Map<Object, Object> leafNode4Depth2 = standardLeafDto(MenuNodeFixtures.LEAF_NODE4_DEPTH2_ID);
        Map<Object, Object> branchNode1Depth1 = standardBranchDto(MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID, leafNode1Depth2, leafNode2Depth2);
        Map<Object, Object> branchNode2Depth1 = standardBranchDto(MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID, leafNode3Depth2, leafNode4Depth2);

        Map<Object, Object> rootContent = Maps.newHashMap();
        rootContent.put("id", MenuNodeFixtures.ROOT_ID);
        rootContent.put("localizedNames", ImmutableMap.of(Locale.ENGLISH, "root"));
        rootContent.put("enabled", true);
        rootContent.put("localizedDescriptions", ImmutableMap.of(Locale.ENGLISH, ""));

        Map<Object, Object> root = Maps.newHashMap();
        root.put("menuNodeContent", rootContent);
        root.put("children", Lists.<Object>newArrayList(branchNode1Depth1, branchNode2Depth1));

        return root;
    }

    private static Map<Object, Object> standardBranchDto(UUID id, Map<Object, Object>... children) {
        Map<Object, Object> standardBranchNode = Maps.newHashMap();
        standardBranchNode.put("menuNodeContent", CoreSubmenuFixtures.newDto(id));
        standardBranchNode.put("children", Lists.newArrayList(children));
        return standardBranchNode;
    }

    private static Map<Object, Object> standardLeafDto(UUID id) {
        Map<Object, Object> standardLeafNode = Maps.newHashMap();
        standardLeafNode.put("menuNodeContent", CoreMenuItemFixtures.newDTO(id));
        return standardLeafNode;
    }
}
