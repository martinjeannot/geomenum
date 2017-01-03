/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.menu;

import com.geomenum.web.Messages;
import com.geomenum.web.RequestAttribute;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.domainmodel.menu.NewMenuItem;
import com.geomenum.web.domainmodel.menu.NewSubmenu;
import com.geomenum.web.requestsresponses.menu.MoveMenuContentRequest;
import com.geomenum.web.requestsresponses.menu.MoveMenuContentResponse;
import com.geomenum.web.requestsresponses.menu.menuitem.AddMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.AddMenuItemResponse;
import com.geomenum.web.requestsresponses.menu.menuitem.DeleteMenuItemRequest;
import com.geomenum.web.requestsresponses.menu.menuitem.DeleteMenuItemResponse;
import com.geomenum.web.requestsresponses.menu.submenu.AddSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.AddSubmenuResponse;
import com.geomenum.web.requestsresponses.menu.submenu.DeleteSubmenuRequest;
import com.geomenum.web.requestsresponses.menu.submenu.DeleteSubmenuResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.geomenum.web.controllers.menu.SubmenuQueryWebController.newMenuItemBackingBeanName;
import static com.geomenum.web.controllers.menu.SubmenuQueryWebController.newSubmenuBackingBeanName;

/**
 * Submenu Dashboard Controller, used to implement the following use cases :
 * - add a submenu or a menu item
 * - delete a submenu or a menu item
 * - move a submenu or a menu item
 * - ...
 */
@WebController
@RequestMapping(value = WebURLPath.SUBMENU_ROOT, method = RequestMethod.POST)
@SessionAttributes({newSubmenuBackingBeanName, newMenuItemBackingBeanName})
public class SubmenuDashboardWebController extends AbstractWebController {

    //~ Create =========================================================================================================

