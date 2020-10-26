package com.ititi.template;

import com.ititi.template.models.beans.Personne;
import com.ititi.template.models.dao.Fdd;
import com.ititi.template.utils.Global;
import com.ititi.template.utils.PreferencesUtils;
import com.ititi.template.views.PersonsViewController;
import com.ititi.template.views.RootController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {
    public static final String TITRE = "Carnet d'adresse - ";
    protected Logger logger = Logger.getLogger(getClass().getName());
    protected ObservableList<Personne> datas = FXCollections.observableArrayList();

    public App() {
        Global.mainApp = this;

        datas.addListener((ListChangeListener<? super Personne>) change -> {
            Fdd.getInstance().setModification(true);
        });
    }

    public void newFile() {
        datas.clear();
        Fdd.getInstance().setCurrentFile(null);
        Fdd.getInstance().setModification(false);
    }
    public void loadFile(final File file) {
        if(file == null) return;
        datas.clear();
        datas.addAll(Fdd.getInstance().loadFromFile(file));
        if (Fdd.getInstance().getCurrentFile() != null)
            Fdd.getInstance().setModification(false);
    }
    public void saveFile(final File file) {Fdd.getInstance().saveToFile(file, datas);}

    public void refreshName() {
        final boolean modification = Fdd.getInstance().isModification();
        final String currentName = Fdd.getInstance().getCurrentFileName();

        final StringBuilder titre = new StringBuilder(TITRE);
        if (modification) titre.append("*");
        if (StringUtils.isNotEmpty(currentName)) titre.append(currentName);
        else titre.append("Nouveau document");

        Global.primaryStage.setTitle(titre.toString());
    }

    @Override
    public void start(final Stage primaryStage) {
        Global.primaryStage = primaryStage;

        loadFile(PreferencesUtils.loadLastFile());
        refreshName();
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("com/ititi/template/images/icone.png"));

        final RootController rootController = RootController.create(primaryStage);
        final BorderPane rootPane = rootController.getRoot();
        final Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);

        final PersonsViewController controller = PersonsViewController.create(primaryStage);
        rootPane.setCenter(controller.getRoot());

        primaryStage.show();
    }

    public ObservableList<Personne> getDatas() {
        return datas;
    }
    public static void main(String[] args) {
        launch();
    }

}