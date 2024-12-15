package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class PartesAdvertenciaCtrll implements Initializable {

    @FXML
    private Button bt_crear;

    @FXML
    private Button bt_parteNaranja;

    @FXML
    private Button bt_parteRojo;

    @FXML
    private Button bt_parteVerde;

    @FXML
    private ComboBox<?> cb_hora;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lbTitulo;

    @FXML
    private ComboBox<?> opcionesSancioncb;

    @FXML
    private Pane paneRojo;

    @FXML
    private Pane paneVerde;

    @FXML
    private TextField tf_nExpediente;

    @FXML
    private TextField tf_nombreGrupo;

    @FXML
    private TextField tf_profesor;

    @FXML
    private TextArea tx_descripcion;

    @FXML
    private TextArea tx_sancion;

    private boolean esVerde = false;

    @FXML
    void onCrear(ActionEvent event) {
        if (esVerde) {
            if (tf_nExpediente.getText().isEmpty() || tf_nombreGrupo.getText().isEmpty() ||
                    tf_profesor.getText().isEmpty() || tx_descripcion.getText().isEmpty() ||
                    datePicker.getValue() == null || cb_hora.getValue() == null || tx_sancion.getText().isEmpty()) {
                System.out.println("Estan vacios");
            }
        } else {
            if (tf_nExpediente.getText().isEmpty() || tf_nombreGrupo.getText().isEmpty() ||
                    tf_profesor.getText().isEmpty() || tx_descripcion.getText().isEmpty() ||
                    datePicker.getValue() == null || cb_hora.getValue() == null || opcionesSancioncb.getValue() == null) {
                System.out.println("Estan vacios");
            }
        }


    }

    @FXML
    private AnchorPane anchoPaneParte;

    @FXML
    void onParteNaranja(ActionEvent event) {
        paneRojo.setVisible(false);
        paneVerde.setVisible(true);
        anchoPaneParte.setStyle("-fx-background-color: orange;");
        lbTitulo.setText("PARTE NARANJA DE ADVERTENCIA");
        esVerde = true;
    }

    @FXML
    void onParteRojo(ActionEvent event) {
        paneRojo.setVisible(true);
        paneVerde.setVisible(false);
        anchoPaneParte.setStyle("-fx-background-color: red;");
        lbTitulo.setText("PARTE ROJO DE ADVERTENCIA");
        esVerde = false;
    }

    @FXML
    void onParteVerde(ActionEvent event) {
        paneRojo.setVisible(false);
        paneVerde.setVisible(true);
        anchoPaneParte.setStyle("-fx-background-color: green;");
        lbTitulo.setText("PARTE VERDE DE ADVERTENCIA");
        esVerde = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneRojo.setVisible(false);
    }
}

