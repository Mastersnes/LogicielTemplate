package com.ititi.template.views;

import com.ititi.template.utils.Global;
import com.sun.xml.bind.v2.schemagen.xmlschema.ExtensionType;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
    protected void afterCreate() {}

    @FXML
    private void newAction() {Global.mainApp.loadFile(null);}

    @FXML
    private void openAction() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));

        final File file = fileChooser.showOpenDialog(Global.primaryStage);
        if (file != null) Global.mainApp.loadFile(file);
    }
}