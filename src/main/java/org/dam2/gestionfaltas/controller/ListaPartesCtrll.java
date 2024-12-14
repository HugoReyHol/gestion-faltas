package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ListaPartesCtrll {

    @FXML
    private Button buscarFechaBtt;

    @FXML
    private Button buscarNumExpBtt;

    @FXML
    private TableColumn<?, ?> descripcionCol;

    @FXML
    private TableColumn<?, ?> expedienteCol;

    @FXML
    private TableColumn<?, ?> fechaCol;

    @FXML
    private DatePicker fechaFinalDP;

    @FXML
    private DatePicker fechaInicioDP;

    @FXML
    private TableColumn<?, ?> grupoCol;

    @FXML
    private TableView<?> listaPartesTable;

    @FXML
    private TableColumn<?, ?> nombreAlumnoCol;

    @FXML
    private TextField numExpedienteTF;

    @FXML
    private Pagination pagination;

    @FXML
    private TableColumn<?, ?> profesorCol;

    @FXML
    private TableColumn<?, ?> sancionCol;

    @FXML
    void onBuscarFechaAction(ActionEvent event) {

    }

    @FXML
    void onBuscarNumExpAction(ActionEvent event) {

    }

}