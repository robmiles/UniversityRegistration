package com.thoughtworks.universityregistration.web.controllers;

import com.thoughtworks.universityregistration.web.infrastructure.ModelAndView;

public class HomeController {
    public ModelAndView home() {
        return new ModelAndView("home");
    }
}
