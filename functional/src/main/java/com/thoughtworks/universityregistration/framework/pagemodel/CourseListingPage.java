package com.thoughtworks.universityregistration.framework.pagemodel;

import com.thoughtworks.universityregistration.framework.Browser;
import org.openqa.selenium.By;

public class CourseListingPage extends PageComponent {

    public CourseListingPage() {
        super(Browser.getInstance().findElement(By.tagName("body")));
    }

    public CourseList courseList() {
        return new CourseList(webElement.findElement(By.id("course-list")));
    }

    public static String getUrl() {
        return "http://localhost/courses";
    }
}
