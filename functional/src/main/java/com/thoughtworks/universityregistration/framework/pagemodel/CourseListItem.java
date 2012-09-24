package com.thoughtworks.universityregistration.framework.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CourseListItem extends PageComponent {

    protected CourseListItem(WebElement webElement) {
        super(webElement);
    }

    public String getName() {
        return webElement.findElement(By.className("course-name")).getText();
    }
}
