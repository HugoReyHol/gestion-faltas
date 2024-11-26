package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PartesAdvertenciaCtrll {

    @FXML
    private Button bt_verde;

    @FXML
    private ComboBox<?> cb_tipos;

    @FXML
    private TextField tf_nExpediente;

    @FXML
    void onVerde(ActionEvent event) {

    }
}