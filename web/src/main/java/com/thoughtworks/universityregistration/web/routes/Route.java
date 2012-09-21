package com.thoughtworks.universityregistration.web.routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Route {
    public boolean matches(HttpServletRequest request);
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
