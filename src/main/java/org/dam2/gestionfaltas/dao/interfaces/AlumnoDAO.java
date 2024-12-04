package org.dam2.gestionfaltas.dao.interfaces;

import org.dam2.gestionfaltas.model.Alumno;
import java.util.List;


public interface AlumnoDAO {
    public Alumno obtener(int numeroExpediente);

    public List<Alumno> listar();

    public void eliminar(int numeroExpediente);

    public void modificar(Alumno alumno);

    public void crear(Alumno alumno);
}
