/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNodeFixtures;
import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebControllerIntegrationTests;
import com.geomenum.web.domainmodel.menu.WebMenuItemFixtures;
import com.geomenum.web.domainmodel.menu.WebSubmenuFixtures;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubmenuDashboardWebControllerIntegrationTests extends AbstractWebControllerIntegrationTests {

    @BeforeClass
    public void setUpBeforeClass() {
        super.setUpBeforeClass();
        insertStandardRestaurant();
    }

    @AfterClass
    public void tearDownAfterClass() {
        clearMenusCollection();
        clearRestaurantsCollection();
    }

    @BeforeMethod
    public void setUpBeforeMethod() {
        clearMenusCollection();
    }

    //~ Create Menu Item ===============================================================================================

    public void addMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("addMenuItem", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_CREATION_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(view().name(containsString(WebURLPath.redirect(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, "toto")))));
    }

    public void addMenuItemToUnknownSubmenu() throws Exception {
        insertStandardMenu();
        UUID unknownSubmenuId = UUID.randomUUID();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, unknownSubmenuId)).session(loggedSession())
                .param("addMenuItem", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_CREATION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, unknownSubmenuId)));
    }

    public void addMenuItemToMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)).session(loggedSession())
                .param("addMenuItem", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_CREATION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)));
    }

    //~ Create Submenu =================================================================================================

    public void addSubmenu() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("addSubmenu", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_CREATION_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(view().name(containsString(WebURLPath.redirect(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, "toto")))));
    }

    public void addSubmenuToUnknownSubmenu() throws Exception {
        insertStandardMenu();
        UUID unknownSubmenuId = UUID.randomUUID();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, unknownSubmenuId)).session(loggedSession())
                .param("addSubmenu", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_CREATION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, unknownSubmenuId)));
    }

    public void addSubmenuToMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)).session(loggedSession())
                .param("addSubmenu", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_CREATION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)));
    }

    //~ Delete Menu Item ===============================================================================================

    public void deleteMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("deleteMenuItem", WebMenuItemFixtures.ID.toString()))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_DELETION_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void deleteUnknownMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("deleteMenuItem", UUID.randomUUID().toString()))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_DELETION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    //~ Delete Submenu =================================================================================================

    public void deleteSubmenu() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)).session(loggedSession())
                .param("deleteSubmenu", WebSubmenuFixtures.ID.toString()))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_DELETION_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)));
    }

    public void deleteUnknownSubmenu() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)).session(loggedSession())
                .param("deleteSubmenu", UUID.randomUUID().toString()))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_DELETION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)));
    }

    //~ Move up ========================================================================================================

    public void moveUp() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("moveUp", WebMenuItemFixtures.ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void moveUpFirstChild() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("moveUp", MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    //~ Move down ======================================================================================================

    public void moveDown() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("moveDown", MenuNodeFixtures.LEAF_NODE3_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void moveDownLastChild() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("moveDown", WebMenuItemFixtures.ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    //~ Move to upper level ============================================================================================


    public void moveToUpperLevel() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("moveToUpperLevel", WebMenuItemFixtures.ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void moveToUpperRootLevel() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)).session(loggedSession())
                .param("moveToUpperLevel", WebSubmenuFixtures.ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)));
    }

    //~ Move to lower level ============================================================================================

    public void moveToLowerLevel() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)).session(loggedSession())
                .param("moveToLowerLevel", MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID.toString())
                .param("sibling", MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)));
    }

    public void moveToLowerLevelToMenuItem() throws Exception {
        insertStandardMenu();

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)).session(loggedSession())
                .param("moveToLowerLevel", MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID.toString())
                .param("sibling", MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }
}
