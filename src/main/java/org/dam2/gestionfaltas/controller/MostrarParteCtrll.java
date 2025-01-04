package org.dam2.gestionfaltas.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.dam2.gestionfaltas.dao.*;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.model.Hora;
import org.dam2.gestionfaltas.model.Incidencia;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.AlertUtil;
import org.dam2.gestionfaltas.util.Color;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;


public class MostrarParteCtrll implements Initializable {
    public Button borrarBtt;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane anchorPaneParte;
    @FXML
    private Label lbTitulo;
    @FXML
    private TextField tf_nExpediente;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> cb_hora;
    @FXML
    private TextArea tx_descripcion;
    @FXML
    private TextField tf_profesor;
    @FXML
    private TextField tf_nombreGrupo;
    @FXML
    private Pane paneVerde;
    @FXML
    private TextArea tx_sancion;
    @FXML
    private Pane paneRojo;
    @FXML
    private TextArea sancionOtraTxArea;
    @FXML
    private Button bt_parteNaranja;
    @FXML
    private Button editarBtt;

    @FXML
    private Button bt_parteRojo;

    @FXML
    private Button bt_parteVerde;
    public ComboBox<String> opcionesSancioncb;

    private Color color;
    private Alumno alumno;
    private Incidencia incidencia;
    private final AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl();
    private final HoraDAOImpl horaDAO = new HoraDAOImpl();
    private final ObservableList<String> horas = FXCollections.observableArrayList();
    private final ObservableList<String> sanciones = FXCollections.observableArrayList(
            """
                    Incoación de expediente o en su caso
                    expediente abreviado""",
            "Reunión con la Comisión de Convivencia",
            """
                    Es obligado pedir disculpas a la persona/as
                    contra las que se ejerció daño físico o moral,
                    y/o reparar los daños materiales causados""",
            "Otro");


    boolean isEditable = false;

    void habilitarCampos(boolean value) {
        isEditable = value;
        tf_nExpediente.setEditable(value);
        tf_nombreGrupo.setEditable(value);
        datePicker.setEditable(value);
        Platform.runLater(() -> cb_hora.getSelectionModel().select(cb_hora.getSelectionModel().getSelectedItem()));
        tx_descripcion.setDisable(!value);
        sancionOtraTxArea.setEditable(value);
        opcionesSancioncb.setEditable(value);
        tf_profesor.setEditable(value);
        tx_sancion.setDisable(!value);
        bt_parteNaranja.setDisable(!value);
        bt_parteRojo.setDisable(!value);
        bt_parteVerde.setDisable(!value);
        editarBtt.setText("Editar");
    } // METODO PARA HABILITAR O DESHABILITAR LOS CAMPOS

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
        alumno = incidencia.getIdAlumno();
        color = incidencia.getIdPuntos().getColor();

        lbTitulo.setText("PARTE " + color + " DE ADVERTENCIA");

        tf_nExpediente.setText(String.valueOf(incidencia.getIdAlumno().getNumeroExpediente()));

        datePicker.setValue(incidencia.getFecha());
        cb_hora.setValue(incidencia.getIdHora().getHora());
        tx_descripcion.setText(incidencia.getDescripcion());

        tf_profesor.setText(incidencia.getIdProfesor().getNumeroAsignado());

        tf_nombreGrupo.setText(incidencia.getIdAlumno().getGrupo().getNombreGrupo());

        tx_sancion.setText(incidencia.getSancion());

        if (incidencia.getSancion().equals(sanciones.toArray()[sanciones.size() - 1])) {
            opcionesSancioncb.setValue((String) sanciones.toArray()[sanciones.size() - 1]);
            sancionOtraTxArea.setText(incidencia.getSancion());
            sancionOtraTxArea.setVisible(true);

        } else opcionesSancioncb.setValue(incidencia.getSancion());

