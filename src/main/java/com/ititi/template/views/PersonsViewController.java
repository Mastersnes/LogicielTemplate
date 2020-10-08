package com.ititi.template.views;

import com.ititi.template.App;
import com.ititi.template.models.Personne;
import com.ititi.template.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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

    public PersonsViewController(){}

    public static PersonsViewController create(final App mainApp) {
        return AbstractController.create("com/ititi/template/views/PersonsView.fxml", mainApp);
    }

    @FXML
    private void initialize() {
        personneTable.setItems(mainApp.getDatas());
        nomColonne.setCellValueFactory(data -> data.getValue().nomProperty());
        prenomColonne.setCellValueFactory(data -> data.getValue().prenomProperty());
        showPerson(null);

        personneTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> showPerson(newVal)
        );
    }

    public void showPerson(final Personne personne) {
        if (personne != null) {
            deleteButton.setDisable(false);
            prenomLabel.setText(personne.getPrenom());
            nomLabel.setText(personne.getNom());
            adresseLabel.setText(personne.getAdresse());
            villeLabel.setText(personne.getVille());
            codePostalLabel.setText(personne.getCodePostal());
            dateNaissanceLabel.setText(DateUtils.format(personne.getDateNaissance()));
        }else {
            deleteButton.setDisable(true);
            prenomLabel.setText("");
            nomLabel.setText("");
            adresseLabel.setText("");
            villeLabel.setText("");
            codePostalLabel.setText("");
            dateNaissanceLabel.setText("");
        }
    }

    @FXML
    protected void deleteAction() {
        final int lastSelectedPerson = personneTable.getSelectionModel().getSelectedIndex();
        if (lastSelectedPerson >= 0) {
            personneTable.getItems().remove(lastSelectedPerson);
        }else {
            final Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune personne selectionné");
            alert.setContentText("");
        }
    }
}