package com.thoughtworks.universityregistration.web.routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Route {

    public abstract boolean matches(HttpServletRequest request);
    public abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;

    protected boolean matchesPattern(String url, String urlPattern) {
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
