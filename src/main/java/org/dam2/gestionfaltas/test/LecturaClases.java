package org.dam2.gestionfaltas.test;

import org.dam2.gestionfaltas.model.*;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LecturaClases {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSession();

        // Preuba lectura de grupos
        Transaction transaction = null;
        List<Grupo> grupos = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            grupos = session.createQuery("from Grupo").list();
            transaction.commit();
            System.out.println("Bien");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error");
            e.printStackTrace();
        }

        for (Grupo g: grupos) {
            System.out.println(g);

        }


        // Preuba lectura de alumnos
        transaction = null;
        List<Alumno> alumnos = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            alumnos = session.createQuery("from Alumno").list();
            transaction.commit();
            System.out.println("Bien");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error");
            e.printStackTrace();
        }

        for (Alumno a: alumnos) {
            System.out.println(a);

        }

        // Preuba lectura de profesores
        transaction = null;
        List<Profesor> profesores = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            profesores = session.createQuery("from Profesor").list();
            transaction.commit();
            System.out.println("Bien");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error");
            e.printStackTrace();
        }

        for (Profesor p: profesores) {
            System.out.println(p);

        }

        // Preuba lectura de incidencias
        transaction = null;
        List<Incidencia> incidencias = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            incidencias = session.createQuery("from Incidencia").list();
            transaction.commit();
            System.out.println("Bien");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error");
            e.printStackTrace();
        }

        for (Incidencia i: incidencias) {
            System.out.println(i);

        }

        // Preuba lectura de puntos_partes
        transaction = null;
        List<PuntosPartes> puntosPartes = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            puntosPartes = session.createQuery("from PuntosPartes").list();
            transaction.commit();
            System.out.println("Bien");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error");
            e.printStackTrace();
        }

        for (PuntosPartes p: puntosPartes) {
            System.out.println(p);

        }

        // Preuba lectura de horas
        transaction = null;
        List<Hora> horas = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            horas = session.createQuery("from Hora").list();
            transaction.commit();
            System.out.println("Bien");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error");
            e.printStackTrace();
        }

        for (Hora h: horas) {
            System.out.println(h);

        }

        HibernateUtil.close();

    }
}
