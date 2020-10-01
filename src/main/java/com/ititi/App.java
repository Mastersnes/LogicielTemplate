package com.ititi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {
    protected Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("First Stage");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        primaryStage.show();
        primaryStage.centerOnScreen();

        final Stage secondStage = new Stage();
        secondStage.setTitle("Second Stage");
        secondStage.setWidth(400);
        secondStage.setHeight(300);
        secondStage.setResizable(false);
        secondStage.show();
        secondStage.centerOnScreen();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                Platform.runLater(secondStage::hide);
            } catch (final Exception e) {
                logger.log(Level.SEVERE, "Erreur de thread", e);
            }
        }).start();
    }

    public static void main(String[] args) {
        launch();
    }

}