package com.thoughtworks.universityregistration.framework.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PageComponent {
    protected WebElement webElement;

    protected PageComponent(WebElement webElement) {
        this.webElement = webElement;
    }

    protected boolean hasClass(String expectedClassName) {
        String classAttribute = webElement.getAttribute("class");
        return classAttribute != null && classAttribute.contains(expectedClassName);
    }

    protected boolean hasElement(By by) {
        try {
            webElement.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
