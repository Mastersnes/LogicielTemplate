package com.ititi.template.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitaire permettant de faciliter le chargement d'un fxml
 */
public class LoaderUtils {
    protected static Logger logger = Logger.getLogger(LoaderUtils.class.getName());

    public static FXMLLoader load(final String path, final String... csss) {
        try {
            final InputStream stream = LoaderUtils.class.getClassLoader().getResourceAsStream(path);
            if (stream == null) throw new IOException("Le fichier est NULL");
            final FXMLLoader loader = new FXMLLoader();
            loader.load(stream);

            for (final String css : csss) {
                final Pane root = loader.getRoot();
                root.getStylesheets().add(css);
            }

            return loader;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erreur, impossible de charger le fichier : " + path, e);
        }
        return null;
    }
}