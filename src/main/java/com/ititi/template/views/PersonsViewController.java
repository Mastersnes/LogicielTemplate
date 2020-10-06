package com.ititi.template.views;

import com.ititi.template.App;
import com.ititi.template.models.Personne;
import com.ititi.template.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.logging.Logger;

/**
 * View du template PersonsView
 */
public class PersonsViewController {
    protected static Logger logger = Logger.getLogger(PersonsViewController.class.getName());

    private AnchorPane root;

    @FXML
    private TableView<Personne> personneTable;
    @FXML
    private TableColumn<Personne, String> nomColonne;
    @FXML
    private TableColumn<Personne, String> prenomColonne;

    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label codePostalLabel;
    @FXML
    private Label dateNaissanceLabel;

    private App mainApp;

    public PersonsViewController(){}

    @FXML
    private void initialize() {
        nomColonne.setCellValueFactory(data -> data.getValue().nomProperty());
        prenomColonne.setCellValueFactory(data -> data.getValue().prenomProperty());
    }

    public static PersonsViewController create() {
        final FXMLLoader loader = Utils.load("com/ititi/template/views/PersonsView.fxml");
        final PersonsViewController controller = loader.getController();
        controller.setRoot(loader.getRoot());
        return controller;
    }

    public void bind(final App mainApp) {
        this.mainApp = mainApp;
        personneTable.setItems(mainApp.getDatas());
    }

    public void setRoot(AnchorPane root) {this.root = root;}
    public AnchorPane getRoot() {return root;}
}