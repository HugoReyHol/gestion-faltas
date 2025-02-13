package org.dam2.gestionfaltas.test;

import org.dam2.gestionfaltas.dao.AlumnoDAOImpl;
import org.dam2.gestionfaltas.dao.HoraDAOImpl;
import org.dam2.gestionfaltas.dao.ProfesorDAOImpl;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.model.Profesor;

public class DAOs {
    public static void main(String[] args) {

        ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();

        Profesor pBuscado = profesorDAO.obtener("1001");

        System.out.println(pBuscado);

        AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl();

        Alumno aBuscado = alumnoDAO.obtener(1001);
        System.out.println(aBuscado);

        System.out.println(alumnoDAO.contar());

        System.out.println("Prueba sin limitar");
        System.out.println(alumnoDAO.listar());
        System.out.println("Prueba limitando");
        System.out.println(alumnoDAO.listar(2, 5));

        HoraDAOImpl horaDAO = new HoraDAOImpl();

        System.out.println(horaDAO.obtener(1));
        System.out.println(horaDAO.listar());

    }
}
