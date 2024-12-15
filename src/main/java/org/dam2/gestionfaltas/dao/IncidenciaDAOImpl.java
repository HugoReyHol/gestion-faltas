package org.dam2.gestionfaltas.dao;

import org.dam2.gestionfaltas.dao.interfaces.IncidenciaDAO;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.model.Incidencia;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void crear(Incidencia incidencia) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(incidencia);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Incidencia> listar() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Incidencia> incidencias = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            incidencias = session.createQuery("from Incidencia ").list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }

        return incidencias;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Incidencia> listarIncidencia(int inicio, int cantidad) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Incidencia> incidencias = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            incidencias = session.createQuery("from Incidencia")
                    .setFirstResult(inicio)
                    .setMaxResults(cantidad)
                    .list();;
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        }

        return incidencias;
    }

    @Override
    public Incidencia obtener(int idParte) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        Incidencia incidencia = null;

        try {
            transaction = session.beginTransaction();
            incidencia = (Incidencia) session.createQuery("from Incidencia where idParte = " + idParte).list().get(0);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }

        return incidencia;
    }

    @Override
    public long contar() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        long cantidad = 0;

        try {
            transaction = session.beginTransaction();
            cantidad = session.createQuery("SELECT COUNT(*) FROM Incidencia", Long.class).uniqueResult();
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();

        }
        return cantidad;
    }

    public List<Incidencia> listarPorAlumno(Alumno alumno) {
        Session session = HibernateUtil.getSession(); // OBTENER LA SESION
        Transaction transaction = null; // INICIAR TRANSACCION
        List<Incidencia> incidencias = new ArrayList<>(); // CREAR LISTA DE INCIDENCIAS

        try {
            transaction = session.beginTransaction(); // INCIAR TRNASACCION
            String hql = "FROM Incidencia WHERE idAlumno = :alumno ORDER BY fecha DESC";
            Query<Incidencia> query = session.createQuery(hql, Incidencia.class);
            query.setParameter("alumno", alumno);
            incidencias = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // SI HAY UN ERROR SE DESHACE LA TRANSACCION
            e.printStackTrace();
        }
        return incidencias; // DEVOLVER LA LISTA
    } // METODO APRA LISTAR LAS INCIDENCIAS POR ALUMNO


    public List<Incidencia> listarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        Session session = HibernateUtil.getSession(); // OBTENER LA SESION
        Transaction transaction = null; // INICIAR TRANSACCION
        List<Incidencia> incidencias = new ArrayList<>(); // CREAR LISTA DE INCIDENCIAS

        try {
            transaction = session.beginTransaction();
            String hql = "FROM Incidencia WHERE fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY fecha DESC";
            Query<Incidencia> query = session.createQuery(hql, Incidencia.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            incidencias = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // SI HAY UN ERROR SE DESHACE LA TRANSACCION
            e.printStackTrace();
        }
        return incidencias; // DEVOLVER LA LISTA
    } // METODO PARA LISTAR INCIDENCIAS POR RANGO DE FECHAS
}
