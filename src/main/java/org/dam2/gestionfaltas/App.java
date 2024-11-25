package org.dam2.gestionfaltas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dam2.gestionfaltas.controller.LoginCtrll;
import org.dam2.gestionfaltas.util.R;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestión faltas");
        stage.setScene(scene);

        LoginCtrll loginCtrll = fxmlLoader.getController();
        stage.setOnCloseRequest(e -> loginCtrll.close());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}