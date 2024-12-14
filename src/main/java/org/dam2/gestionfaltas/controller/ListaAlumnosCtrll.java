package org.dam2.gestionfaltas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ListaAlumnosCtrll {

    @FXML
    private Button buscarNumExpBtt;

    @FXML
    private TableColumn<?, ?> expedienteCol;

    @FXML
    private TableView<?> listaPartesTable;

    @FXML
    private TableColumn<?, ?> nombreAlumnoCol;

    @FXML
    private TableColumn<?, ?> nombreGrupoCol;

    @FXML
    private TextField numExpedienteTF;

    @FXML
    private Pagination pagination;

    @FXML
    private TableColumn<?, ?> puntosAcumuladosCol;

    @FXML
    void onBuscarNumExpAction(ActionEvent event) {

    }

}