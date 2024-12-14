package org.dam2.gestionfaltas.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.dam2.gestionfaltas.App;

import java.io.IOException;

public class CambiarVista {
    public static void cambiarVistaBtt(String nameFxml, Button button, String title) {
        try {
            // CARGAR EL ARCHIVO FXML
            FXMLLoader fxmlLoader = new
                    FXMLLoader(R.getUI(nameFxml));
            Parent root = fxmlLoader.load();
            // OBTENER CONTROLLER
            Object controller = fxmlLoader.getController();
            Scene scene = new Scene(root); // CREAR UNA NUEVA ESCENA
            // OBTENER EL STAGE ACTUAL A PARTIR DEL BOTON QUE SE HA CLICADO
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setTitle(title); // TITULO DE LA VENTANA
            stage.setScene(scene); // ESTABLECER LA NUEVA ESCENA AL STAGE ACTUAL
            // MOSTRAR VENTANA SI NO ESTA VISIBLE
            if (!stage.isShowing()) {
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace(); // SI HAY ERROR EN LA CARGA DEL FXML, SE LANZA LA EXCEPCION
        }
    } // METODO ESTATICO PARA CAMBIAR DE VISTA CON UN ID DE UN BOTON
}
