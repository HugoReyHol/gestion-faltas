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

    public Alumno(String nombreAlumno, int numeroExpediente) {
        this.nombreAlumno = nombreAlumno;
        this.numeroExpediente = numeroExpediente;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
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

    public int getPuntosAcumulados() {
        int puntosAcumulados = 0;  // VARIABLE QUE GUARDA LOS PUNTOS ACUMULADOS
        for (Incidencia incidencia : incidencias) {
            if (incidencia.getIdPuntos() != null) {
                // SUMAMOS LOS PUNTOS DE CADA INCIDENCIA
                puntosAcumulados += incidencia.getIdPuntos().getPuntos();
            } // SI LA INCIDENCIA TIENE UN ID DE PUNTOS ASOCIADO SUMAMOS LOS PUNTOS
        } // ITERAMOS SOBRE LA LISTA DE INCIDENCIAS DEL ALUMNO

        return puntosAcumulados; // RETORNAMOS EL TOTAL DE PUNTOS
    } // METODO PARA CALCULAR LOS PUNTOS ACUMULADOS

    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", nombreAlumno='" + nombreAlumno + '\'' +
                ", numeroExpediente=" + numeroExpediente +
                ", grupo=" + grupo +
                '}';
    }
}
