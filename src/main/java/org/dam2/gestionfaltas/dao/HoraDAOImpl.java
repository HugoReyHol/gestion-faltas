package org.dam2.gestionfaltas.dao;

import org.dam2.gestionfaltas.dao.interfaces.HoraDAO;
import org.dam2.gestionfaltas.model.Hora;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class HoraDAOImpl implements HoraDAO {
    @Override
    public Hora obtener(int idHora) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        Hora hora = null;

        try {
            transaction = session.beginTransaction();
            hora = (Hora) session.createQuery("from Hora where idHora = " + idHora).list().get(0);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }

        return hora;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hora> listar() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Hora> horas = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            horas = session.createQuery("from Hora").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }

        return horas;
    }
}
