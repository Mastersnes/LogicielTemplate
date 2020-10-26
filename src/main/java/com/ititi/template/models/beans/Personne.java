package com.ititi.template.models.beans;

import com.ititi.template.utils.LocalDateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Represente une personne
 */
public class Personne {
    private Integer id;

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

    public Integer getId() {return id;}
    public void setId(final Integer id) {this.id = id;}

    public String getNom() {return nom.get();}
    public void setNom(String nom) {this.nom.set(nom);}
    public StringProperty nomProperty() {return nom;}

    public String getPrenom() {return prenom.get();}
    public void setPrenom(String prenom) {this.prenom.set(prenom);}
    public StringProperty prenomProperty() {return prenom;}

    public String getAdresse() {return adresse.get(); }
    public void setAdresse(String adresse) {this.adresse.set(adresse);}
    public StringProperty adresseProperty() {return adresse;}

    public String getVille() {return ville.get();}
    public void setVille(String ville) {this.ville.set(ville);}
    public StringProperty villeProperty() {return ville;}

    public String getCodePostal() {return codePostal.get();}
    public void setCodePostal(String codePostal) {this.codePostal.set(codePostal);}
    public StringProperty codePostalProperty() {return codePostal;}

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDateNaissance() {return dateNaissance.get();}
    public void setDateNaissance(LocalDate dateNaissance) {this.dateNaissance.set(dateNaissance);}
    public ObjectProperty<LocalDate> dateNaissanceProperty() {return dateNaissance;}
}