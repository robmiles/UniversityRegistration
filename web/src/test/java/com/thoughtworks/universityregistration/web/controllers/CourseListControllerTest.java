package com.thoughtworks.universityregistration.web.controllers;

import com.thoughtworks.universityregistration.domain.Course;
import com.thoughtworks.universityregistration.domain.repositories.CourseRepository;
import com.thoughtworks.universityregistration.web.infrastructure.ModelAndView;
import com.thoughtworks.universityregistration.web.infrastructure.ModelKeys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseListControllerTest {

    @Mock private CourseRepository courseRepository;

    @SuppressWarnings("unchecked")
    @Test
    public void shouldAddListOfCoursesToTheView() throws Exception {
        List<Course> courses = asList(new Course("test course"));
        when(courseRepository.getAll()).thenReturn(courses);
        CourseListController controller = new CourseListController(courseRepository);

        ModelAndView modelAndView = controller.list();

        assertThat(modelAndView.getViewName(), is("courses"));
        assertThat((List<Course>) modelAndView.getModel().get(ModelKeys.COURSES), is(courses));
    }
}
