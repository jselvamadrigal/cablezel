package com.josue.util;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            configuration.configure();

            StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    private static void shutdown()
    {
        if(sessionFactory != null)
        {
            sessionFactory.close();
        }
    }
}
