package org.dam2.gestionfaltas.util;

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

        // Meter models
        //cfg.addAnnotatedClass();

        //configuration.addAnnotatedClass(Clase1.class);

        factory = cfg.buildSessionFactory();
        session = factory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
    	return session;
    }

    public static void close() {
        System.out.println("Cerrando sesion");
        factory.close();
        session.close();

    }
}
