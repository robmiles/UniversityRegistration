package com.thoughtworks.universityregistration.framework.datasetup.builders;

import com.thoughtworks.universityregistration.framework.datasetup.CourseData;

public class CourseBuilder implements Builder<CourseData> {

    private String name;

    public static CourseBuilder course() {
        return new CourseBuilder();
    }

    public CourseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CourseData build() {
        return new CourseData(name);
    }

}
