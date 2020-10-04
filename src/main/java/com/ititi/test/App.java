package com.ititi.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {
    protected Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Carnet d'adresse");
        primaryStage.setResizable(false);

        final BorderPane rootPane = load("views/RootView.fxml");
        final Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);

        final AnchorPane detailsPane = load("views/PersonsView.fxml");
        rootPane.setCenter(detailsPane);

        primaryStage.show();
    }

    public <T extends Pane> T load(final String path) {
        T pane = null;
        try {
            final InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
            if (stream == null) throw new IOException("Le fichier est NULL");
            final FXMLLoader loader = new FXMLLoader();
            pane = loader.load(stream);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erreur, impossible de charger le fichier", e);
        }
        return pane;
    }

    public static void main(String[] args) {
        launch();
    }

}