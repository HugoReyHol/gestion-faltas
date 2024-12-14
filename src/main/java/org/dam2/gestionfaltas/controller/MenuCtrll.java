package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dam2.gestionfaltas.util.CambiarVista;
import org.dam2.gestionfaltas.util.R;

public class MenuCtrll {

    @FXML
    private Button bt_crearParte;

    @FXML
    private Button bt_crearProfesor;

    @FXML
    private Button bt_listaAlumnos;

    @FXML
    private Button bt_listaPartes;

    @FXML
    void onCrearParte(ActionEvent event) {
        CambiarVista.cambiarVistaBtt("partesAdvertencia.fxml", bt_crearParte, "Crear Parte Advertencia");
    } // IR A LA VISTA DE CREAR PARTE

    @FXML
    void onCrearProfesor(ActionEvent event) {
        //CambiarVista.cambiarVistaBtt(".fxml", bt_crearParte, "Crear Profesor");
    } // IR A LA VISTA DE CREAR PROFESORES

    @FXML
    void onListaAlumnos(ActionEvent event) {
        CambiarVista.cambiarVistaBtt("listaAlumnos.fxml", bt_listaAlumnos, "Lista de Alumnos");
    } // IR A LA VISTA DE LISTA DE ALUMNOS

    @FXML
    void onListaPartes(ActionEvent event) {
        CambiarVista.cambiarVistaBtt("listaPartes.fxml", bt_listaPartes, "Lista de Partes");
    } // IR A LA VISTA DE LISTA DE PARTES

}