    @RequestMapping(params = "addMenuItem")
    public String addMenuItem(@PathVariable("menuId") String menuId,
                              @PathVariable("submenuId") String parentId,
                              @Valid @ModelAttribute(newMenuItemBackingBeanName) NewMenuItem newMenuItem,
                              RedirectAttributes redirectAttributes) {
        AddMenuItemResponse response = createRequestDispatcher().getResponse(
                new AddMenuItemRequest(menuId, parentId, newMenuItem),
                AddMenuItemResponse.class);
        if(response.hasExceptionOccurred()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.MenuItem.ERROR_ON_CREATE);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.MenuItem.SUCCESS_ON_CREATE);
        }
        return redirect(WebURLPath.getSubmenuURL(menuId, parentId));
    }

    @RequestMapping(params = "addSubmenu")
    public String addSubmenu(@PathVariable("menuId") String menuId,
                             @PathVariable("submenuId") String parentId,
                             @Valid @ModelAttribute(newSubmenuBackingBeanName) NewSubmenu newSubmenu,
                             RedirectAttributes redirectAttributes) {
        AddSubmenuResponse response = createRequestDispatcher().getResponse(
                new AddSubmenuRequest(menuId, parentId, newSubmenu),
                AddSubmenuResponse.class);
        if(response.hasExceptionOccurred()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.ERROR_ON_CREATE);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.SUCCESS_ON_CREATE);
        }
        return redirect(WebURLPath.getSubmenuURL(menuId, parentId));
    }

    //~ Delete =========================================================================================================

    @RequestMapping(params = "deleteMenuItem")
    public String deleteMenuItem(@PathVariable("menuId") String menuId,
                                 @PathVariable("submenuId") String parentId,
                                 @RequestParam("deleteMenuItem") String menuItemId,
                                 RedirectAttributes redirectAttributes) {
        DeleteMenuItemResponse response = createRequestDispatcher().getResponse(
                new DeleteMenuItemRequest(menuId, menuItemId),
                DeleteMenuItemResponse.class);
        if(response.hasExceptionOccurred()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.MenuItem.ERROR_ON_DELETE);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.MenuItem.SUCCESS_ON_DELETE);
        }
        return redirect(WebURLPath.getSubmenuURL(menuId, parentId));
    }

    @RequestMapping(params = "deleteSubmenu")
    public String deleteSubmenu(@PathVariable("menuId") String menuId,
                                @PathVariable("submenuId") String parentId,
                                @RequestParam("deleteSubmenu") String submenuId,
                                RedirectAttributes redirectAttributes) {
        DeleteSubmenuResponse response = createRequestDispatcher().getResponse(
                new DeleteSubmenuRequest(menuId, submenuId),
                DeleteSubmenuResponse.class);
        if(response.hasExceptionOccurred()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.ERROR_ON_DELETE);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.SUCCESS_ON_DELETE);
        }
        return redirect(WebURLPath.getSubmenuURL(menuId, parentId));
    }

    //~ Move ===========================================================================================================

    /*
     * NOTE :
     * Since at the moment, any "move" code is similar to MenuItem and Submenu, we'll be using common
     * requests to perform "move" operations. This may change in the future if one day, a distinction is
     * made between moving a submenu or a menu item.
     */

    @RequestMapping(params = "moveUp")
    public String moveUpMenuContent(@PathVariable("menuId") String menuId,
                                    @PathVariable("submenuId") String parentId,
                                    @RequestParam("moveUp") String childId,
                                    RedirectAttributes redirectAttributes) {
        MoveMenuContentResponse response = createRequestDispatcher().getResponse(
                new MoveMenuContentRequest(menuId, childId, MoveMenuContentRequest.Direction.UP),
                MoveMenuContentResponse.class);
        if(!response.hasExceptionOccurred() && response.isMoveSuccessful()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.SUCCESS_ON_MOVE);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.ERROR_ON_MOVE);
        }
        return redirect(WebURLPath.getSubmenuURL(menuId, parentId));
    }

    @RequestMapping(params = "moveDown")
    public String moveDownMenuContent(@PathVariable("menuId") String menuId,
                                      @PathVariable("submenuId") String parentId,
                                      @RequestParam("moveDown") String childId,
                                      RedirectAttributes redirectAttributes) {

        MoveMenuContentResponse response = createRequestDispatcher().getResponse(
                new MoveMenuContentRequest(menuId, childId, MoveMenuContentRequest.Direction.DOWN),
                MoveMenuContentResponse.class);
        if(!response.hasExceptionOccurred() && response.isMoveSuccessful()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.SUCCESS_ON_MOVE);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.ERROR_ON_MOVE);
        }
        return redirect(WebURLPath.getSubmenuURL(menuId, parentId));
    }

    @RequestMapping(params = "moveToUpperLevel")
    public String moveMenuContentToUpperLevel(@PathVariable("menuId") String menuId,
                                              @PathVariable("submenuId") String parentId,
                                              @RequestParam("moveToUpperLevel") String childId,
                                              RedirectAttributes redirectAttributes) {
        MoveMenuContentResponse response = createRequestDispatcher().getResponse(
                new MoveMenuContentRequest(menuId, childId, MoveMenuContentRequest.Direction.UPPER_LEVEL),
                MoveMenuContentResponse.class);
        if(!response.hasExceptionOccurred() && response.isMoveSuccessful()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.SUCCESS_ON_MOVE);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.ERROR_ON_MOVE);
        }
        return redirect(WebURLPath.getSubmenuURL(menuId, parentId));
    }

    @RequestMapping(params = {"moveToLowerLevel", "sibling"})
    public String moveMenuContentToLowerLevel(@PathVariable("menuId") String menuId,
                                              @PathVariable("submenuId") String parentId,
                                              @RequestParam("moveToLowerLevel") String childId,
                                              @RequestParam("sibling") String siblingId,
                                              RedirectAttributes redirectAttributes) {
        MoveMenuContentResponse response = createRequestDispatcher().getResponse(
                new MoveMenuContentRequest(menuId, childId, siblingId),
                MoveMenuContentResponse.class);
        if(!response.hasExceptionOccurred() && response.isMoveSuccessful()) {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.SUCCESS_ON_MOVE);
        } else {
            redirectAttributes.addFlashAttribute(RequestAttribute.MESSAGE, Messages.Submenu.ERROR_ON_MOVE);
        }
        return redirect(WebURLPath.getSubmenuURL(menuId, parentId));
    }
}
