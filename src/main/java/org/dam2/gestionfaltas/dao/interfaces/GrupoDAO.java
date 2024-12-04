package org.dam2.gestionfaltas.dao.interfaces;

import org.dam2.gestionfaltas.model.Grupo;
import java.util.List;

public interface GrupoDAO {
    public Grupo obtener(int idGrupo);

    public List<Grupo> listar();

    public void crear(Grupo grupo);

    public void eliminar(int idGrupo);
    public void modificar(Grupo grupo);
}
