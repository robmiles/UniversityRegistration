package com.thoughtworks.universityregistration.web.infrastructure;

import com.thoughtworks.universityregistration.web.routes.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Router {
    private List<Route> routes = new ArrayList<Route>();

    public void add(Route route) {
        routes.add(route);
    }

    public void routeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        for (Route route : routes) {
            if (route.matches(request)) {
                route.processRequest(request, response);
                response.setStatus(200);
                return;
            }
        }
        response.setStatus(404);
    }

}
