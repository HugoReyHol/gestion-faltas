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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.dam2.gestionfaltas.dao.AlumnoDAOImpl;
import org.dam2.gestionfaltas.model.Alumno;
import org.dam2.gestionfaltas.util.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
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

    private Color color;
    private Alumno alumno;
    private final ObservableList<String> horas = FXCollections.observableArrayList();
    private final ObservableList<String> sanciones = FXCollections.observableArrayList(
            "Incoación de expediente o en su caso \nexpediente abreviado",
            "Reunión con la Comisión de Convivencia",
            """
            Es obligado pedir disculpas a la persona/as
            contra las que se ejerció daño físico o moral,
            y/o reparar los daños materiales causados""");

    private final AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl();

    @FXML
    void onCrear(ActionEvent event) {



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

        opcionesSancioncb.setItems(sanciones);

        tf_profesor.setText(MenuCtrll.profesor.getNumeroAsignado());

        ObjectMapper JSON_MAPPER = new ObjectMapper();

        try {
            Map<String, List<String>> json = JSON_MAPPER.readValue(
                    new File("src/main/resources/variables_externas/horario.json"),
                    new TypeReference<>() {
                    });

            horas.addAll(json.get("horas"));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        cb_hora.setItems(horas);
    }


    public void onEscrito(KeyEvent keyEvent) {
        try {
            alumno = alumnoDAO.obtener(Integer.parseInt(tf_nExpediente.getText()));

            if (alumno != null) tf_nombreGrupo.setText(alumno.getGrupo().getNombreGrupo());

        }catch (Exception e) {}

    }
}

