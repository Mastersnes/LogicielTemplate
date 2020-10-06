package com.ititi.template;

import com.ititi.template.models.Personne;
import com.ititi.template.utils.Utils;
import com.ititi.template.views.PersonsViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jdk.jshell.execution.Util;

import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {
    protected Logger logger = Logger.getLogger(getClass().getName());
    protected ObservableList<Personne> datas = FXCollections.observableArrayList();

    public App() {
        datas.add(Personne.create("Lili", "Ni"));
        datas.add(Personne.create("Titi", "Poussin"));
        datas.add(Personne.create("Star", "Delongnez"));
        datas.add(Personne.create("Mao", "Lasticot"));
        datas.add(Personne.create("Zaloune", "Laloune"));
        datas.add(Personne.create("Filou-Fiche", "Lecamps"));
        datas.add(Personne.create("Hecate", "Mipmip"));
        datas.add(Personne.create("Selene", "Mipmip"));
        datas.add(Personne.create("Gros-Grugru", "Agrain"));
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Carnet d'adresse");
        primaryStage.setResizable(false);

        final BorderPane rootPane = Utils.load("com/ititi/template/views/RootView.fxml").getRoot();
        final Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);

        final PersonsViewController controller = PersonsViewController.create();
        rootPane.setCenter(controller.getRoot());
        controller.bind(this);

        primaryStage.show();
    }

    public ObservableList<Personne> getDatas() {
        return datas;
    }

    public static void main(String[] args) {
        launch();
    }

}