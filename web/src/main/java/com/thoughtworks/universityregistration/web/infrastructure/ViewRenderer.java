package com.thoughtworks.universityregistration.web.infrastructure;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ViewRenderer {
    private final HtmlGenerator htmlGenerator;

    public ViewRenderer(HtmlGenerator htmlGenerator) {
        this.htmlGenerator = htmlGenerator;
    }

    public void renderView(HttpServletResponse response, ModelAndView modelAndView) throws IOException {
        String html = htmlGenerator.generate(modelAndView);
        PrintWriter printWriter = response.getWriter();
        printWriter.println(html);
        printWriter.close();
    }
}
