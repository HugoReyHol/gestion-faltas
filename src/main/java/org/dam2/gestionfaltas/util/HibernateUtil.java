package org.dam2.gestionfaltas.util;

import org.dam2.gestionfaltas.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    static SessionFactory factory;
    static Session session;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("configuration/hibernate.cfg.xml");

        // Se registran las clases que hay que MAPEAR con cada tabla de la base de datos
        // Models
        cfg.addAnnotatedClass(Alumno.class);
        cfg.addAnnotatedClass(Grupo.class);
        cfg.addAnnotatedClass(Profesor.class);
        cfg.addAnnotatedClass(PuntosPartes.class);
        cfg.addAnnotatedClass(Incidencia.class);
        cfg.addAnnotatedClass(Hora.class);

        try {
            factory = cfg.buildSessionFactory();
            session = factory.openSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
    	return session;
    }

    public static void close() {
        System.out.println("Cerrando sesion");
        if (session != null) session.close();
        if (factory != null) factory.close();

    }
}
