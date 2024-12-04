package org.dam2.gestionfaltas.dao;

import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.dam2.gestionfaltas.dao.interfaces.ProfesorDAO;
import org.dam2.gestionfaltas.model.Profesor;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;


public class ProfesorDAOImpl implements ProfesorDAO {
    @Override
    public Profesor obtener(String numeroAsignado) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        Profesor profesor = null;

        try {
            transaction = session.beginTransaction();
            profesor = (Profesor) session.createQuery("from Profesor where numeroAsignado = '" + numeroAsignado + "'").list().get(0);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
        return profesor;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Profesor> listar() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Profesor> profesores = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            profesores = session.createQuery("from Profesor").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }

        return profesores;
    }

    @Override
    public void crear(Profesor profesor) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(profesor);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }
}
