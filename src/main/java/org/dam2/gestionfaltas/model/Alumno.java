package org.dam2.gestionfaltas.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @Column(name = "id_alum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlumno;

    @Column(name = "puntos_acumulados")
    private int puntosAcumulados;

    @Column(name = "nombre_alum")
    private String nombreAlumno;

    @Column(name = "numero_expediente")
    private int numeroExpediente;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    private Grupo grupo;

    @OneToMany(mappedBy = "idAlumno", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Incidencia> incidencias;

    public Alumno() {
    }

    public Alumno(int puntosAcumulados, String nombreAlumno, int numeroExpediente) {
        this.puntosAcumulados = puntosAcumulados;
        this.nombreAlumno = nombreAlumno;
        this.numeroExpediente = numeroExpediente;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public int getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(int numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", puntosAcumulados=" + puntosAcumulados +
                ", nombreAlumno='" + nombreAlumno + '\'' +
                ", numeroExpediente=" + numeroExpediente +
                ", grupo=" + grupo +
                '}';
    }
}