        switch (color) {
            case VERDE -> {
                paneVerde.setVisible(true);
                paneRojo.setVisible(false);
                anchorPaneParte.setStyle("-fx-background-color: #befc77");
            }
            case NARANJA -> {
                paneVerde.setVisible(true);
                paneRojo.setVisible(false);
                anchorPaneParte.setStyle("-fx-background-color: #FFA500");
            }
            case ROJO -> {
                paneVerde.setVisible(false);
                paneRojo.setVisible(true);
                anchorPaneParte.setStyle("-fx-background-color: #E64942");
            }
        }
    }

    public void onClickSancionAction(ActionEvent actionEvent) {
        if (opcionesSancioncb.getValue().equals(sanciones.toArray()[sanciones.size() - 1])) {
            sancionOtraTxArea.setVisible(true);
        } else {
            sancionOtraTxArea.setVisible(false);
        }
    }

    public void onEditarAction(ActionEvent actionEvent) {
        if (isEditable) {
            // SI ES EDITABLE, CIERRA LA VENTANA
            onCancelarAction(actionEvent);
        }
        habilitarCampos(true);
        ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();
        Profesor p = profesorDAO.obtener(tf_profesor.getText());
        if (p == null) {
            AlertUtil.mostrarInfo("El profesor no existe");
            return;
        }

        incidencia.setIdProfesor(p);

        if (alumno == null) {
            AlertUtil.mostrarInfo("Debe introducir un número de expediente válido");
            return;
        }

        incidencia.setIdAlumno(alumno);

        incidencia.setFecha(datePicker.getValue());

        if (incidencia.getFecha() == null) {
            AlertUtil.mostrarInfo("Debe elegir una fecha");
            return;
        }

        incidencia.setIdHora(horaDAO.obtener(cb_hora.getValue()));

        if (incidencia.getIdHora() == null) {
            AlertUtil.mostrarInfo("Debe elegir una hora");
            return;
        }

        if (tx_descripcion.getText().isBlank() || tx_descripcion.getText().length() > 255) {
            AlertUtil.mostrarInfo("La descripción debe ser menor a 255 carácteres y contener texto");
            return;
        }

        incidencia.setDescripcion(tx_descripcion.getText());

        PuntosPartesDAOImpl puntosPartesDAO = new PuntosPartesDAOImpl();
        incidencia.setIdPuntos(puntosPartesDAO.obtener(color));
        System.out.println(puntosPartesDAO.obtener(color));

        if (color != Color.ROJO) {
            incidencia.setSancion(tx_sancion.getText());

            if (incidencia.getSancion().isBlank() || incidencia.getSancion().length() > 255) {
                AlertUtil.mostrarInfo("La sanción debe ser menor a 255 carácteres y contener texto");
                return;
            }

        } else {
            incidencia.setSancion(opcionesSancioncb.getValue());

            // Comprueba si es otro o no
            if (opcionesSancioncb.getValue().equals(sanciones.toArray()[sanciones.size() - 1])) {
                incidencia.setSancion(sancionOtraTxArea.getText());

            } else {
                incidencia.setSancion(tx_sancion.getText());

            }

            if (incidencia.getSancion() == null) {
                AlertUtil.mostrarInfo("Debe elegir una sanción");
                return;
            }
        }

        IncidenciaDAOImpl incidenciaDAO = new IncidenciaDAOImpl();

        incidenciaDAO.modificar(incidencia);
    }

    public void onCancelarAction(ActionEvent actionEvent) {
        ((Stage) paneRojo.getScene().getWindow()).close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        habilitarCampos(false);
        editarBtt.setText("Modo edición");
        Platform.runLater(() -> {
            anchorPaneParte.layoutXProperty().bind(anchorPane.widthProperty().subtract(anchorPaneParte.widthProperty()).divide(2));
            anchorPaneParte.layoutYProperty().bind(anchorPane.heightProperty().subtract(anchorPaneParte.heightProperty()).divide(2));
        });
        opcionesSancioncb.setItems(sanciones);

        for (Hora h : horaDAO.listar()) {
            horas.add(h.getHora());
        }

        cb_hora.setItems(horas);
    }

    public void onEscrito(KeyEvent keyEvent) {
        try {
            alumno = alumnoDAO.obtener(Integer.parseInt(tf_nExpediente.getText()));

            if (alumno != null) tf_nombreGrupo.setText(alumno.getGrupo().getNombreGrupo());
            else tf_nombreGrupo.clear();

        } catch (Exception e) {
        }
    }

    @FXML
    void onParteNaranja(ActionEvent event) {
        paneRojo.setVisible(false);
        paneVerde.setVisible(true);
        anchorPaneParte.setStyle("-fx-background-color:#FFA500");
        lbTitulo.setText("PARTE NARANJA DE ADVERTENCIA");
        color = Color.NARANJA;
    }

    @FXML
    void onParteRojo(ActionEvent event) {
        paneRojo.setVisible(true);
        paneVerde.setVisible(false);
        anchorPaneParte.setStyle("-fx-background-color:#E64942");
        lbTitulo.setText("PARTE ROJO DE ADVERTENCIA");
        color = Color.ROJO;
    }

    @FXML
    void onParteVerde(ActionEvent event) {
        paneRojo.setVisible(false);
        paneVerde.setVisible(true);
        anchorPaneParte.setStyle("-fx-background-color: #befc77");
        lbTitulo.setText("PARTE VERDE DE ADVERTENCIA");
        color = Color.VERDE;
    }

    public void onBorrarAction(ActionEvent actionEvent) {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar el parte?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            IncidenciaDAOImpl incidenciaDAO = new IncidenciaDAOImpl();
            // ELIMINAMOS LA INCIDENCIA Y SE CIERRA LA VENTANA
            incidenciaDAO.eliminar(incidencia.getIdParte());
            onCancelarAction(actionEvent);
            /** luego se tendria que dar al boton de recargar **/
        }
    } // METODO PARA ELIMINAR PARTES
}
