package com.thoughtworks.universityregistration.framework.actors;

import com.thoughtworks.universityregistration.framework.Browser;
import com.thoughtworks.universityregistration.framework.pagemodel.CourseListingPage;
import org.openqa.selenium.WebDriver;

public class Student implements Actor {

    private WebDriver browser = Browser.getInstance();

    public static Student student() {
        return new Student();
    }

    public void viewsTheCourseListingPage() {
        browser.get(CourseListingPage.getUrl());
    }
}
