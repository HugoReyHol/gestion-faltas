package org.dam2.gestionfaltas.model;

import javax.persistence.*;


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
    private String nombre;

    @Column(name = "numero_asignado")
    private int numeroAsignado;

    // TODO Acabar clase

}
