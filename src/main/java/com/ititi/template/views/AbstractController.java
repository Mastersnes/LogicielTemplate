package com.ititi.template.views;

import com.ititi.template.utils.LoaderUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controller abstrait
 */
public abstract class AbstractController<ROOT extends Pane> {
    protected Stage stage;
    protected ROOT root;

    public static <CONTROLLER extends AbstractController> CONTROLLER create(final String path, final Stage stage, final String... csss) {
        final FXMLLoader loader = LoaderUtils.load(path, csss);
        if (loader != null) {
            final CONTROLLER controller = loader.getController();
            controller.stage = stage;
            controller.root = loader.getRoot();
            controller.afterCreate();
            return controller;
        }else return null;
    }

    protected abstract void initialize();
    protected abstract void afterCreate();

    public ROOT getRoot() {
        return root;
    }
    public Stage getStage() {return stage;}
}