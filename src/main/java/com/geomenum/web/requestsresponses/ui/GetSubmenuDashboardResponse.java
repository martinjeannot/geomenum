package com.geomenum.web.requestsresponses.ui;

import com.geomenum.r2d2.common.Response;

import java.util.List;
import java.util.Map;

/**
 * @see GetSubmenuDashboardRequest
 */
public class GetSubmenuDashboardResponse extends Response {

    private List<Map<String, Object>> submenuDashboardData;

    public GetSubmenuDashboardResponse(List<Map<String, Object>> submenuDashboardData) {
        this.submenuDashboardData = submenuDashboardData;
    }

    public List<Map<String, Object>> getSubmenuDashboardData() {
        return submenuDashboardData;
    }

    public void setSubmenuDashboardData(List<Map<String, Object>> submenuDashboardData) {
        this.submenuDashboardData = submenuDashboardData;
    }
}
