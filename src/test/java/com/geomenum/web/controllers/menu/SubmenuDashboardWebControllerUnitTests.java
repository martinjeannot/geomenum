/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.core.datamodel.tree.menu.MenuNodeFixtures;
import com.geomenum.core.domainmodel.menu.CoreMenuFixtures;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.AbstractWebControllerUnitTests;
import com.geomenum.web.domainmodel.menu.WebMenuItemFixtures;
import com.geomenum.web.domainmodel.menu.WebSubmenuFixtures;
import com.geomenum.web.requestsresponses.menu.MoveMenuContentRequest;
import com.geomenum.web.requestsresponses.menu.MoveMenuContentResponse;
import com.geomenum.web.requestsresponses.menu.MoveMenuContentResponseFixtures;
import com.geomenum.web.requestsresponses.menu.menuitem.*;
import com.geomenum.web.requestsresponses.menu.submenu.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubmenuDashboardWebControllerUnitTests extends AbstractWebControllerUnitTests {

    @InjectMocks
    private SubmenuDashboardWebController controller;

    @Override
    protected AbstractWebController getController() {
        return controller;
    }

    //~ Create =========================================================================================================

    public void addMenuItem() throws Exception {
        /*Mockito.when(requestDispatcher.getResponse(any(CreateMenuItemRequest.class), eq(CreateMenuItemResponse.class)))
                .thenReturn(CreateMenuItemResponseFixtures.successResponse());*/

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("addMenuItem", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_CREATION_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getMenuItemURL(CoreMenuFixtures.ID, WebMenuItemFixtures.ID)));
    }

    public void addMenuItemWithFailure() throws Exception {
        /*Mockito.when(requestDispatcher.getResponse(any(CreateMenuItemRequest.class), eq(CreateMenuItemResponse.class)))
                .thenReturn(CreateMenuItemResponseFixtures.defaultResponse());*/

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("addMenuItem", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_CREATION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void addSubmenu() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(CreateSubmenuRequest.class), eq(CreateSubmenuResponse.class)))
                .thenReturn(/*CreateSubmenuResponseFixtures.successResponse(MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID)*/ null);

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("addSubmenu", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_CREATION_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID)));
    }

    public void addSubmenuWithFailure() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(CreateSubmenuRequest.class), eq(CreateSubmenuResponse.class)))
                .thenReturn(/*CreateSubmenuResponseFixtures.defaultResponse()*/ null);

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("addSubmenu", ""))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_CREATION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    //~ Delete =========================================================================================================

    public void deleteMenuItem() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(DeleteMenuItemRequest.class), eq(DeleteMenuItemResponse.class)))
                .thenReturn(DeleteMenuItemResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("deleteMenuItem", WebMenuItemFixtures.ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_DELETION_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void deleteMenuItemWithFailure() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(DeleteMenuItemRequest.class), eq(DeleteMenuItemResponse.class)))
                .thenReturn(DeleteMenuItemResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("deleteMenuItem", WebMenuItemFixtures.ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MENU_ITEM_DELETION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void deleteSubmenu() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(DeleteSubmenuRequest.class), eq(DeleteSubmenuResponse.class)))
                .thenReturn(DeleteSubmenuResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID))
                .param("deleteSubmenu", WebSubmenuFixtures.ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_DELETION_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)));
    }

    public void deleteSubmenuWithFailure() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(DeleteSubmenuRequest.class), eq(DeleteSubmenuResponse.class)))
                .thenReturn(DeleteSubmenuResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID))
                .param("deleteSubmenu", WebSubmenuFixtures.ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.SUBMENU_DELETION_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, MenuNodeFixtures.ROOT_ID)));
    }

    //~ Move up ========================================================================================================

    public void moveUp() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(MoveMenuContentRequest.class), eq(MoveMenuContentResponse.class)))
                .thenReturn(MoveMenuContentResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("moveUp", MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void moveUpWithFailure() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(MoveMenuContentRequest.class), eq(MoveMenuContentResponse.class)))
                .thenReturn(MoveMenuContentResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("moveUp", MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    //~ Move down ======================================================================================================

    public void moveDown() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(MoveMenuContentRequest.class), eq(MoveMenuContentResponse.class)))
                .thenReturn(MoveMenuContentResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("moveDown", MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void moveDownWithFailure() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(MoveMenuContentRequest.class), eq(MoveMenuContentResponse.class)))
                .thenReturn(MoveMenuContentResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("moveDown", MenuNodeFixtures.LEAF_NODE1_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    //~ Move to upper level ============================================================================================

    public void moveToUpperLevel() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(MoveMenuContentRequest.class), eq(MoveMenuContentResponse.class)))
                .thenReturn(MoveMenuContentResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("moveToUpperLevel", MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void moveToUpperLevelWithFailure() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(MoveMenuContentRequest.class), eq(MoveMenuContentResponse.class)))
                .thenReturn(MoveMenuContentResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("moveToUpperLevel", MenuNodeFixtures.LEAF_NODE2_DEPTH2_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    //~ Move to lower level ============================================================================================

    public void moveToLowerLevel() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(MoveMenuContentRequest.class), eq(MoveMenuContentResponse.class)))
                .thenReturn(MoveMenuContentResponseFixtures.successResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("moveToLowerLevel", MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID.toString())
                .param("sibling", MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.SUCCESS))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }

    public void moveToLowerLevelWithFailure() throws Exception {
        Mockito.when(requestDispatcher.getResponse(any(MoveMenuContentRequest.class), eq(MoveMenuContentResponse.class)))
                .thenReturn(MoveMenuContentResponseFixtures.defaultResponse());

        mockMvc.perform(post(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID))
                .param("moveToLowerLevel", MenuNodeFixtures.BRANCH_NODE2_DEPTH1_ID.toString())
                .param("sibling", MenuNodeFixtures.BRANCH_NODE1_DEPTH1_ID.toString()))
                //.andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(flash().attribute(RequestAttribute.Name.MOVE_MESSAGE, RequestAttribute.Value.FAILURE))
                .andExpect(redirectedUrl(WebURLPath.getSubmenuURL(CoreMenuFixtures.ID, WebSubmenuFixtures.ID)));
    }
}
