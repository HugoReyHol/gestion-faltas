package org.dam2.gestionfaltas.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableRow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.dam2.gestionfaltas.dao.AlumnoDAOImpl;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.util.AlertUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListaAlumnosCtrll implements Initializable {
    @FXML
    private Button recargarTablaBtt;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button buscarNumExpBtt;
    @FXML
    private TableColumn<Alumno, Integer> expedienteCol;
    @FXML
    private TableView<Alumno> listaAlumnosTable;
    @FXML
    private TableColumn<Alumno, String> nombreAlumnoCol;
    @FXML
    private TableColumn<Alumno, String> nombreGrupoCol;
    @FXML
    private TextField numExpedienteTF;
    @FXML
    private Pagination pagination;
    @FXML
    private TableColumn<Alumno, Integer> puntosAcumuladosCol;

    private final AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl(); // DAO PARA CONSULTAR DATOS
    private static final int filasPorPagina = 5; // NÚMERO DE FILAS POR PÁGINA


    @FXML
    public void onBuscarNumExpListener(KeyEvent keyEvent) {
        filtrarLista(false);
        if (numExpedienteTF.getText().isBlank()) {
            configurarPaginacion();
        }
    }

    @FXML
    public void onBuscarNumExpBoton(ActionEvent actionEvent) {
        filtrarLista(true);
    }

    void filtrarLista(boolean mostrarAlerta) {
        if (numExpedienteTF.getText().matches("\\d+")) {
            int numeroExpediente = Integer.parseInt(numExpedienteTF.getText());

            // OBTENER EL ALUMNO DESDE LA BASE DE DATOS USANDO EL DAO
            Alumno alumno = alumnoDAO.obtener(numeroExpediente);

            if (alumno != null) {
                // SI EXISTE EL ALUMNO, CREAMOS UN OBSERVABLELIST CON EL ALUMNO OBTENIDO
                ObservableList<Alumno> datos = FXCollections.observableArrayList();
                datos.add(alumno);

                // ACTUALIZAMOS LA TABLA CON LOS DATOS DEL ALUMNO BUSCADO
                listaAlumnosTable.setItems(datos);
            } else {
                // SI NO EXISTE, SE LIMPIA LA TABLA Y SE MUESTRA EL ERROR
                listaAlumnosTable.setItems(FXCollections.emptyObservableList());
                if (mostrarAlerta) AlertUtil.mostrarError("No existe un alumno con el expediente: " + numeroExpediente);
            } // SI EL ALUMNO NO EXISTE, SALTARA UN ERROR
        } else {
            if (mostrarAlerta) AlertUtil.mostrarError("Debe ingresar un número de expediente válido.");
        } // VALIDAR QUE SEA UN NÚMERO
    } // BOTON PARA BUSCAR UN ALUMNO POR SU EXPEDIENTE

    /**
     * NOTA: la paginación funciona calculando el índice de inicio de la lista a partir
     * de la página actual y el número de filas por página.
     * Entonces, se obtienen los datos de la paginacion desde el dao utilizando ese índice de inicio
     * y el límite de filas por página.
     * Luego, estos datos se muestran en la tabla y se colorean las filas según el número de
     * incidencias del alumno.
     ****/

    private void refreshTable(int paginaActual) {
        int inicio = paginaActual * filasPorPagina; // SE CALCULA EL INDICE DE INCIO A PARTIR DE LA PAGINA ACTUAL Y EL NUM FILAS
        List<Alumno> alumnosPaginados = alumnoDAO.listar(inicio, filasPorPagina); // LISTA PAGINADA DE ALUMNOS

        // CREAR EL OBSERVABLELIST CON ESA LISTA
        ObservableList<Alumno> datos = FXCollections.observableArrayList(alumnosPaginados);
        listaAlumnosTable.setItems(datos); // ESTABLECEMOS LOS DATOS EN LA TABLA


        // CONFIGURAMOS LOS COLORES SEGUN LOS PUNTOS
        /** El método setRowFactory de TableView en JavaFX recibe una función lambda
         *  que define cómo se van a construir las filas (TableRow) de la tabla. **/
        listaAlumnosTable.setRowFactory(tableView -> new TableRow<Alumno>() {
            @Override
            protected void updateItem(Alumno alumno, boolean empty) {
                super.updateItem(alumno, empty);

                if (alumno == null || empty) {
                    setStyle(""); // LIMPIAR ESTILO
                } else {
                    int puntos = alumno.getPuntosAcumulados(); // PUNTOS ACUMULADOS
                    if (puntos >= 12) {
                        // MÁS DE 12 INCIDENCIAS -> COLOR ROJO
                        setStyle("-fx-background-color: #ff6666;");
                    } else if (puntos >= 6) {
                        // ENTRE 5 Y 9 INCIDENCIAS -> COLOR NARANJA
                        setStyle("-fx-background-color: #ffcc66;");
                    } else {
                        // MENOS DE 5 INCIDENCIAS -> COLOR VERDE
                        setStyle("-fx-background-color: #99ff99;");
                    }
                } // SI EL ALUMNO ES NULO O ESTA VACIO SE LIMPIA EL ESTILO, SI NO, SE CAMBIA EL COLOR SEGUN LA CANTIDAD DE PUNTOS
            } // ESTE METODO ES PARA ACTUALIZAR EL CONCENIDO DE LA FILA CUANDO SE ASIGNAN NUEVOS DATOS/CAMBIO DE ESTADO
        }); // setRowFactory
    } // METODO PARA ACTUALIZAD TABLA

    private void configurarPaginacion() {
        long totalAlumnos = alumnoDAO.contar();
        int totalPaginas = (int) Math.ceil((double) totalAlumnos / filasPorPagina);

        pagination.setPageCount(totalPaginas);
        pagination.setCurrentPageIndex(0);
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            refreshTable(newIndex.intValue());
        });

        refreshTable(0); // CARGAR LA PRIMERA PÁGINA
    } // CONFIGURAR PAGINACION

    public void centrarTextoCeldas() {
        expedienteCol.setStyle("-fx-alignment: CENTER");
        nombreAlumnoCol.setStyle("-fx-alignment: CENTER");
        puntosAcumuladosCol.setStyle("-fx-alignment: CENTER");
        nombreGrupoCol.setStyle("-fx-alignment: CENTER");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // CONFIGURAR COLUMNAS
        expedienteCol.setCellValueFactory(new PropertyValueFactory<>("numeroExpediente"));
        nombreAlumnoCol.setCellValueFactory(new PropertyValueFactory<>("nombreAlumno"));
        nombreGrupoCol.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getGrupo().getNombreGrupo()));
        // Configuramos la columna de puntos acumulados
        puntosAcumuladosCol.setCellValueFactory(cellData -> {
            Alumno alumno = cellData.getValue();
            return new SimpleIntegerProperty(alumno.getPuntosAcumulados()).asObject();
        });

        configurarPaginacion(); // CONFIGURAR LA PAGINACIÓN
        centrarTextoCeldas();
    } // INITIALIZABLE

    public void onRecargarAction(ActionEvent event) {
        configurarPaginacion();
        numExpedienteTF.setText("");
    }
}