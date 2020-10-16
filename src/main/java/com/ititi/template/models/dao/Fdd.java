package com.ititi.template.models.dao;

import com.ititi.template.models.beans.Personne;
import com.ititi.template.models.dto.PersonneDto;
import com.ititi.template.models.dto.PersonnesDto;
import com.ititi.template.utils.PreferencesUtils;
import javafx.scene.control.Alert;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO de communication avec la BDD Fichier XML
 */
public class Fdd {
    protected static Fdd instance;
    protected Logger logger = Logger.getLogger(getClass().getName());
    protected File currentFile;

    private Fdd() {
    }

    public static Fdd getInstance() {
        if (instance == null) {
            instance = new Fdd();
        }
        return instance;
    }

    public File loadLastFile() {loadFromFile(PreferencesUtils.loadLastFile());}
    public File loadFromFile(final File file) {
        if (file == null) return loadLastFile();
        try {
            final JAXBContext context = JAXBContext.newInstance(PersonnesDto.class);
            final Unmarshaller um = context.createUnmarshaller();

            final PersonnesDto personnes = (PersonnesDto) um.unmarshal(file);

            PreferencesUtils.saveLastFile(file);
            currentFile = file;
        } catch (final JAXBException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Impossible de charger le fichier: " + file.getPath());
            alert.showAndWait();
        }
    }

    public void saveToFile(final File file) {
        if (file == null){
            if (currentFile != null) saveToFile(currentFile);
            return;
        }
        try {
            final JAXBContext context = JAXBContext.newInstance(PersonnesDto.class);
            final Marshaller m = context.createMarshaller();

            final PersonnesDto personnes = new PersonnesDto();
            personnes.setPersonnes(datas);

            m.marshal(personnes, file);
            PreferencesUtils.saveLastFile(file);
        } catch (final JAXBException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Impossible de sauvegarder le fichier: " + file.getPath());
            alert.showAndWait();
        }
    }

}