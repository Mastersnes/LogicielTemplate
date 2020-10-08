package com.ititi.template.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

/**
 * Classe utilitaire permettant de manipuler les dates
 */
public class DateUtils {
    protected static Logger logger = Logger.getLogger(DateUtils.class.getName());
    protected static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String format(final LocalDate date) {
        if (date == null) return null;
        else return FORMATTER.format(date);
    }

    public static LocalDate parse(final String date) {
        try {
            if (date == null) return null;
            else return FORMATTER.parse(date, LocalDate::from);
        } catch (final DateTimeParseException e) {
            return null;
        }
    }

    public static boolean check(final String date) {return parse(date) != null;}
}