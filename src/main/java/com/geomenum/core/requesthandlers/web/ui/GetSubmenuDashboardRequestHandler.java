package com.geomenum.core.requesthandlers.web.ui;

import com.geomenum.core.domainmodel.menu.CoreMenu;
import com.geomenum.core.services.menu.MenuCoreService;
import com.geomenum.r2d2.servicelayer.AbstractRequestHandler;
import com.geomenum.r2d2.spring.servicelayer.RequestHandler;
import com.geomenum.web.requestsresponses.ui.GetSubmenuDashboardRequest;
import com.geomenum.web.requestsresponses.ui.GetSubmenuDashboardResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static com.geomenum.core.misc.GUIHelper.guiHelper;

@RequestHandler
public class GetSubmenuDashboardRequestHandler extends AbstractRequestHandler<GetSubmenuDashboardRequest, GetSubmenuDashboardResponse> {

    @Autowired
    private MenuCoreService menuCoreService;

    @Override
    public GetSubmenuDashboardResponse handle(GetSubmenuDashboardRequest request) {
        CoreMenu menu = menuCoreService.findById(request.getMenuId());
        List<Map<String, Object>> submenuDashboardData = guiHelper(request.getSubmenuId(), menu).buildSubmenuDashboardInfo();
        return new GetSubmenuDashboardResponse(submenuDashboardData);
    }

    @Override
    public GetSubmenuDashboardResponse createDefaultResponse() {
        return new GetSubmenuDashboardResponse(null);
    }
}
