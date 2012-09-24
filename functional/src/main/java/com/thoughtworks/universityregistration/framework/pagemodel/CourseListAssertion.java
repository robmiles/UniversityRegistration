package com.thoughtworks.universityregistration.framework.pagemodel;

import com.thoughtworks.universityregistration.framework.Framework;
import com.thoughtworks.universityregistration.framework.datasetup.CourseData;

import java.util.List;

import static junit.framework.Assert.fail;

public class CourseListAssertion extends Assertion<CourseList> {

    protected CourseListAssertion(CourseList component) {
        super(component);
    }

    public static CourseListAssertion courseList() {
        return new CourseListAssertion(new CourseListingPage().courseList());
    }

    public void hasCourse(String courseTag) {
        CourseData course = Framework.getItem(courseTag);
        List<CourseListItem> courseListItems = component.getItems();
        for (CourseListItem item : courseListItems) {
            if (item.getName().equals(course.getName())) {
                return;
            }
        }
        fail("Could not find a matching course with name '" + course.getName() + "'");
    }
}
