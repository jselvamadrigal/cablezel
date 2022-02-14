package com.josue.dao;

import com.josue.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDao {
    private static GenericDao genericDao = null;

    private final SessionFactory factory;
    private Transaction transaccion;

    public GenericDao()
    {
        factory = HibernateUtil.getSessionFactory();
    }

    public static GenericDao getInstance()
    {
        if (genericDao == null)
        {
            genericDao = new GenericDao();
        }
        return genericDao;
    }

    public void insertar(Object o)
    {
        try (Session session = factory.openSession()) {
            transaccion = session.beginTransaction();
            session.save(o);
            transaccion.commit();
        } catch (Exception e) {
            transaccion.rollback();
            e.printStackTrace();
        }
    }

    public boolean eliminar(Object o)
    {
        boolean retorno = false;
        try (Session session = factory.openSession()) {
            transaccion = session.beginTransaction();
            session.delete(o);
            transaccion.commit();
            retorno = true;
        } catch (Exception e) {
            transaccion.rollback();
            e.printStackTrace();
        }
        return retorno;

    }

}
