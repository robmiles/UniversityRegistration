package com.thoughtworks.universityregistration.framework;

import org.openqa.selenium.WebDriver;

public class Browser {

    private static InProcHtmlUnitDriver inProcessDriver;

    public static WebDriver getInstance() {
        if (inProcessDriver == null) {
            inProcessDriver = new InProcHtmlUnitDriver();
            inProcessDriver.setJavascriptEnabled(true);
        }
        return inProcessDriver;
    }

}
