package com.ititi.template.views;

import com.ititi.template.models.beans.Personne;
import com.ititi.template.models.dao.Fdd;
import com.ititi.template.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

/**
 * View du template PersonsEditDialog
 */
public class PersonsEditDialogController extends AbstractController<AnchorPane> {
    @FXML
    protected TextField prenom;
    @FXML
    protected TextField nom;
    @FXML
    protected TextField adresse;
    @FXML
    protected TextField ville;
    @FXML
    protected TextField codePostal;
    @FXML
    protected TextField dateNaissance;

    protected Personne personne;
    protected boolean isOk;

    public static boolean showDialog(final boolean add, final Personne personne, final Stage ownerStage) {
        final Stage dialogStage = new Stage();
        if (add) dialogStage.setTitle("Ajout d'une nouvelle personne");
        else dialogStage.setTitle("Modification de la personne");
        dialogStage.getIcons().add(new Image("com/ititi/template/images/icone.png"));
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(ownerStage);

        final PersonsEditDialogController editController = PersonsEditDialogController.create(dialogStage);
        dialogStage.setScene(new Scene(editController.getRoot()));
        editController.setPersonne(personne);

        dialogStage.showAndWait();

        return editController.isOk;
    }

    public static PersonsEditDialogController create(final Stage stage) {
        return create("com/ititi/template/views/PersonsEditDialog.fxml", stage, "com/ititi/template/views/DarkTheme.css");
    }

    @Override
    protected void initialize() {}

    @Override
    protected void afterCreate() {}

    public void setPersonne(final Personne personne) {
        this.personne = personne;
        prenom.setText(personne.getPrenom());
        nom.setText(personne.getNom());
        adresse.setText(personne.getAdresse());
        ville.setText(personne.getVille());
        codePostal.setText(personne.getCodePostal());
        dateNaissance.setText(DateUtils.format(personne.getDateNaissance()));
        dateNaissance.setPromptText("dd/MM/yyyy");
    }

    protected boolean checkInput() {
        final StringBuilder errorMessage = new StringBuilder();

        if (StringUtils.isEmpty(prenom.getText())) {
            errorMessage.append("Le prenom est vide.\n");
        }
        if (StringUtils.isEmpty(nom.getText())) {
            errorMessage.append("Le nom est vide.\n");
        }
        if (StringUtils.isEmpty(adresse.getText())) {
            errorMessage.append("L'adresse est vide.\n");
        }
        if (StringUtils.isEmpty(ville.getText())) {
            errorMessage.append("La ville est vide.\n");
        }

        if (StringUtils.isEmpty(codePostal.getText())) {
            errorMessage.append("Le code postal est vide.\n");
        }else {
            try {
                Integer.parseInt(codePostal.getText());
            } catch (final NumberFormatException e) {
                errorMessage.append("Le code postal doit être numérique.\n");
            }
        }

        if (StringUtils.isEmpty(dateNaissance.getText())) {
            errorMessage.append("La date de naissance est vide.\n");
        }else if (!DateUtils.check(dateNaissance.getText())) {
            errorMessage.append("La date de naissance doit être au format dd/MM/yyyy.\n");
        }

        if (errorMessage.length() == 0) return true;
        else {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Personne invalide");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return false;
        }
    }

    /**
     * Actions
     */
    @FXML
    protected void handleOk() {
        if (checkInput()) {
            personne.setPrenom(prenom.getText());
            personne.setNom(nom.getText());
            personne.setAdresse(adresse.getText());
            personne.setCodePostal(codePostal.getText());
            personne.setVille(ville.getText());
            personne.setDateNaissance(DateUtils.parse(dateNaissance.getText()));
            Fdd.getInstance().setModification(true);
            isOk = true;
            stage.close();
        }
    }
    @FXML
    protected void handleCancel() {
        isOk = false;
        stage.close();
    }
}