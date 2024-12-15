package org.dam2.gestionfaltas.controller;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.dam2.gestionfaltas.model.Incidencia;
import org.dam2.gestionfaltas.util.Color;


public class MostrarParteCtrll {
    public AnchorPane anchoPaneParte;
    public Label lbTitulo;
    public TextField tf_nExpediente;
    public TextField datePicker;
    public TextField cb_hora;
    public TextArea tx_descripcion;
    public TextField tf_profesor;
    public TextField tf_nombreGrupo;
    public Pane paneVerde;
    public TextArea tx_sancion;
    public Pane paneRojo;
    public TextField opcionesSancioncb;

    public void setIncidencia(Incidencia incidencia){
        Color color = incidencia.getIdPuntos().getColor();

        lbTitulo.setText("PARTE " + color + " DE ADVERTENCIA");

        tf_nExpediente.setText(String.valueOf(incidencia.getIdAlumno().getNumeroExpediente()));

        datePicker.setText(String.valueOf(incidencia.getFecha()));
        cb_hora.setText(incidencia.getHora());
        tx_descripcion.setText(incidencia.getDescripcion());

        tf_profesor.setText(incidencia.getIdProfesor().getNumeroAsignado());

        tf_nombreGrupo.setText(incidencia.getIdAlumno().getGrupo().getNombreGrupo());

        tx_sancion.setText(incidencia.getSancion());
        opcionesSancioncb.setText(incidencia.getSancion());

        switch (color) {
            case VERDE -> {
                paneVerde.setVisible(true);
                paneRojo.setVisible(false);
                anchoPaneParte.setStyle("-fx-background-color: green;");
            }
            case NARANJA -> {
                paneVerde.setVisible(true);
                paneRojo.setVisible(false);
                anchoPaneParte.setStyle("-fx-background-color: orange;");
            }
            case ROJO -> {
                paneVerde.setVisible(false);
                paneRojo.setVisible(true);
                anchoPaneParte.setStyle("-fx-background-color: red;");
            }
        }
    }
}
