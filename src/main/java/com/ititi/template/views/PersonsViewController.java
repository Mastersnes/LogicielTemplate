package com.ititi.template.views;

import com.ititi.template.models.beans.Personne;
import com.ititi.template.models.dao.Fdd;
import com.ititi.template.utils.DateUtils;
import com.ititi.template.utils.Global;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * View du template PersonsView
 */
public class PersonsViewController extends AbstractController<AnchorPane> {
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

    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    public PersonsViewController(){}

    public static PersonsViewController create(final Stage stage) {
        return AbstractController.create("com/ititi/template/views/PersonsView.fxml", stage, "com/ititi/template/views/DarkTheme.css");
    }

    @FXML
    @Override
    protected void initialize() {
        nomColonne.setCellValueFactory(data -> data.getValue().nomProperty());
        prenomColonne.setCellValueFactory(data -> data.getValue().prenomProperty());
        showPerson(null);

        personneTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> showPerson(newVal)
        );
        personneTable.setRowFactory(tv -> {
            final TableRow<Personne> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                    if (row.isEmpty()) addAction();
                    else editAction();
                }
            });
            return row;
        });
    }

    @Override
    protected void afterCreate() {
        personneTable.setItems(Global.mainApp.getDatas());
    }

    public void showPerson(final Personne personne) {
        personneTable.getSelectionModel().select(personne);
        if (personne != null) {
            deleteButton.setDisable(false);
            editButton.setDisable(false);
            prenomLabel.setText(personne.getPrenom());
            nomLabel.setText(personne.getNom());
            adresseLabel.setText(personne.getAdresse());
            villeLabel.setText(personne.getVille());
            codePostalLabel.setText(personne.getCodePostal());
            dateNaissanceLabel.setText(DateUtils.format(personne.getDateNaissance()));
        }else {
            deleteButton.setDisable(true);
            editButton.setDisable(true);
            prenomLabel.setText("");
            nomLabel.setText("");
            adresseLabel.setText("");
            villeLabel.setText("");
            codePostalLabel.setText("");
            dateNaissanceLabel.setText("");
        }
    }

    /**
     * Actions
     */
    @FXML
    protected void deleteAction() {
        final int lastSelectedPerson = personneTable.getSelectionModel().getSelectedIndex();
        if (lastSelectedPerson >= 0) {
            personneTable.getItems().remove(lastSelectedPerson);
        }else {
            final Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(stage);
            alert.setTitle("Aucune personne selectionné");
            alert.setContentText("Veuillez selectionner une personne pour la supprimer.");

            alert.showAndWait();
        }
    }

    @FXML
    protected void editAction() {
        final Personne personne = personneTable.getSelectionModel().getSelectedItem();
        if (personne != null) {
            if (PersonsEditDialogController.showDialog(false, personne, stage)) {
                showPerson(personne);
            }
        }else {
            final Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(stage);
            alert.setTitle("Personne non selectionnée");
            alert.setContentText("Veuillez selectionner une personne à editer.");
            alert.showAndWait();
        }
    }
    @FXML
    protected void addAction() {
        final Personne personne = new Personne();
        if (PersonsEditDialogController.showDialog(true, personne, stage)) {
            Global.mainApp.getDatas().add(personne);
            showPerson(personne);
        }
    }
}