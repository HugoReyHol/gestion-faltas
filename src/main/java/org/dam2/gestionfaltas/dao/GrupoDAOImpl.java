package org.dam2.gestionfaltas.dao;

import org.dam2.gestionfaltas.dao.interfaces.GrupoDAO;
import org.dam2.gestionfaltas.model.Grupo;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;


public class GrupoDAOImpl implements GrupoDAO {
    @Override
    public Grupo obtener(int idGrupo) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        Grupo grupo = null;

        try {
            transaction = session.beginTransaction();
            grupo = session.get(Grupo.class, idGrupo);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }

        return grupo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Grupo> listar() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Grupo> grupos = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            grupos = session.createQuery("from Grupo").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }

        return grupos;
    }

    @Override
    public void crear(Grupo grupo) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(grupo);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }

    @Override
    public void eliminar(int idGrupo) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Grupo grupo = session.get(Grupo.class, idGrupo);
            session.delete(grupo);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }

    @Override
    public void modificar(Grupo grupo) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(grupo);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }
}
