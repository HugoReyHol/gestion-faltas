package org.dam2.gestionfaltas.test;

import org.dam2.gestionfaltas.dao.AlumnoDAOImpl;
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


    }
}
