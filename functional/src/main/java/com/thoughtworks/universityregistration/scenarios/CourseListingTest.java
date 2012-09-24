package com.thoughtworks.universityregistration.scenarios;

import com.thoughtworks.universityregistration.framework.Scenario;
import org.junit.Test;

import static com.thoughtworks.universityregistration.framework.Framework.*;
import static com.thoughtworks.universityregistration.framework.actors.Student.student;
import static com.thoughtworks.universityregistration.framework.datasetup.DataTag.taggedAs;
import static com.thoughtworks.universityregistration.framework.datasetup.builders.CourseBuilder.course;
import static com.thoughtworks.universityregistration.framework.pagemodel.CourseListAssertion.courseList;

public class CourseListingTest extends Scenario {

    @Test
    public void shouldHaveACourse() throws Exception {
        given(course().withName("Computer Science 101"), taggedAs("Comp Sci course"));

        when(student()).viewsTheCourseListingPage();

        then(courseList()).hasCourse("Comp Sci course");
    }
}
