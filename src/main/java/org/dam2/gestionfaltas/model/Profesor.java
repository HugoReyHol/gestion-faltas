package org.dam2.gestionfaltas.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @Column(name = "id_profesor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfesor;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "nombre")
    private String nombreProfesor;

    @Column(name = "numero_asignado")
    private String numeroAsignado;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "idProfesor", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Incidencia> incidencias;

    public Profesor() {
    }

    public Profesor(String contrasena, String nombreProfesor, String numeroAsignado, String tipo) {
        this.contrasena = contrasena;
        this.nombreProfesor = nombreProfesor;
        this.numeroAsignado = numeroAsignado;
        this.tipo = tipo;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNumeroAsignado() {
        return numeroAsignado;
    }

    public void setNumeroAsignado(String numeroAsignado) {
        this.numeroAsignado = numeroAsignado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "idProfesor=" + idProfesor +
                ", contrasena='" + contrasena + '\'' +
                ", nombreProfesor='" + nombreProfesor + '\'' +
                ", numeroAsignado='" + numeroAsignado + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
