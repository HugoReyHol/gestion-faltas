package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.CambiarVista;
import org.dam2.gestionfaltas.util.Tipos;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuCtrll implements Initializable {

    @FXML
    private Button bt_crearParte;

    @FXML
    private Button bt_crearProfesor;

    @FXML
    private Button bt_listaAlumnos;

    @FXML
    private Button bt_listaPartes;

    public static Profesor profesor;

    @FXML
    void onCrearParte(ActionEvent event) {
        CambiarVista.cambiarVistaBtt("partesAdvertencia.fxml", bt_crearParte, "Crear Parte Advertencia");
    } // IR A LA VISTA DE CREAR PARTE

    @FXML
    void onCrearProfesor(ActionEvent event) {
        CambiarVista.cambiarVistaBtt("crearProfesor.fxml", bt_crearProfesor, "Crear Profesor");
    } // IR A LA VISTA DE CREAR PROFESORES

    @FXML
    void onListaAlumnos(ActionEvent event) {
        CambiarVista.cambiarVistaBtt("listaAlumnos.fxml", bt_listaAlumnos, "Lista de Alumnos");
    } // IR A LA VISTA DE LISTA DE ALUMNOS

    @FXML
    void onListaPartes(ActionEvent event) {
        CambiarVista.cambiarVistaBtt("listaPartes.fxml", bt_listaPartes, "Lista de Partes");
    } // IR A LA VISTA DE LISTA DE PARTES

    public void actualizarBotones() {
        if (profesor.getTipo() == Tipos.PROFESOR) {
            bt_crearProfesor.setVisible(false);
            bt_listaAlumnos.setVisible(false);
            bt_listaPartes.setVisible(false);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (profesor != null) {
            actualizarBotones();
        }
    }
}

