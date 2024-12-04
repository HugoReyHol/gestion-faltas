package org.dam2.gestionfaltas.dao.interfaces;

import org.dam2.gestionfaltas.model.Incidencia;


public interface IncidenciaDAO {
    public void eliminar(int idParte);

    public void modificar(Incidencia incidencia);
}
