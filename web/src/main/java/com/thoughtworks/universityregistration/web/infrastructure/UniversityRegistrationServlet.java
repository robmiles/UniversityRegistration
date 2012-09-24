package com.thoughtworks.universityregistration.web.infrastructure;

import com.thoughtworks.universityregistration.domain.repositories.CourseRepository;
import com.thoughtworks.universityregistration.infrastructure.hibernate.repositories.HibernateCourseRepository;
import com.thoughtworks.universityregistration.web.controllers.CourseListController;
import com.thoughtworks.universityregistration.web.controllers.HomeController;
import com.thoughtworks.universityregistration.web.routes.CourseListRoute;
import com.thoughtworks.universityregistration.web.routes.HomeRoute;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.thoughtworks.universityregistration.infrastructure.hibernate.HibernateUtils.getSessionFactory;

public class UniversityRegistrationServlet extends HttpServlet {

    private Router router;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String pathToTemplatesFolder = config.getServletContext().getRealPath("/WEB-INF/templates");
        StringTemplateProcessor stringTemplateProcessor = new StringTemplateProcessor(pathToTemplatesFolder);
        HtmlGenerator htmlGenerator = new HtmlGenerator(stringTemplateProcessor);
        ViewRenderer viewRenderer = new ViewRenderer(htmlGenerator);
        CourseRepository courseRepository = new HibernateCourseRepository(getSessionFactory());

        router = new Router();
        router.add(new HomeRoute(new HomeController(), viewRenderer));
        router.add(new CourseListRoute(new CourseListController(courseRepository), viewRenderer));

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        router.routeRequest(req, resp);
    }
}