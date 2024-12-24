package org.dam2.gestionfaltas.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "horas")
public class Hora {

    @Id
    @Column(name = "id_hora")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHora;

    @Column(name = "hora")
    private String hora;

    @OneToMany(mappedBy = "idHora", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Incidencia> incidencias;

    public Hora() {
    }

    public Hora(String hora) {
        this.hora = hora;
    }

    public int getIdHora() {
        return idHora;
    }

    public void setIdHora(int idHora) {
        this.idHora = idHora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    @Override
    public String toString() {
        return "Hora{" +
                "idHora=" + idHora +
                ", hora='" + hora + '\'' +
                '}';
    }
}
