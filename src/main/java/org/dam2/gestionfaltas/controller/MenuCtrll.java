package org.dam2.gestionfaltas.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.CambiarVista;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.dam2.gestionfaltas.util.R;
import org.dam2.gestionfaltas.util.Tipos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuCtrll implements Initializable {

    @FXML
    private HBox hbContenido;
    @FXML
    private Pane paneMenu;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button bt_crearParte;
    @FXML
    private Button bt_crearProfesor;
    @FXML
    private Button bt_listaAlumnos;
    @FXML
    private Button bt_listaPartes;
    @FXML
    private Button bt_cerrarSesion;

    public static Profesor profesor;

    public void cambiarEscena(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI(fxml));
        AnchorPane anchorPane1 = fxmlLoader.load();

        hbContenido.getChildren().clear();
        hbContenido.getChildren().add(anchorPane1);
    }
    @FXML
    void onCrearParte(ActionEvent event) throws IOException {
        cambiarEscena("partesAdvertencia.fxml");
    } // IR A LA VISTA DE CREAR PARTE

    @FXML
    void onCrearProfesor(ActionEvent event) throws IOException {
        cambiarEscena("crearProfesor.fxml");
    } // IR A LA VISTA DE CREAR PROFESORES

    @FXML
    void onListaAlumnos(ActionEvent event) throws IOException {
        cambiarEscena("listaAlumnos.fxml");
    } // IR A LA VISTA DE LISTA DE ALUMNOS

    @FXML
    void onListaPartes(ActionEvent event) throws IOException {
        cambiarEscena("listaPartes.fxml");
    } // IR A LA VISTA DE LISTA DE PARTES

    public void actualizarBotones() {
        if (profesor.getTipo() == Tipos.PROFESOR) {
            bt_crearProfesor.setVisible(false);
            bt_listaAlumnos.setVisible(false);
            bt_listaPartes.setVisible(false);
        }
    }

    @FXML
    void onCerrarSesionAction(ActionEvent event) {
        profesor = null; // DESHABILITAR PROFESOR
        CambiarVista.cambiarVistaBtt("login.fxml", bt_cerrarSesion, "Iniciar sesion"); // IR A LA VISTA LOGIN
    } // BOTON PARA CERRAR SESSION

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (profesor != null) {
            actualizarBotones();
        }
        hbContenido.prefWidthProperty().bind(anchorPane.widthProperty());
        hbContenido.prefHeightProperty().bind(anchorPane.heightProperty());
        Platform.runLater(() -> {
            paneMenu.prefWidthProperty().bind(anchorPane.widthProperty());
            paneMenu.layoutYProperty().set(0);
        });
        bt_cerrarSesion.layoutYProperty().bind(paneMenu.heightProperty().subtract(bt_cerrarSesion.heightProperty()).divide(2));
        bt_cerrarSesion.layoutXProperty().bind(paneMenu.widthProperty().subtract(bt_cerrarSesion.widthProperty()).subtract(20));
    }
}