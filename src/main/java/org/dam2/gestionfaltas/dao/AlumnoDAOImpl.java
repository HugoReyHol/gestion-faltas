package org.dam2.gestionfaltas.dao;

import org.dam2.gestionfaltas.dao.interfaces.AlumnoDAO;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;


public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public Alumno obtener(int numeroExpediente) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        Alumno alumno = null;

        try {
            transaction = session.beginTransaction();
            alumno = (Alumno) session.createQuery("from Alumno where numero_expediente = " + numeroExpediente).list().getFirst();
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
        return alumno;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Alumno> listar() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Alumno> alumnos = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            alumnos = session.createQuery("from Alumno").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }

        return alumnos;
    }
}
