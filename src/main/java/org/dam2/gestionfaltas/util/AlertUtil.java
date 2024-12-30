package org.dam2.gestionfaltas.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class AlertUtil {
    public static void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void mostrarInfo(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /*
    * Devuelve true si acepta o false si cancela
    * */
    public static boolean crearConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();

        return alert.getResult() != ButtonType.CANCEL;
    }
}
