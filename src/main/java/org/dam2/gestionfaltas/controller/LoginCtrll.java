package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;


public class LoginCtrll {

    @FXML
    public TextField inNumero;

    @FXML
    public PasswordField inContrasena;

    private final Session session = HibernateUtil.getSession();


    public void onLogIn(ActionEvent actionEvent) {
        // TODO implementar logica de verificacion de usuario cuando tengamos las clases y el dao

    }
}