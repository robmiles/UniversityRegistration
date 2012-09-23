package com.thoughtworks.universityregistration.scenarios;

import com.thoughtworks.universityregistration.framework.Scenario;
import com.thoughtworks.universityregistration.framework.datasetup.DataWriter;
import org.junit.Test;

import static com.thoughtworks.universityregistration.framework.Framework.given;
import static com.thoughtworks.universityregistration.framework.datasetup.builders.CourseBuilder.course;

public class CourseListingTest extends Scenario {

    @Test
    public void shouldHaveACourse() throws Exception {
        given(course().withName("Computer Science 101"));

        DataWriter.persistData();
    }
}
