package org.dam2.gestionfaltas.dao.interfaces;

import org.dam2.gestionfaltas.model.Incidencia;
import java.util.List;


public interface IncidenciaDAO {
    public void eliminar(int idParte);

    public void modificar(Incidencia incidencia);

    public void crear(Incidencia incidencia);

    public List<Incidencia> listar();

    public List<Incidencia> listarIncidencia(int inicio, int cantidad);

    public Incidencia obtener(int idParte);

    public long contar();
}
