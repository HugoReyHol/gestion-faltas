package org.dam2.gestionfaltas.dao;

import org.dam2.gestionfaltas.dao.interfaces.IncidenciaDAO;
import org.dam2.gestionfaltas.model.Incidencia;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class IncidenciaDAOImpl implements IncidenciaDAO {

    @Override
    public void eliminar(int idParte) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Incidencia incidencia = session.get(Incidencia.class, idParte);
            session.delete(incidencia);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }

    @Override
    public void modificar(Incidencia incidencia) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(incidencia);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }
}
