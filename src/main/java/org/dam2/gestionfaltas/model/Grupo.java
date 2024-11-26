package org.dam2.gestionfaltas.model;


import javax.persistence.*;


@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @Column(name = "id_grupo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGrupo;

    @Column(name = "nombre_grupo")
    private String nombreGrupo;

    public Grupo() {
    }

    public Grupo(int idGrupo, String nombreGrupo) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "idGrupo=" + idGrupo +
                ", nombreGrupo='" + nombreGrupo + '\'' +
                '}';
    }
}
