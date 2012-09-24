package com.thoughtworks.universityregistration.web.controllers;

import com.thoughtworks.universityregistration.domain.Course;
import com.thoughtworks.universityregistration.domain.repositories.CourseRepository;
import com.thoughtworks.universityregistration.web.infrastructure.ModelAndView;
import com.thoughtworks.universityregistration.web.infrastructure.ModelKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseListController {

    private final CourseRepository courseRepository;

    public CourseListController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public ModelAndView list() {
        Map<ModelKeys, Object> model = new HashMap<ModelKeys, Object>();
        List<Course> courses = courseRepository.getAll();
        model.put(ModelKeys.COURSES, courses);
        ModelAndView modelAndView = new ModelAndView("courses");
        modelAndView.setModel(model);
        return modelAndView;
    }

}
