package org.dam2.gestionfaltas.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.controlsfx.tools.Platform;
import org.dam2.gestionfaltas.dao.*;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.model.Hora;
import org.dam2.gestionfaltas.model.Incidencia;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.AlertUtil;
import org.dam2.gestionfaltas.util.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;


public class MostrarParteCtrll implements Initializable {
    public AnchorPane anchoPaneParte;
    public Label lbTitulo;
    public TextField tf_nExpediente;
    public DatePicker datePicker;
    public ComboBox<String> cb_hora;
    public TextArea tx_descripcion;
    public TextField tf_profesor;
    public TextField tf_nombreGrupo;
    public Pane paneVerde;
    public TextArea tx_sancion;
    public Pane paneRojo;
    @FXML
    private TextArea sancionOtraTxArea;
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

    public void setIncidencia(Incidencia incidencia){
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

        if (incidencia.getSancion().equals(sanciones.toArray()[sanciones.size()-1])) {
            opcionesSancioncb.setValue((String) sanciones.toArray()[sanciones.size()-1]);
            sancionOtraTxArea.setText(incidencia.getSancion());
            sancionOtraTxArea.setVisible(true);

        } else opcionesSancioncb.setValue(incidencia.getSancion());

        switch (color) {
            case VERDE -> {
                paneVerde.setVisible(true);
                paneRojo.setVisible(false);
                anchoPaneParte.setStyle("-fx-background-color: green;");
            }
            case NARANJA -> {
                paneVerde.setVisible(true);
                paneRojo.setVisible(false);
                anchoPaneParte.setStyle("-fx-background-color: orange;");
            }
            case ROJO -> {
                paneVerde.setVisible(false);
                paneRojo.setVisible(true);
                anchoPaneParte.setStyle("-fx-background-color: red;");
            }
        }
    }

    public void onClickSancionAction(ActionEvent actionEvent) {
        if(opcionesSancioncb.getValue().equals(sanciones.toArray()[sanciones.size()-1])){
            sancionOtraTxArea.setVisible(true);
        } else {
            sancionOtraTxArea.setVisible(false);
        }
    }

    public void onEditarAction(ActionEvent actionEvent) {
        ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();
        Profesor p = profesorDAO.obtener(tf_profesor.getText());
        if ( p == null) {
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

        incidenciaDAO.modificar(incidencia);

        onCancelarAction(actionEvent);
    }

    public void onCancelarAction(ActionEvent actionEvent) {
        ((Stage) paneRojo.getScene().getWindow()).close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        opcionesSancioncb.setItems(sanciones);

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
}
