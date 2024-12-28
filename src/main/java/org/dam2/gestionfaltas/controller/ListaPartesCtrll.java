package org.dam2.gestionfaltas.controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.dam2.gestionfaltas.dao.AlumnoDAOImpl;
import org.dam2.gestionfaltas.dao.IncidenciaDAOImpl;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.model.Incidencia;
import org.dam2.gestionfaltas.util.AlertUtil;
import org.dam2.gestionfaltas.util.R;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ListaPartesCtrll implements Initializable {
    @FXML
    private Button recargarTablaBtt;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button buscarFechaBtt;
    @FXML
    private Button buscarNumExpBtt;
    @FXML
    private TableColumn<Incidencia, String> descripcionCol;
    @FXML
    private TableColumn<Incidencia, Integer> expedienteCol;
    @FXML
    private TableColumn<Incidencia, String> fechaCol;
    @FXML
    private DatePicker fechaFinalDP;
    @FXML
    private DatePicker fechaInicioDP;
    @FXML
    private TableColumn<Incidencia, String> grupoCol;
    @FXML
    private TableView<Incidencia> listaPartesTable;
    @FXML
    private TableColumn<Incidencia, String> nombreAlumnoCol;
    @FXML
    private TextField numExpedienteTF;
    @FXML
    private Pagination pagination;
    @FXML
    private TableColumn<Incidencia, String> profesorCol;
    @FXML
    private TableColumn<Incidencia, String> sancionCol;
    @FXML
    private TableColumn<Incidencia, Button> colVerMas;

    private final IncidenciaDAOImpl incidenciaDAO = new IncidenciaDAOImpl(); // DAO PARA CONSULTAR DATOS
    private final AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl(); // DAO PARA CONSULTAR DATOS
    private static final int filasPorPagina = 5; // NÚMERO DE FILAS POR PÁGINA

    @FXML
    void onBuscarFechaAction(ActionEvent event) {
        LocalDate fechaInicio = fechaInicioDP.getValue();
        LocalDate fechaFin = fechaFinalDP.getValue();

        if (fechaInicio != null && fechaFin == null) { // BUSCADO POR FECHA CONCRETA
            // OBTENER LAS INCIDENCIAS FILTRADAS POR FECHAS
            List<Incidencia> incidenciasFechas = incidenciaDAO.listarPorFechaConcreta(fechaInicio);

            // CREAR UN OBSERVABLELIST CON LAS INCIDENCIAS
            ObservableList<Incidencia> datos = FXCollections.observableArrayList(incidenciasFechas);
            listaPartesTable.setItems(datos);
        } else if (fechaInicio == null && fechaFin != null) {// BUSCADO POR FECHA CONCRETA
            // OBTENER LAS INCIDENCIAS FILTRADAS POR FECHAS
            List<Incidencia> incidenciasFechas = incidenciaDAO.listarPorFechaConcreta(fechaFin);

            // CREAR UN OBSERVABLELIST CON LAS INCIDENCIAS
            ObservableList<Incidencia> datos = FXCollections.observableArrayList(incidenciasFechas);
            listaPartesTable.setItems(datos);  // ESTABLECER LOS DATOS EN LA TABLA
        } else if(fechaInicio != null) {
            if (fechaInicio.isBefore(fechaFin)) {
                // OBTENER LAS INCIDENCIAS FILTRADAS POR FECHAS
                List<Incidencia> incidenciasFechas = incidenciaDAO.listarPorFechas(fechaInicio,fechaFin);

                // CREAR UN OBSERVABLELIST CON LAS INCIDENCIAS
                ObservableList<Incidencia> datos = FXCollections.observableArrayList(incidenciasFechas);
                listaPartesTable.setItems(datos);
            } else {
                AlertUtil.mostrarError("La primera fecha debe ser menor que la segunda.");
            }
        } else {
            AlertUtil.mostrarError("Debe seleccionar un rango de fechas.");
        } // SI LAS FECHAS NO SON NULAS, SE BUSCA
    } // BOTON PARA BUSCAR POR FECHAS

    @FXML
    void onBuscarNumExpAction(ActionEvent event) {
        if (numExpedienteTF.getText().matches("\\d+")) {
            int numeroExpediente = Integer.parseInt(numExpedienteTF.getText());
            Alumno alumno = alumnoDAO.obtener(numeroExpediente); // OBTENER ALUMNO

            if (alumno != null) {
                // OBTENER LAS INCIDENCIAS DEL ALUMNO
                List<Incidencia> incidenciasAlumno = incidenciaDAO.listarPorAlumno(alumno);

                // CREAR UN OBSERVABLELIST CON LAS INCIDENCIAS
                ObservableList<Incidencia> datos = FXCollections.observableArrayList(incidenciasAlumno);
                listaPartesTable.setItems(datos);  // ESTABLECER LOS DATOS EN LA TABLA
            } else {
                // SI NO EXISTE, SE LIMPIA LA TABLA Y SE MUESTRA EL ERROR
                listaPartesTable.setItems(FXCollections.emptyObservableList());
                AlertUtil.mostrarError("No existe un alumno con el expediente: " + numeroExpediente);
            } // SI EL ALUMNO NO EXISTE, SALTARA UN ERROR
        } else {
            AlertUtil.mostrarError("Debe ingresar un número de expediente válido.");
        } // VALIDAR QUE SEA UN NÚMERO
    } // BOTON PARA BUSCAR LAS INCIDENCIAS DE UN ALUMNO POR SU EXPEDIENTE

    private void refreshTable(int paginaActual) {
        int inicio = paginaActual * filasPorPagina; // SE CALCULA EL INDICE DE INCIO A PARTIR DE LA PAGINA ACTUAL Y EL NUM FILAS
        List<Incidencia> partesPaginados = incidenciaDAO.listarIncidencia(inicio, filasPorPagina); // LISTA PAGINADA DE PARTES

        // CREAR EL OBSERVABLELIST CON ESA LISTA
        ObservableList<Incidencia> datos = FXCollections.observableArrayList(partesPaginados);
        listaPartesTable.setItems(datos); // ESTABLECEMOS LOS DATOS EN LA TABLA

        // CONFIGURAMOS LOS COLORES SEGUN LA SANCION
        /** El método setRowFactory de TableView en JavaFX recibe una función lambda
         *  que define cómo se van a construir las filas (TableRow) de la tabla. **/
        listaPartesTable.setRowFactory(tableView -> new TableRow<Incidencia>() {
            @Override
            protected void updateItem(Incidencia incidencia, boolean empty) {
                super.updateItem(incidencia, empty);

                if (incidencia == null || empty) {
                    setStyle(""); // LIMPIAR ESTILO
                } else {
                    switch (incidencia.getIdPuntos().getColor()) {
                        case VERDE -> setStyle("-fx-background-color: #99ff99;");
                        case NARANJA -> setStyle("-fx-background-color: #ffcc66;");
                        case ROJO -> setStyle("-fx-background-color: #ff6666;");
                    }

                } // SI LA INCIDENCIA ES NULO O ESTA VACIA SE LIMPIA EL ESTILO, SI NO, SE CAMBIA EL COLOR SEGUN LA SANCION
            } // ESTE METODO ES PARA ACTUALIZAR EL CONCENIDO DE LA FILA CUANDO SE ASIGNAN NUEVOS DATOS/CAMBIO DE ESTADO
        }); // setRowFactory
    } // METODO PARA ACTUALIZAR TABLA

    private void configurarPaginacion() {
        long totalPartes = incidenciaDAO.contar(); // TOTAL DE PARTES
        // CALCULAR EL NÚMERO DE PÁGINAS NECESARIAS
        int totalPaginas = (int) Math.ceil((double) totalPartes / filasPorPagina);

        pagination.setPageCount(totalPaginas);  // ESTABLECER EL TOTAL DE PÁGINAS
        pagination.setCurrentPageIndex(0);  // ESTABLECER LA PÁGINA INICIAL

        // ESCUCHAR EL CAMBIO DE PÁGINA Y ACTUALIZAR LA TABLA
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            refreshTable(newIndex.intValue());
        });

        refreshTable(0); // CARGAR LA PRIMERA PÁGINA
    } // CONFIGURAR PAGINACION
    public void centrarTextoCeldas() {
        expedienteCol.setStyle("-fx-alignment: CENTER");
        nombreAlumnoCol.setStyle("-fx-alignment: CENTER");
        colVerMas.setStyle("-fx-alignment: CENTER");
        descripcionCol.setStyle("-fx-alignment: CENTER");
        fechaCol.setStyle("-fx-alignment: CENTER");
        grupoCol.setStyle("-fx-alignment: CENTER");
        profesorCol.setStyle("-fx-alignment: CENTER");
        sancionCol.setStyle("-fx-alignment: CENTER");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //imageView.fitWidthProperty().bind(anchorPane.widthProperty());
        //imageView.fitHeightProperty().bind(anchorPane.heightProperty());
        // CONFIGURAR COLUMNAS
        /**NOTA: para configurar como se obtiene el valor de una celda en una columna se utiliza una función lambda
         * con un objeto de tipo ReadOnlyObjectWrapper<S>, que se utiliza para encapsular el valor que se mostrará
         * en la celda de la tabla. El valor que se envuelve es el resultado de data.getValue() .**/
        expedienteCol.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getIdAlumno().getNumeroExpediente()));
        nombreAlumnoCol.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getIdAlumno().getNombreAlumno()));
        grupoCol.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getIdAlumno().getGrupo().getNombreGrupo()));
        profesorCol.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getIdProfesor().getNombreProfesor()));
        fechaCol.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getFecha().toString()));

        descripcionCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        sancionCol.setCellValueFactory(new PropertyValueFactory<>("sancion"));

        // Configura colVerMas para tener un boton si la fila tiene una incidencia
        colVerMas.setCellFactory(col -> new TableCell<>() {
            private final Button boton = new Button("Ver más");

            {
                boton.getStyleClass().add("viewmore");
                // Configurar el evento del botón
                boton.setOnAction(event -> {
                    Incidencia incidencia = getTableView().getItems().get(getIndex());
                    onVerMas(incidencia);
                });
            }

            @Override
            protected void updateItem(Button b, boolean vacio) {
                super.updateItem(b, vacio);
                if(vacio) setGraphic(null);
                else setGraphic(boton);

            }
        });

        configurarPaginacion(); // CONFIGURAR LA PAGINACIÓN
        centrarTextoCeldas();
    } // INITIALIZABLE

    private void onVerMas(Incidencia incidencia) {

        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("mostrarParte.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());

            ((MostrarParteCtrll) fxmlLoader.getController()).setIncidencia(incidencia);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Detalles incidencia");
            stage.showAndWait();

            listaPartesTable.refresh();

        } catch (IOException e) {}
    }

    public void onRecargarAction(ActionEvent event) {
        configurarPaginacion();
    }
}