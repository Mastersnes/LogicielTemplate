package com.ititi.template;

import com.ititi.template.models.beans.Personne;
import com.ititi.template.models.dao.Bdd;
import com.ititi.template.models.dto.PersonnesDto;
import com.ititi.template.utils.Global;
import com.ititi.template.utils.LoaderUtils;
import com.ititi.template.utils.PreferencesUtils;
import com.ititi.template.views.PersonsViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {
    protected Logger logger = Logger.getLogger(getClass().getName());
    protected Stage primaryStage;
    protected ObservableList<Personne> datas = FXCollections.observableArrayList();

    public App() {
        Global.mainApp = this;
        datas.addAll(Bdd.getInstance().listAll());
        datas.addListener((ListChangeListener<? super Personne>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) Bdd.getInstance().remove(change.getRemoved().get(0));
                else if (change.wasAdded()) Bdd.getInstance().save(change.getAddedSubList().get(0));
            }
        });
    }

    @Override
    public void start(final Stage primaryStage) {
        Global.primaryStage = primaryStage;
        primaryStage.setTitle("Carnet d'adresse");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("com/ititi/template/images/icone.png"));

        final BorderPane rootPane = LoaderUtils.load(
                "com/ititi/template/views/RootView.fxml",
                "com/ititi/template/views/DarkTheme.css")
                .getRoot();
        final Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);

        final PersonsViewController controller = PersonsViewController.create(primaryStage);
        rootPane.setCenter(controller.getRoot());
        primaryStage.show();
    }

    public void loadLastFile() {loadFromFile(PreferencesUtils.loadLastFile());}
    public void loadFromFile(final File file) {
        if (file == null) return;
        try {
            final JAXBContext context = JAXBContext.newInstance(PersonnesDto.class);
            final Unmarshaller um = context.createUnmarshaller();

            final PersonnesDto personnes = (PersonnesDto) um.unmarshal(file);

            datas.clear();
            datas.addAll(personnes.getPersonnes());
            PreferencesUtils.saveLastFile(file);
            primaryStage.setTitle("Carnet d'adresse - " + file.getPath());
        } catch (final JAXBException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Impossible de charger le fichier: " + file.getPath());
            alert.showAndWait();
        }
    }

    public void saveToFile(final File file) {
        if (file == null) return;
        try {
            final JAXBContext context = JAXBContext.newInstance(PersonnesDto.class);
            final Marshaller m = context.createMarshaller();

            final PersonnesDto personnes = new PersonnesDto();
            personnes.setPersonnes(datas);

            m.marshal(personnes, file);
            PreferencesUtils.saveLastFile(file);
        } catch (final JAXBException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Impossible de sauvegarder le fichier: " + file.getPath());
            alert.showAndWait();
        }
    }

    public ObservableList<Personne> getDatas() {
        return datas;
    }
    public Stage getPrimaryStage() {return primaryStage;}

    public static void main(String[] args) {
        launch();
    }

}