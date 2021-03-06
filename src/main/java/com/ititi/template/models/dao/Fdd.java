package com.ititi.template.models.dao;

import com.ititi.template.models.beans.Personne;
import com.ititi.template.models.dto.PersonnesDto;
import com.ititi.template.utils.Global;
import com.ititi.template.utils.PreferencesUtils;
import javafx.scene.control.Alert;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * DAO de communication avec la BDD Fichier XML
 */
public class Fdd {
    protected static Fdd instance;
    protected Logger logger = Logger.getLogger(getClass().getName());
    protected File currentFile;
    protected boolean modification;

    private Fdd() {
    }

    public static Fdd getInstance() {
        if (instance == null) {
            instance = new Fdd();
        }
        return instance;
    }

    public List<Personne> loadLastFile() {return loadFromFile(PreferencesUtils.loadLastFile());}
    public List<Personne> loadFromFile(final File file) {
        currentFile = null;
        if (file != null) {
            try {
                final JAXBContext context = JAXBContext.newInstance(PersonnesDto.class);
                final Unmarshaller um = context.createUnmarshaller();

                final PersonnesDto personnes = (PersonnesDto) um.unmarshal(file);

                PreferencesUtils.saveLastFile(file);
                setCurrentFile(file);
                if (personnes != null) return personnes.getPersonnes();
            } catch (final JAXBException e) {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Impossible de charger le fichier: " + file.getPath());
                alert.showAndWait();
            }
        }
        return new ArrayList<>();
    }

    public void saveCurrentFile(final List<Personne> datas) {saveToFile(currentFile, datas);}
    public void saveToFile(final File file, final List<Personne> datas) {
        if (file == null) return;
        try {
            final JAXBContext context = JAXBContext.newInstance(PersonnesDto.class);
            final Marshaller m = context.createMarshaller();

            final PersonnesDto personnes = new PersonnesDto();
            personnes.setPersonnes(datas);

            m.marshal(personnes, file);
            PreferencesUtils.saveLastFile(file);
            setCurrentFile(file);
            setModification(false);
        } catch (final JAXBException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Impossible de sauvegarder le fichier: " + file.getPath());
            alert.showAndWait();
        }
    }

    public String getCurrentFileName() {
        if (currentFile != null) return currentFile.getName();
        else return StringUtils.EMPTY;
    }

    public File getCurrentFile() {return currentFile;}
    public void setCurrentFile(final File currentFile) {
        this.currentFile = currentFile;
    }

    public void setModification(boolean modification) {
        this.modification = modification;
        Global.mainApp.refreshName();
    }
    public boolean isModification() {return modification;}
}