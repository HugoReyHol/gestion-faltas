package org.dam2.gestionfaltas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LoginCtrll {
    @FXML
    private Label welcomeText;

    private final SessionFactory factory = HibernateUtil.getSessionFactory();
    private final Session session = HibernateUtil.getSession();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void close() {
        session.close();
        factory.close();

    }
}