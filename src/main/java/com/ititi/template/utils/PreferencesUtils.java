package com.ititi.template.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * Classe utilitaire permettant de sauvegarder/charger des preferences utilisateur
 */
public class PreferencesUtils {
    protected static Logger logger = Logger.getLogger(PreferencesUtils.class.getName());

    public static File loadLastFile() {
        final String filePath = load("lastFile");
        if (StringUtils.isNotEmpty(filePath)) {
            final File file = new File(filePath);
            if (file.exists()) return file;
        }
        return null;
    }
    public static String load(final String key) {return load(key, null);}
    public static String load(final String key, final String defValue) {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesUtils.class);
        return prefs.get(key, defValue);
    }

    public static void saveLastFile(final File file) {
        save("lastFile", file.getAbsolutePath());
    }
    public static void save(final String key, final String value) {
        final Preferences prefs = Preferences.userNodeForPackage(PreferencesUtils.class);
        if (StringUtils.isNotEmpty(value)) prefs.put(key, value);
        else prefs.remove(key);
    }
}