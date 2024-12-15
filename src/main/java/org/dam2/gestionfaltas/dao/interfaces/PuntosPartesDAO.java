package org.dam2.gestionfaltas.dao.interfaces;

import org.dam2.gestionfaltas.model.PuntosPartes;
import org.dam2.gestionfaltas.util.Color;

import java.util.List;


public interface PuntosPartesDAO {
    public void eliminar(int idPuntos);
    public void modificar(PuntosPartes puntosPartes);

    public void crear(PuntosPartes puntosPartes);

    public List<PuntosPartes> listar();
    public PuntosPartes obtener(int idPuntos);
    public PuntosPartes obtener(Color color);
}
