package com.thoughtworks.universityregistration.web.infrastructure;

import java.util.HashMap;
import java.util.Map;

public class HtmlGenerator {

    private StringTemplateProcessor stringTemplateProcessor;

    public HtmlGenerator(StringTemplateProcessor stringTemplateProcessor) {
        this.stringTemplateProcessor = stringTemplateProcessor;
    }

    public String generate(ModelAndView modelAndView) {
        Map<ModelKeys, Object> model = modelAndView.getModel();
        Map<String, Object> transformedModel = null;
        if (model != null) {
            transformedModel = transformMap(model);
        }
        return stringTemplateProcessor.processTemplate(modelAndView.getViewName(), transformedModel);
    }

    private Map<String, Object> transformMap(Map<ModelKeys, Object> model) {
        Map<String, Object> transformedMap = new HashMap<String, Object>();
        for (ModelKeys key : model.keySet()) {
            transformedMap.put(key.getKey(), model.get(key));
        }
        return transformedMap;
    }

}
