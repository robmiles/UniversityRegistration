package com.thoughtworks.universityregistration.web.routes;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeRouteTest {

    @Test
    public void shouldMatchValidUrl() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HomeRoute homeRoute = new HomeRoute(null, null);
        when(request.getRequestURI()).thenReturn("/");

        assertThat(homeRoute.matches(request), is(true));
    }

    @Test
    public void shouldNotMatchInvalidUrl() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HomeRoute homeRoute = new HomeRoute(null, null);
        when(request.getRequestURI()).thenReturn("/invalid");

        assertThat(homeRoute.matches(request), is(false));
    }
}