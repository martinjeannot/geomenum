/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.services.AbstractCoreServiceIntegrationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;

public class MenuItemCoreServiceTests extends AbstractCoreServiceIntegrationTests {

    @Autowired
    private MenuItemCoreService menuItemCoreService;

    @Autowired
    private MenuCoreService menuCoreService;

    @BeforeMethod
    public void setUp() {
        clearMenuCollection();
    }

    //~ create =========================================================================================================

    /*public void create() {
        insertStandardMenu();

        CoreMenuItem createdObject = menuItemCoreService.create(CoreMenuFixtures.ID, CoreSubmenuFixtures.ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertNotNull(createdObject);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(CoreSubmenuFixtures.ID);
        assertEquals(parentNode.getChildCount(), 3);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
        assertEquals(parentNode.getChildren().get(2).getContent().getId(), createdObject.getId());
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void createFromUnknownMenu() {
        menuItemCoreService.create(CoreMenuFixtures.ID, CoreSubmenuFixtures.ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find parentNode with id 5ef104dc-9c04-4c95-b91b-821710804e20")
    public void createFromUnknownParentNode() {
        insertStandardMenu();
        UUID UnknownParentNodeId = UUID.fromString("5ef104dc-9c04-4c95-b91b-821710804e20");

        menuItemCoreService.create(CoreMenuFixtures.ID, UnknownParentNodeId);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Given parent node does not allow children \\(id : a1018378-8e0e-4ec0-a691-c3f98f6c629b\\)")
    public void createFromMenuItem() {
        insertStandardMenu();

        menuItemCoreService.create(CoreMenuFixtures.ID, CoreMenuItemFixtures.ID);
    }

    //~ findById =======================================================================================================

    public void findById() {
        insertStandardMenu();

        CoreMenuItem retrievedObject = menuItemCoreService.findById(CoreMenuFixtures.ID, CoreMenuItemFixtures.ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertNotNull(retrievedObject);
    }

    public void findByIdWithUnknownObject() {
        insertStandardMenu();

        CoreMenuItem retrievedObject = menuItemCoreService.findById(CoreMenuFixtures.ID, UUID.randomUUID());

        assertEquals(getMenuCollectionSize(), 1);
        assertNull(retrievedObject);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void findByIdWithUnknownMenu() {
        menuItemCoreService.findById(CoreMenuFixtures.ID, CoreMenuItemFixtures.ID);
    }

    //~ update =========================================================================================================

    public void update() {
        insertStandardMenu();
        assertTrue((Boolean) newDTO().get("enabled"));

        CoreMenuItem updatedObject = menuItemCoreService.update(CoreMenuFixtures.ID, updatedDTO());

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(updatedObject.getEnabled());
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(CoreSubmenuFixtures.ID);
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), updatedObject.getId());
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.core.domainmodel.menu.CoreMenuItem\\] Validation failed :\n" +
                    "localizedNames : may not be empty\n")
    public void updateWithInvalidObject() {
        insertStandardMenu();

        menuItemCoreService.update(CoreMenuFixtures.ID, invalidDTO());
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void updateWithUnknownMenu() {
        menuItemCoreService.update(CoreMenuFixtures.ID, updatedDTO());
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void updateWithUnknownObject() {
        insertStandardMenu();
        Map<Object, Object> unknownObject = updatedDTO();
        unknownObject.put("id", UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47"));

        menuItemCoreService.update(CoreMenuFixtures.ID, unknownObject);
    }

    //~ delete =========================================================================================================

    public void delete() {
        insertStandardMenu();

        CoreMenuItem deletedObject = menuItemCoreService.delete(CoreMenuFixtures.ID, CoreMenuItemFixtures.ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertNotNull(deletedObject);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(CoreSubmenuFixtures.ID);
        assertEquals(parentNode.getChildCount(), 1);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void deleteWithUnknownMenu() {
        menuItemCoreService.delete(CoreMenuFixtures.ID, CoreMenuItemFixtures.ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void deleteWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        menuItemCoreService.delete(CoreMenuFixtures.ID, unknownObjectId);
    }

    //~ moveUp =========================================================================================================

    public void moveUp() {
        insertStandardMenu();

        boolean moveResult = menuItemCoreService.moveUp(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertTrue(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID);
    }

    public void moveUpFirstChild() {
        insertStandardMenu();

        boolean moveResult = menuItemCoreService.moveUp(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void moveUpWithUnknownMenu() {
        menuItemCoreService.moveUp(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void moveUpWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        menuItemCoreService.moveUp(CoreMenuFixtures.ID, unknownObjectId);
    }

    //~ moveDown =======================================================================================================

    public void moveDown() {
        insertStandardMenu();

        boolean moveResult = menuItemCoreService.moveDown(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertTrue(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID);
    }

    public void moveDownLastChild() {
        insertStandardMenu();

        boolean moveResult = menuItemCoreService.moveDown(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void moveDownWithUnknownMenu() {
        menuItemCoreService.moveDown(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void moveDownWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        menuItemCoreService.moveDown(CoreMenuFixtures.ID, unknownObjectId);
    }

    //~ moveToUpperLevel ===============================================================================================

    public void moveToUpperLevel() {
        insertStandardMenu();

        boolean moveResult = menuItemCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertTrue(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode previousParentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
        assertEquals(previousParentNode.getChildCount(), 1);
        assertEquals(standardMenu.getRoot().getChildCount(), 3);
        assertEquals(standardMenu.getRoot().getChildren().get(2).getContent().getId(), MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
    }

    public void moveRootToUpperLevel() {
        insertStandardMenu();

        boolean moveResult = menuItemCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        assertNull(standardMenu.getRoot().getParent());
    }

    public void moveToRootLevel() {
        insertStandardMenu();
        menuItemCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);

        boolean moveResult = menuItemCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        assertEquals(standardMenu.getRoot().getContent().getId(), MenuNodeFixtures.ROOT_ID);
        assertEquals(standardMenu.getRoot().getChildCount(), 3);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void moveToUpperLevelWithUnknownMenu() {
        menuItemCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void moveToUpperLevelWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        menuItemCoreService.moveToUpperLevel(CoreMenuFixtures.ID, unknownObjectId);
    }

    //~ moveToLowerLevel ===============================================================================================

    public void moveToLowerLevel() {
        insertStandardMenu();
        menuItemCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID);


        boolean moveResult = menuItemCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertTrue(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.LEAF_NODE4_DEPTH2_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID);
    }

    public void moveRootToLowerLevel() {
        insertStandardMenu();

        boolean moveResult = menuItemCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
    }

    public void moveToLowerLevelToMenuItem() {
        insertStandardMenu();

        boolean moveResult = menuItemCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID, MenuNodeFixtures.LEAF_NODE4_DEPTH2_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.LEAF_NODE4_DEPTH2_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void moveToLowerLevelWithUnknownMenu() {
        menuItemCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void moveToLowerLevelWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        menuItemCoreService.moveToLowerLevel(CoreMenuFixtures.ID, unknownObjectId, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }*/
}
