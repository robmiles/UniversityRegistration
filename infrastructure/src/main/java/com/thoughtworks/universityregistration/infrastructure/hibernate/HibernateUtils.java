package com.thoughtworks.universityregistration.infrastructure.hibernate;

import com.thoughtworks.universityregistration.domain.Course;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized(HibernateUtils.class) {
                if (sessionFactory == null) {
                    sessionFactory = createSessionFactory();
                }
            }
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {

        Configuration cfg = getConfiguration();

        final ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                cfg.getProperties()).buildServiceRegistry();

        cfg.setSessionFactoryObserver(new SessionFactoryObserver() {

            @Override
            public void sessionFactoryCreated(SessionFactory factory) { }

            @Override
            public void sessionFactoryClosed(SessionFactory factory) {
                ServiceRegistryBuilder.destroy(serviceRegistry);
            }
        });

        return cfg.buildSessionFactory(serviceRegistry);

    }

    private static Configuration getConfiguration() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Course.class);

        String connectionUrl = String.format("jdbc:mysql://localhost/UniversityRegistration");

        cfg.setProperty("hibernate.connection.username", "unireg");
        cfg.setProperty("hibernate.connection.password", "unireg");
        cfg.setProperty("hibernate.connection.url", connectionUrl);

        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.show_sql", "false");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfg.setProperty("hibernate.connection.autocommit", "false");
        cfg.setProperty("hibernate.c3p0.min_size", "5");
        cfg.setProperty("hibernate.c3p0.max_size", "20");
        cfg.setProperty("hibernate.c3p0.timeout", "1800");
        cfg.setProperty("hibernate.c3p0.max_statements", "50");
        cfg.setProperty("hibernate.hbm2ddl.auto", "validate");
        cfg.setProperty("hibernate.cache.provider_class",
                "org.hibernate.cache.NoCacheProvider");
        cfg.setProperty("hibernate.transaction.factory_class",
                "org.hibernate.transaction.JDBCTransactionFactory");
        cfg.setProperty("hibernate.current_session_context_class", "thread");
        cfg.setProperty("hibernate.generate_statistics", "true");
        return cfg;
    }

}
