package org.dam2.gestionfaltas.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "partes_incidencia")
public class Incidencia {

    @Id
    @Column(name = "id_parte")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParte;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "sancion")
    private String sancion;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_alum", referencedColumnName = "id_alum")
    private Alumno idAlumno;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    private Profesor idProfesor;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_puntos", referencedColumnName = "id_puntos")
    private PuntosPartes idPuntos;

    public Incidencia() {
    }

    public Incidencia(String descripcion, LocalDate fecha, String hora, String sancion) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.sancion = sancion;
    }

    public int getIdParte() {
        return idParte;
    }

    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    public PuntosPartes getIdPuntos() {
        return idPuntos;
    }

    public void setIdPuntos(PuntosPartes idPuntos) {
        this.idPuntos = idPuntos;
    }

    @Override
    public String toString() {
        return "Incidencia{" +
                "idParte=" + idParte +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", sancion='" + sancion + '\'' +
                ", idAlumno=" + idAlumno +
                ", idProfesor=" + idProfesor +
                ", idPuntos=" + idPuntos +
                '}';
    }
}
