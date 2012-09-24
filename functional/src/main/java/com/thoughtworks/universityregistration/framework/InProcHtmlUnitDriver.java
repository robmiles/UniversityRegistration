package com.thoughtworks.universityregistration.framework;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebConnection;
import com.thoughtworks.inproctester.htmlunit.InProcessWebConnection;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class InProcHtmlUnitDriver extends HtmlUnitDriver {

    private final WebConnection inProcessWebConnection;

    public InProcHtmlUnitDriver() {
        super(BrowserVersion.FIREFOX_3_6); //Default is IE7 which causes bugs with jQuery
        inProcessWebConnection = new InProcessWebConnection(InProcessConnectionFactory.getConnection(), getWebClient().getCookieManager());
        getWebClient().setWebConnection(inProcessWebConnection);
        getWebClient().setAjaxController(new NicelyResynchronizingAjaxController());
    }

}

