package com.thoughtworks.universityregistration.framework.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends PageComponent {

    protected CourseList(WebElement webElement) {
        super(webElement);
    }

    public List<CourseListItem> getItems() {
        List<WebElement> listItems = webElement.findElements(By.tagName("li"));
        ArrayList<CourseListItem> courseListItems = new ArrayList<CourseListItem>();
        for (WebElement listItem : listItems) {
            courseListItems.add(new CourseListItem(listItem));
        }
        return courseListItems;
    }

}
