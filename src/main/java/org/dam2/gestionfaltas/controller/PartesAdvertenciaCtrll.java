package org.dam2.gestionfaltas.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.dam2.gestionfaltas.dao.AlumnoDAOImpl;
import org.dam2.gestionfaltas.dao.HoraDAOImpl;
import org.dam2.gestionfaltas.dao.IncidenciaDAOImpl;
import org.dam2.gestionfaltas.dao.PuntosPartesDAOImpl;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.model.Hora;
import org.dam2.gestionfaltas.model.Incidencia;
import org.dam2.gestionfaltas.util.AlertUtil;
import org.dam2.gestionfaltas.util.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class PartesAdvertenciaCtrll implements Initializable {

    @FXML
    private AnchorPane anchoPaneParte;

    @FXML
    private Button bt_crear;

    @FXML
    private Button bt_parteNaranja;

    @FXML
    private Button bt_parteRojo;

    @FXML
    private Button bt_parteVerde;

    @FXML
    private ComboBox<String> cb_hora;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lbTitulo;

    @FXML
    private ComboBox<String> opcionesSancioncb;

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


    @FXML
    private TextArea sancionOtraTxArea;


    private Color color;
    private Alumno alumno;
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

    private final AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl();
    private final HoraDAOImpl horaDAO = new HoraDAOImpl();

    @FXML
    void onCrear(ActionEvent event) {
        Incidencia incidencia = new Incidencia();
        incidencia.setIdProfesor(MenuCtrll.profesor);

        if (alumno == null) {
            AlertUtil.mostrarInfo("Debe introducir un número de expediente válido");
            return;
        }

        incidencia.setIdAlumno(alumno);

        incidencia.setFecha(datePicker.getValue());

        if (incidencia.getFecha() == null ) {
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

        }else {
            incidencia.setSancion(opcionesSancioncb.getValue());

            // Comprueba si es otro o no
            if(opcionesSancioncb.getValue().equals(sanciones.toArray()[sanciones.size()-1])){
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

        incidenciaDAO.crear(incidencia);

    }

    @FXML
    void onParteNaranja(ActionEvent event) {
        paneRojo.setVisible(false);
        paneVerde.setVisible(true);
        anchoPaneParte.setStyle("-fx-background-color: orange;");
        lbTitulo.setText("PARTE NARANJA DE ADVERTENCIA");
        color = Color.NARANJA;
    }

    @FXML
    void onParteRojo(ActionEvent event) {
        paneRojo.setVisible(true);
        paneVerde.setVisible(false);
        anchoPaneParte.setStyle("-fx-background-color: red;");
        lbTitulo.setText("PARTE ROJO DE ADVERTENCIA");
        color = Color.ROJO;
    }

    @FXML
    void onParteVerde(ActionEvent event) {
        paneRojo.setVisible(false);
        paneVerde.setVisible(true);
        anchoPaneParte.setStyle("-fx-background-color: green;");
        lbTitulo.setText("PARTE VERDE DE ADVERTENCIA");
        color = Color.VERDE;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onParteVerde(new ActionEvent());
        System.out.println(color);

        opcionesSancioncb.setItems(sanciones);

        tf_profesor.setText(MenuCtrll.profesor.getNumeroAsignado());

        for (Hora h: horaDAO.listar()) {
            horas.add(h.getHora());
        }

        cb_hora.setItems(horas);
    }


    public void onEscrito(KeyEvent keyEvent) {
        try {
            alumno = alumnoDAO.obtener(Integer.parseInt(tf_nExpediente.getText()));

            if (alumno != null) tf_nombreGrupo.setText(alumno.getGrupo().getNombreGrupo());
            else tf_nombreGrupo.clear();

        }catch (Exception e) {}

    }

    @FXML
    void onClickSancionAction(ActionEvent event) {
        if(Objects.equals(opcionesSancioncb.getValue(), sanciones.toArray()[sanciones.size()-1])){
            sancionOtraTxArea.setVisible(true);
        } else {
            sancionOtraTxArea.setVisible(false);
        }
    }
}

