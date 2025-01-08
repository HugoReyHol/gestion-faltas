package org.dam2.gestionfaltas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dam2.gestionfaltas.util.HibernateUtil;
import org.dam2.gestionfaltas.util.R;
import javafx.scene.image.Image;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Inicializar la escena principal
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestión faltas");
        stage.setScene(scene);
        // AÑADIR IMAGEN EN EL STAGE DEL LOGO DE LA APLICACION
        stage.getIcons().add(new Image(R.getUI("Image/logoRiberaPequeño.png").toString()));
        // Cuando se cierre el primer stage cierra tambien la base de datos
        stage.setOnCloseRequest(e -> HibernateUtil.close());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}