package org.dam2.gestionfaltas.dao.interfaces;

import org.hibernate.Session;
import org.dam2.gestionfaltas.model.Profesor;
import java.util.List;


public interface ProfesorDAO {
    public Profesor obtener(String numeroAsignado);

    public List<Profesor> listar();

    public void crear(Profesor profesor);
}
