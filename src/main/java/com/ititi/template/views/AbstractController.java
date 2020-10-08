package com.ititi.template.views;

import com.ititi.template.App;
import com.ititi.template.models.Personne;
import com.ititi.template.utils.DateUtils;
import com.ititi.template.utils.LoaderUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.logging.Logger;

/**
 * Controller abstrait
 */
public class AbstractController<ROOT extends Pane> {
    protected Logger logger = Logger.getLogger(getClass().getName());
    protected App mainApp;
    protected ROOT root;

    public static <CONTROLLER extends AbstractController> CONTROLLER create(final String path, final App mainApp) {
        final FXMLLoader loader = LoaderUtils.load(path);
        final CONTROLLER controller = loader.getController();
        controller.mainApp = mainApp;
        controller.root = loader.getRoot();
        return controller;
    }

    public void ini

    public ROOT getRoot() {
        return root;
    }
    public App getMainApp() {
        return mainApp;
    }
}