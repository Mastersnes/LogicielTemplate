package com.ititi.template.utils;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitaire
 */
public class Utils {
    protected static Logger logger = Logger.getLogger(Utils.class.getName());

    public static FXMLLoader load(final String path) {
        try {
            final InputStream stream = Utils.class.getClassLoader().getResourceAsStream(path);
            if (stream == null) throw new IOException("Le fichier est NULL");
            final FXMLLoader loader = new FXMLLoader();
            loader.load(stream);
            return loader;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erreur, impossible de charger le fichier", e);
        }
        return null;
    }
}