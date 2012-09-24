package com.thoughtworks.universityregistration.web.routes;

import com.thoughtworks.universityregistration.web.controllers.CourseListController;
import com.thoughtworks.universityregistration.web.infrastructure.ModelAndView;
import com.thoughtworks.universityregistration.web.infrastructure.ViewRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseListRouteTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private CourseListController courseListController;
    @Mock private ViewRenderer viewRenderer;

    @Test
    public void shouldMatchValidUrl() throws Exception {
        CourseListRoute courseListRoute = new CourseListRoute(courseListController, viewRenderer);
        when(request.getRequestURI()).thenReturn("/courses");

        assertThat(courseListRoute.matches(request), is(true));
    }

    @Test
    public void shouldNotMatchInvalidUrl() throws Exception {
        CourseListRoute courseListRoute = new CourseListRoute(courseListController, viewRenderer);
        when(request.getRequestURI()).thenReturn("/invalid");

        assertThat(courseListRoute.matches(request), is(false));
    }

    @Test
    public void shouldRouteToCourseListController() throws Exception {
        CourseListRoute courseListRoute = new CourseListRoute(courseListController, viewRenderer);
        ModelAndView modelAndView = new ModelAndView(null);
        when(courseListController.list()).thenReturn(modelAndView);

        courseListRoute.processRequest(request, response);

        verify(viewRenderer).renderView(response, modelAndView);

    }
}
