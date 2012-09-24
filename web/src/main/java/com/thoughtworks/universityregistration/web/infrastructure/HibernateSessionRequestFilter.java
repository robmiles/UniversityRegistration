package com.thoughtworks.universityregistration.web.infrastructure;

import org.hibernate.StaleObjectStateException;

import javax.servlet.*;
import java.io.IOException;

import static com.thoughtworks.universityregistration.infrastructure.hibernate.HibernateUtils.getSessionFactory;

public class HibernateSessionRequestFilter implements Filter {

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        try {
            getSessionFactory().getCurrentSession().beginTransaction();

            processWebRequest(request, response, chain);

            getSessionFactory().getCurrentSession().getTransaction().commit();
            getSessionFactory().getCurrentSession().close();

        } catch (StaleObjectStateException staleEx) {
            throw new ServletException("Failed to commit transaction due to concurrency exception", staleEx);
        } catch (Throwable ex) {
            // Rollback only
            ex.printStackTrace();
            try {
                if (getSessionFactory().getCurrentSession().getTransaction().isActive()) {
                    getSessionFactory().getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }

            throw new ServletException(ex);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private void processWebRequest(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    public void destroy() {}

}
