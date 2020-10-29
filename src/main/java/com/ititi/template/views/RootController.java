package com.ititi.template.views;

import com.ititi.template.models.dao.Fdd;
import com.ititi.template.utils.Global;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

/**
 * View du template RootView
 */
public class RootController extends AbstractController<BorderPane> {
    public RootController(){}

    public static RootController create(final Stage stage) {
        return AbstractController.create("com/ititi/template/views/RootView.fxml", stage, "com/ititi/template/views/DarkTheme.css");
    }

    @Override
    protected void initialize() {}
    @Override
    protected void afterCreate() {
        Global.primaryStage.setOnCloseRequest(e -> exitAction());
    }

    @FXML
    private void newAction() {
        checkModif();
        Global.mainApp.newFile();
    }

    @FXML
    private void openAction() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));

        final File file = fileChooser.showOpenDialog(Global.primaryStage);
        if (file != null) Global.mainApp.loadFile(file);
    }

    @FXML
    private void saveAction() {
        final File current = Fdd.getInstance().getCurrentFile();
        if (current != null) Global.mainApp.saveFile(current);
        else saveAsAction();
    }

    @FXML
    private void saveAsAction() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));

        File file = fileChooser.showSaveDialog(Global.primaryStage);
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            Global.mainApp.saveFile(file);
        }
    }

    @FXML
    private void exitAction() {
        checkModif();
        System.exit(0);
    }

    private void checkModif() {
        if (Fdd.getInstance().isModification()) {
            final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(new ButtonType[] {ButtonType.YES, ButtonType.NO});
            alert.setTitle("Modification non enregistrées");
            alert.setContentText("Attention, des modifications ont été apportées. Souhaitez-vous les sauvegarder ?");
            final Optional<ButtonType> type = alert.showAndWait();
            if (type.isPresent() && type.get() == ButtonType.YES) saveAction();
        }
    }
}