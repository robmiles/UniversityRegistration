package com.thoughtworks.universityregistration.web.infrastructure;

import java.util.Map;

public class ModelAndView {
    private final String viewName;
    private Map<ModelKeys, Object> model;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public Map<ModelKeys, Object> getModel() {
        return model;
    }

    public void setModel(Map<ModelKeys, Object> model) {
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }
}
