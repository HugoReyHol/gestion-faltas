package org.dam2.gestionfaltas.model;

import org.dam2.gestionfaltas.util.Color;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "puntos_partes")
public class PuntosPartes {

    @Id
    @Column(name = "id_puntos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParte;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "puntos")
    private int puntos;

    @OneToMany(mappedBy = "idPuntos", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Incidencia> incidencias;

    public PuntosPartes() {
    }

    public PuntosPartes(Color color, int puntos) {
        this.color = color;
        this.puntos = puntos;
    }

    public int getIdParte() {
        return idParte;
    }

    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    @Override
    public String toString() {
        return "PuntosPartes{" +
                "idParte=" + idParte +
                ", color='" + color + '\'' +
                ", puntos=" + puntos +
                '}';
    }
}
