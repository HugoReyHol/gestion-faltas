package org.dam2.gestionfaltas.dao;

import org.dam2.gestionfaltas.dao.interfaces.PuntosPartesDAO;
import org.dam2.gestionfaltas.model.Incidencia;
import org.dam2.gestionfaltas.model.PuntosPartes;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PuntosPartesDAOImpl implements PuntosPartesDAO {
    @Override
    public void eliminar(int idPuntos) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            PuntosPartes puntosPartes = session.get(PuntosPartes.class, idPuntos);
            session.delete(puntosPartes);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }

    @Override
    public void modificar(PuntosPartes puntosPartes) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(puntosPartes);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }

    @Override
    public void crear(PuntosPartes puntosPartes) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(puntosPartes);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }

    @Override
    public List<PuntosPartes> listar() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<PuntosPartes> puntosPartes = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            puntosPartes = session.createQuery("from PuntosPartes ").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }

        return puntosPartes;
    }

    @Override
    public PuntosPartes obtener(int idPuntos) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        PuntosPartes puntosPartes = null;

        try {
            transaction = session.beginTransaction();
            puntosPartes = (PuntosPartes) session.createQuery("from PuntosPartes where idPuntos = " + idPuntos).list().get(0);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }

        return puntosPartes;
    }
}
