package com.thoughtworks.universityregistration.infrastructure.hibernate.repositories;

import com.thoughtworks.universityregistration.domain.Course;
import com.thoughtworks.universityregistration.domain.repositories.CourseRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateCourseRepository implements CourseRepository {

    private final SessionFactory sessionFactory;

    public HibernateCourseRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Course.class);
        return criteria.list();
    }
}
