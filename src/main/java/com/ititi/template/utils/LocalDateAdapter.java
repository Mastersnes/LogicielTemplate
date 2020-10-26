package com.ititi.template.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

/**
 * Adapter permettant de convertir une localDate en String et viceversa
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(final String date) throws Exception {
        return LocalDate.parse(date);
    }

    @Override
    public String marshal(final LocalDate date) throws Exception {
        return date.toString();
    }
}