/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.services.menu;

import com.geomenum.core.services.AbstractCoreServiceIntegrationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;

public class SubmenuCoreServiceTests extends AbstractCoreServiceIntegrationTests {

    @Autowired
    private SubmenuCoreService submenuCoreService;

    @Autowired
    private MenuCoreService menuCoreService;

    @BeforeMethod
    public void setUp() {
        clearMenuCollection();
    }

    //~ create =========================================================================================================

    /*public void create() {
        insertStandardMenu();

        CoreSubmenu createdObject = submenuCoreService.create(CoreMenuFixtures.ID, CoreSubmenuFixtures.ID);

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
        submenuCoreService.create(CoreMenuFixtures.ID, CoreSubmenuFixtures.ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find parentNode with id 5ef104dc-9c04-4c95-b91b-821710804e20")
    public void createFromUnknownParentNode() {
        insertStandardMenu();
        UUID UnknownParentNodeId = UUID.fromString("5ef104dc-9c04-4c95-b91b-821710804e20");

        submenuCoreService.create(CoreMenuFixtures.ID, UnknownParentNodeId);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "Given parent node does not allow children \\(id : a1018378-8e0e-4ec0-a691-c3f98f6c629b\\)")
    public void createFromMenuItem() {
        insertStandardMenu();

        submenuCoreService.create(CoreMenuFixtures.ID, CoreMenuItemFixtures.ID);
    }

    //~ findById =======================================================================================================

    public void findById() {
        insertStandardMenu();

        CoreSubmenu retrievedObject = submenuCoreService.findById(CoreMenuFixtures.ID, CoreSubmenuFixtures.ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertNotNull(retrievedObject);
    }

    public void findByIdWithUnknownObject() {
        insertStandardMenu();

        CoreSubmenu retrievedObject = submenuCoreService.findById(CoreMenuFixtures.ID, UUID.randomUUID());

        assertEquals(getMenuCollectionSize(), 1);
        assertNull(retrievedObject);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void findByIdWithUnknownMenu() {
        submenuCoreService.findById(CoreMenuFixtures.ID, CoreSubmenuFixtures.ID);
    }

    //~ update =========================================================================================================

    public void update() {
        insertStandardMenu();
        assertTrue((Boolean) newDTO().get("enabled"));

        CoreSubmenu updatedObject = submenuCoreService.update(CoreMenuFixtures.ID, updatedDTO());

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(updatedObject.getEnabled());
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot();
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), updatedObject.getId());
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }

    @Test(expectedExceptions = {ValidationException.class},
            expectedExceptionsMessageRegExp = "\\[com.geomenum.core.domainmodel.menu.CoreSubmenu\\] Validation failed :\n" +
                    "localizedNames : may not be empty\n")
    public void updateWithInvalidObject() {
        insertStandardMenu();

        submenuCoreService.update(CoreMenuFixtures.ID, invalidDTO());
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void updateWithUnknownMenu() {
        submenuCoreService.update(CoreMenuFixtures.ID, updatedDTO());
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void updateWithUnknownObject() {
        insertStandardMenu();
        Map<Object, Object> unknownObject = updatedDTO();
        unknownObject.put("id", UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47"));

        submenuCoreService.update(CoreMenuFixtures.ID, unknownObject);
    }

    //~ delete =========================================================================================================

    public void delete() {
        insertStandardMenu();

        CoreSubmenu deletedObject = submenuCoreService.delete(CoreMenuFixtures.ID, CoreSubmenuFixtures.ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertNotNull(deletedObject);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot();
        assertEquals(parentNode.getChildCount(), 1);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void deleteWithUnknownMenu() {
        submenuCoreService.delete(CoreMenuFixtures.ID, CoreSubmenuFixtures.ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void deleteWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        submenuCoreService.delete(CoreMenuFixtures.ID, unknownObjectId);
    }

    //~ moveUp =========================================================================================================

    public void moveUp() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveUp(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertTrue(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot();
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
    }

    public void moveUpFirstChild() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveUp(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot();
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void moveUpWithUnknownMenu() {
        submenuCoreService.moveUp(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void moveUpWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        submenuCoreService.moveUp(CoreMenuFixtures.ID, unknownObjectId);
    }

    //~ moveDown =======================================================================================================

    public void moveDown() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveDown(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertTrue(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot();
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
    }

    public void moveDownLastChild() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveDown(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot();
        assertEquals(parentNode.getChildCount(), 2);
        assertEquals(parentNode.getChildren().get(0).getContent().getId(), MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
        assertEquals(parentNode.getChildren().get(1).getContent().getId(), MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void moveDownWithUnknownMenu() {
        submenuCoreService.moveDown(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void moveDownWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        submenuCoreService.moveDown(CoreMenuFixtures.ID, unknownObjectId);
    }

    //~ moveToUpperLevel ===============================================================================================

    public void moveToUpperLevel() {
        insertStandardMenu();
        submenuCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);

        boolean moveResult = submenuCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertTrue(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode previousParentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
        assertEquals(previousParentNode.getChildCount(), 2);
        assertEquals(standardMenu.getRoot().getChildCount(), 2);
        assertEquals(standardMenu.getRoot().getChildren().get(1).getContent().getId(), MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
    }

    public void moveRootToUpperLevel() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        assertNull(standardMenu.getRoot().getParent());
    }

    public void moveToRootLevel() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        assertEquals(standardMenu.getRoot().getContent().getId(), MenuNodeFixtures.ROOT_ID);
        assertEquals(standardMenu.getRoot().getChildCount(), 2);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find menu with id 4a0ue9ms13aAlcnWAYnlCZHH7zjSQhGzCHL5G0X3OxY")
    public void moveToUpperLevelWithUnknownMenu() {
        submenuCoreService.moveToUpperLevel(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void moveToUpperLevelWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        submenuCoreService.moveToUpperLevel(CoreMenuFixtures.ID, unknownObjectId);
    }

    //~ moveToLowerLevel ===============================================================================================

    public void moveToLowerLevel() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertTrue(moveResult);
        // children verification
        CoreMenu standardMenu = menuCoreService.findById(CoreMenuFixtures.ID);
        MenuNode parentNode = standardMenu.getRoot().findNodeById(MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
        assertEquals(parentNode.getChildCount(), 3);
        assertEquals(parentNode.getChildren().get(2).getContent().getId(), MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);
    }

    public void moveRootToLowerLevel() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID);

        assertEquals(getMenuCollectionSize(), 1);
        assertFalse(moveResult);
    }

    public void moveToLowerLevelToMenuItem() {
        insertStandardMenu();

        boolean moveResult = submenuCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID, MenuNodeFixtures.LEAF_NODE4_DEPTH2_ID);

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
        submenuCoreService.moveToLowerLevel(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }

    @Test(expectedExceptions = {NullPointerException.class},
            expectedExceptionsMessageRegExp = "Cannot find node with id 4f9da2a4-de8b-47cd-a4e2-4adb3e77da47")
    public void moveToLowerLevelWithUnknownObject() {
        insertStandardMenu();
        UUID unknownObjectId = UUID.fromString("4f9da2a4-de8b-47cd-a4e2-4adb3e77da47");

        submenuCoreService.moveToLowerLevel(CoreMenuFixtures.ID, unknownObjectId, MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID);
    }*/
}
