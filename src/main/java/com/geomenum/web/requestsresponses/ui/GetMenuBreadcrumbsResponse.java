package com.geomenum.web.requestsresponses.ui;

import com.geomenum.r2d2.common.Response;

import java.util.List;
import java.util.Map;

/**
 * @see GetMenuBreadcrumbsRequest
 */
public class GetMenuBreadcrumbsResponse extends Response {

    private List<Map<String, String>> breadcrumbNavigationBarData;

    public GetMenuBreadcrumbsResponse(List<Map<String, String>> breadcrumbNavigationBarData) {
        this.breadcrumbNavigationBarData = breadcrumbNavigationBarData;
    }

    public List<Map<String, String>> getBreadcrumbNavigationBarData() {
        return breadcrumbNavigationBarData;
    }

    public void setBreadcrumbNavigationBarData(List<Map<String, String>> breadcrumbNavigationBarData) {
        this.breadcrumbNavigationBarData = breadcrumbNavigationBarData;
    }
}
