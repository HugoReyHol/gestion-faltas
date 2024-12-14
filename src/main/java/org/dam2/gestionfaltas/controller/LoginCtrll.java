package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dam2.gestionfaltas.dao.ProfesorDAOImpl;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.AlertUtil;
import org.dam2.gestionfaltas.util.Encriptador;
import org.dam2.gestionfaltas.util.R;

import java.io.IOException;


public class LoginCtrll {

    @FXML
    public TextField inNumero;

    @FXML
    public PasswordField inContrasena;

    private final ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();


    public void onLogIn(ActionEvent actionEvent) {
        // Comprueba si los campos están vacios
        if (inNumero.getText().isBlank()) {
            AlertUtil.mostrarInfo("El número asignado no puede estar vacío ");
            return;
        }
        if (inContrasena.getText().isBlank()) {
            AlertUtil.mostrarInfo("La contraseña no puede estar vacía ");
            return;
        }

        // Comprueba si el campo contiene 4 letras o menos
        if (inNumero.getText().length() > 4) {
            AlertUtil.mostrarInfo("El número asignado no puede tener más de 4 letras");
            return;
        }

        // Compruba si el usuario existe y en caso de existir si a contraseña es correcta
        Profesor profesor = profesorDAO.obtener(inNumero.getText());

        if (profesor == null) {
            AlertUtil.mostrarInfo("El profesor " + inNumero.getText() + " no existe");
            return;
        }

        if (!profesor.getContrasena().equals(Encriptador.encriptar(inContrasena.getText()))) {
            AlertUtil.mostrarInfo("Contraseña incorrecta, inténtelo de nuevo");
            return;
        }

        // Cambia a escena menu y pasa el profesor iniciado
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("vistaMenu.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Error al cargar la escena vistaMenu");
            return;
        }

        MenuCtrll.profesor = profesor;

        ((Stage) inNumero.getScene().getWindow()).setScene(scene);

    }
}