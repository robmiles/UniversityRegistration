package com.thoughtworks.universityregistration.web.infrastructure;

import com.thoughtworks.universityregistration.web.controllers.HomeController;
import com.thoughtworks.universityregistration.web.routes.HomeRoute;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UniversityRegistrationServlet extends HttpServlet {

    private HtmlGenerator htmlGenerator;
    private ViewRenderer viewRenderer;
    private Router router;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String pathToTemplatesFolder = config.getServletContext().getRealPath("/WEB-INF/templates");
        StringTemplateProcessor stringTemplateProcessor = new StringTemplateProcessor(pathToTemplatesFolder);
        htmlGenerator = new HtmlGenerator(stringTemplateProcessor);
        viewRenderer = new ViewRenderer(htmlGenerator);

        router = new Router();
        router.add(new HomeRoute(new HomeController(), viewRenderer));

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        router.routeRequest(req, resp);
    }
}