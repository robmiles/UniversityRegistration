package com.thoughtworks.universityregistration.web.infrastructure;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import java.util.Map;

public class StringTemplateProcessor {

    private final String pathToTemplatesFolder;

    public StringTemplateProcessor(String pathToTemplatesFolder) {
        this.pathToTemplatesFolder = pathToTemplatesFolder;
    }

    public String processTemplate(String viewName, Map<String, Object> model) {
        StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("stringTemplateGroupName", pathToTemplatesFolder);
        StringTemplate stringTemplate = stringTemplateGroup.getInstanceOf(viewName, model);
        return stringTemplate.toString();
    }
}
