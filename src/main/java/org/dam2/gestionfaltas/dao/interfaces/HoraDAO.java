package org.dam2.gestionfaltas.dao.interfaces;

import org.dam2.gestionfaltas.model.Hora;

import java.util.List;

public interface HoraDAO {
    public Hora obtener(int idHora);
    public Hora obtener(String hora);
    public List<Hora> listar();

}
