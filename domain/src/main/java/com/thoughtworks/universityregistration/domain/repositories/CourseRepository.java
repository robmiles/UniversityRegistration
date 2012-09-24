package com.thoughtworks.universityregistration.domain.repositories;

import com.thoughtworks.universityregistration.domain.Course;

import java.util.List;

public interface CourseRepository {
    public List<Course> getAll();
}
