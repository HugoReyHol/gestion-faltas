package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.dam2.gestionfaltas.dao.ProfesorDAOImpl;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.AlertUtil;
import org.dam2.gestionfaltas.util.Encriptador;
import org.dam2.gestionfaltas.util.Tipos;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearProfesorCtrll implements Initializable {

    @FXML
    private Button btCrearProfesor;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private PasswordField tfContrasena;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfNumeroAsignado;

    String[] tipoProfesor={"PROFESOR", "JEFE_DE_ESTUDIOS"};
    ProfesorDAOImpl profesorDAO=new ProfesorDAOImpl();
    @FXML
    void onCrearProfesor(ActionEvent event) {
        if (tfNombre.getText().isBlank() || tfNumeroAsignado.getText().isBlank() ||
                tfContrasena.getText().isBlank() || cbTipo.getValue() == null) {
            AlertUtil.mostrarInfo("Rellene todos los campos");

        }else{

            Profesor profesor= new Profesor(Encriptador.encriptar(tfContrasena.getText()),tfNombre.getText(),tfNumeroAsignado.getText(),Tipos.valueOf(cbTipo.getValue()));
            System.out.println(profesor);
            profesorDAO.crear(profesor);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipo.getItems().addAll(tipoProfesor);
    }
}


