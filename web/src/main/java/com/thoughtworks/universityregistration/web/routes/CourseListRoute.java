package com.thoughtworks.universityregistration.web.routes;

import com.thoughtworks.universityregistration.web.controllers.CourseListController;
import com.thoughtworks.universityregistration.web.infrastructure.ModelAndView;
import com.thoughtworks.universityregistration.web.infrastructure.ViewRenderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CourseListRoute extends Route {

    public static final String URL_PATTERN = "/courses$";
    private final CourseListController controller;
    private final ViewRenderer viewRenderer;

    public CourseListRoute(CourseListController controller, ViewRenderer viewRenderer) {
        this.controller = controller;
        this.viewRenderer = viewRenderer;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return matchesPattern(request.getRequestURI(), URL_PATTERN);
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = controller.list();
        viewRenderer.renderView(response, modelAndView);
    }
}
