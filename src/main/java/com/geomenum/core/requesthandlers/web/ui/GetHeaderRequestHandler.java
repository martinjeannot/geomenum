/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.requesthandlers.web.ui;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.domainmodel.restaurant.CoreRestaurant;
import com.geomenum.core.domainmodel.system.CoreUser;
import com.geomenum.core.services.menu.MenuCoreService;
import com.geomenum.core.services.restaurant.RestaurantCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.ui.GetHeaderRequest;
import com.geomenum.web.requestsresponses.ui.GetHeaderResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

// TODO Refactor to optimize DB request instead of retrieving whole restaurants and whole menus !
// Eventually, it's ok to inject a persistence (user) service here, since we're only retrieving data upon authentication
@RequestHandler
public class GetHeaderRequestHandler extends AbstractRequestHandler<GetHeaderRequest, GetHeaderResponse> {

    @Autowired
    private RestaurantCoreService restaurantCoreService;

    @Autowired
    private MenuCoreService menuCoreService;

    @Override
    public GetHeaderResponse handle(GetHeaderRequest request) {
        // for a single restaurant here
        CoreUser user = (CoreUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CoreRestaurant restaurant = restaurantCoreService.findById(user.getRestaurantId());
        CoreMenu menu = menuCoreService.findById(restaurant.getMenuId());
        Map<String, String> headerData = Maps.newHashMap();
        headerData.put("restaurantName", restaurant.getName());
        headerData.put("restaurantId", restaurant.getId());
        headerData.put("menuId", restaurant.getMenuId());
        headerData.put("menuRootId", menu.getRoot().getContent().getId().toString());
        return new GetHeaderResponse(user.getId(), Lists.newArrayList(headerData));
    }

    @Override
    public GetHeaderResponse createDefaultResponse() {
        return new GetHeaderResponse(null, null);
    }
}
