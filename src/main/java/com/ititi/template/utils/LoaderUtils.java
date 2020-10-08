package com.ititi.template.utils;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitaire permettant de faciliter le chargement d'un fxml
 */
public class LoaderUtils {
    protected static Logger logger = Logger.getLogger(LoaderUtils.class.getName());

    public static FXMLLoader load(final String path) {
        try {
            final InputStream stream = LoaderUtils.class.getClassLoader().getResourceAsStream(path);
            if (stream == null) throw new IOException("Le fichier est NULL");
            final FXMLLoader loader = new FXMLLoader();
            loader.load(stream);
            return loader;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erreur, impossible de charger le fichier : " + path, e);
        }
        return null;
    }
}