package com.ititi.template.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * Represente une personne
 */
public class Personne {
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty adresse;
    private StringProperty ville;
    private StringProperty codePostal;
    private ObjectProperty<LocalDate> dateNaissance;

    public Personne() {
        nom = new SimpleStringProperty();
        prenom = new SimpleStringProperty();
        adresse = new SimpleStringProperty();
        ville = new SimpleStringProperty();
        codePostal = new SimpleStringProperty();
        dateNaissance = new SimpleObjectProperty<>();
    }

    public static Personne create(final String prenom, final String nom) {
        final Personne personne = new Personne();
        personne.setNom(nom);
        personne.setPrenom(prenom);
        return personne;
    }

    public void setNom(String nom) {this.nom.set(nom);}
    public String getNom() {return nom.get();}
    public StringProperty nomProperty() {return nom;}

    public void setPrenom(String prenom) {this.prenom.set(prenom);}
    public String getPrenom() {return prenom.get();}
    public StringProperty prenomProperty() {return prenom;}

    public void setAdresse(String adresse) {this.adresse.set(adresse);}
    public String getAdresse() {return adresse.get(); }
    public StringProperty adresseProperty() {return adresse;}

    public void setVille(String ville) {this.ville.set(ville);}
    public String getVille() {return ville.get();}
    public StringProperty villeProperty() {return ville;}

    public void setCodePostal(String codePostal) {this.codePostal.set(codePostal);}
    public String getCodePostal() {return codePostal.get();}
    public StringProperty codePostalProperty() {return codePostal;}

    public void setDateNaissance(LocalDate dateNaissance) {this.dateNaissance.set(dateNaissance);}
    public LocalDate getDateNaissance() {return dateNaissance.get();}
    public ObjectProperty<LocalDate> dateNaissanceProperty() {return dateNaissance;}
}