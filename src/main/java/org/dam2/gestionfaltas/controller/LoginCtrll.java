package org.dam2.gestionfaltas.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.dam2.gestionfaltas.dao.ProfesorDAOImpl;
import org.dam2.gestionfaltas.model.Profesor;
import org.dam2.gestionfaltas.util.AlertUtil;
import org.dam2.gestionfaltas.util.Encriptador;
import org.dam2.gestionfaltas.util.R;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginCtrll implements Initializable {

    @FXML
    public TextField inNumero;
    @FXML
    public PasswordField inContrasena;
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public ImageView imageView;
    @FXML
    public VBox verticalBox;

    private final ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();

    public void onLogIn(ActionEvent actionEvent) {
        // Comprueba si los campos están vacios
        if (inNumero.getText().isBlank()) {
            AlertUtil.mostrarInfo("El número asignado no puede estar vacío ");
            return;
        }
        if (inContrasena.getText().isBlank()) {
            AlertUtil.mostrarInfo("La contraseña no puede estar vacía ");
            return;
        }

        // Comprueba si el campo contiene 4 letras o menos
        if (inNumero.getText().length() > 4) {
            AlertUtil.mostrarInfo("El número asignado no puede tener más de 4 letras");
            return;
        }

        // Compruba si el usuario existe y en caso de existir si a contraseña es correcta
        Profesor profesor = profesorDAO.obtener(inNumero.getText());

        if (profesor == null) {
            AlertUtil.mostrarInfo("El profesor " + inNumero.getText() + " no existe");
            return;
        }

        if (!profesor.getContrasena().equals(Encriptador.encriptar(inContrasena.getText()))) {
            AlertUtil.mostrarInfo("Contraseña incorrecta, inténtelo de nuevo");
            return;
        }

        // Cambia a escena menu y pasa el profesor iniciado
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("vistaMenu.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Error al cargar la escena vistaMenu");
            return;
        }

        MenuCtrll.profesor = profesor;
        ((MenuCtrll) fxmlLoader.getController()).actualizarBotones();

        ((Stage) inNumero.getScene().getWindow()).setScene(scene);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(anchorPane.widthProperty());
        imageView.fitHeightProperty().bind(anchorPane.heightProperty());
        Platform.runLater(() -> {
            verticalBox.layoutXProperty().bind(anchorPane.widthProperty().subtract(verticalBox.widthProperty()).divide(2));
            verticalBox.layoutYProperty().bind(anchorPane.heightProperty().subtract(verticalBox.heightProperty()).divide(2.2));
        });
    }
}