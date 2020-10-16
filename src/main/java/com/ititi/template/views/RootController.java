package com.ititi.template.views;

import com.ititi.template.utils.Global;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    private void newAction() {
        Global.mainApp.getDatas().clear();
        Global.mainApp.saveToFile(null);
    }
}