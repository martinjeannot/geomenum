package com.geomenum.core.requesthandlers.web.ui;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.services.menu.MenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.ui.GetMenuBreadcrumbsRequest;
import com.geomenum.web.requestsresponses.ui.GetMenuBreadcrumbsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static com.geomenum.core.misc.GUIHelper.guiHelper;

@RequestHandler
public class GetMenuBreadcrumbsRequestHandler extends AbstractRequestHandler<GetMenuBreadcrumbsRequest, GetMenuBreadcrumbsResponse> {

    @Autowired
    private MenuCoreService menuCoreService;

    @Override
    public GetMenuBreadcrumbsResponse handle(GetMenuBreadcrumbsRequest request) {
        CoreMenu menu = menuCoreService.findById(request.getMenuId());
        List<Map<String, String>> menuBreadcrumbs = guiHelper(request.getMenuNodeContentId(), menu).buildBreadcrumbNavigationBar();
        return new GetMenuBreadcrumbsResponse(menuBreadcrumbs);
    }

    @Override
    public GetMenuBreadcrumbsResponse createDefaultResponse() {
        return new GetMenuBreadcrumbsResponse(null);
    }
}
