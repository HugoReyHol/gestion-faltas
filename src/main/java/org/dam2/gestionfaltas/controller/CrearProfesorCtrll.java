package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearProfesorCtrll implements Initializable {

    @FXML
    private Button btCrearProfesor;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TextField tfContrasena;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfNumeroAsignado;

    String[] tipoProfesor={"Profesor", "Jefe de Estudio"};
    @FXML
    void onCrearProfesor(ActionEvent event) {
        if (tfNombre.getText().isEmpty() || tfNumeroAsignado.getText().isEmpty() ||
                tfContrasena.getText().isEmpty() || cbTipo.getValue() == null) {
            System.out.println("Estan vacios");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipo.getItems().addAll(tipoProfesor);
    }
}


