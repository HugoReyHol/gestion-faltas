package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.dam2.gestionfaltas.dao.ProfesorDAOImpl;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.Encriptador;


public class LoginCtrll {

    @FXML
    public TextField inNumero;

    @FXML
    public PasswordField inContrasena;

    private final ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();


    public void onLogIn(ActionEvent actionEvent) {
        // Comprueba si los campos están vacios
        if (inNumero.getText().isBlank()) {
            // TODO Alerta de introducir número
            System.out.println("Numero vacio");
            return;
        }
        if (inContrasena.getText().isBlank()) {
            // TODO Alerta de introducir contraseña
            System.out.println("Contra vacia");
            return;
        }

        // Comprueba si el campo contiene 4 letras o menos
        if (inNumero.getText().length() > 4) {
            // TODO Alerta de introducir numero formato correcto
            System.out.println("Formato malo");
            return;
        }

        // Compruba si el usuario existe y en caso de existir si a contraseña es correcta
        Profesor profesor = profesorDAO.obtener(inNumero.getText());

        if (profesor == null) {
            // TODO Alerta profesor no existe
            System.out.println("Profe inventado");
            return;
        }

        if (!profesor.getContrasena().equals(Encriptador.encriptar(inContrasena.getText()))) {
            // TODO Alerta contraseña incorrecta
            System.out.println("Contraseña mala");
            return;
        }

        // Cambia a escena menu y pasa el profesor iniciado
        System.out.println("Ha funcionao");

    }
}