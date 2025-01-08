package org.dam2.gestionfaltas.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.dam2.gestionfaltas.dao.ProfesorDAOImpl;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.AlertUtil;
import org.dam2.gestionfaltas.util.Encriptador;
import org.dam2.gestionfaltas.util.Tipos;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearProfesorCtrll implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane anchorPaneMenu;
    @FXML
    private AnchorPane anchorPaneForm;
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
        if (tfNombre.getText().isBlank()) {
            AlertUtil.mostrarInfo("El campo \"Nombre\" no puede estar vacío.");
            return;
        }

        if (tfNumeroAsignado.getText().isBlank()) {
            AlertUtil.mostrarInfo("El campo \"Número asignado\" no puede estar vacío.");
            return;
        }

        if (profesorDAO.obtener(tfNumeroAsignado.getText()) != null) {
            AlertUtil.mostrarInfo("El profesor " + tfNumeroAsignado.getText() + " ya existe.");
            return;
        }

        if (tfContrasena.getText().isBlank()) {
            AlertUtil.mostrarInfo("El campo \"Contraseña\" no puede estar vacío.");
            return;
        }

        if (cbTipo.getValue() == null) {
            AlertUtil.mostrarInfo("Debe seleccionar un tipo de profesor");
            return;
        }

        Profesor profesor= new Profesor(Encriptador.encriptar(tfContrasena.getText()),tfNombre.getText(),tfNumeroAsignado.getText(),Tipos.valueOf(cbTipo.getValue()));
        System.out.println(profesor);
        profesorDAO.crear(profesor);

        cbTipo.setValue(null);
        tfContrasena.clear();
        tfNumeroAsignado.clear();
        tfNombre.clear();

        AlertUtil.mostrarInfo("Profesor creado correctamente.");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipo.getItems().addAll(tipoProfesor);

        Platform.runLater(() -> {
            anchorPaneForm.layoutXProperty().bind(anchorPane.widthProperty().subtract(anchorPaneForm.widthProperty()).divide(2));
            anchorPaneForm.layoutYProperty().bind(anchorPane.heightProperty().subtract(anchorPaneForm.heightProperty()).divide(2));
        });
    }
}


