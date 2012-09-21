package com.thoughtworks.universityregistration.web.routes;

import com.thoughtworks.universityregistration.web.infrastructure.ModelAndView;
import com.thoughtworks.universityregistration.web.infrastructure.ViewRenderer;
import com.thoughtworks.universityregistration.web.controllers.HomeController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeRoute implements Route {

    private final HomeController controller;
    private final ViewRenderer viewRenderer;

    public HomeRoute(HomeController controller, ViewRenderer viewRenderer) {
        this.controller = controller;
        this.viewRenderer = viewRenderer;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return true;
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = controller.home();
        viewRenderer.renderView(response, modelAndView);
    }
}
