package com.ititi.template.models.dto;

import com.ititi.template.models.beans.Personne;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represente une personne
 */
@Entity
@Table(name = "Personne")
public class PersonneDto {
    @Id
    @Column(name="ID", unique = true, nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "ADRESSE")
    private String adresse;
    @Column(name = "VILLE")
    private String ville;
    @Column(name = "CODE_POSTAL")
    private String codePostal;
    @Column(name = "DATE_NAISSANCE")
    private LocalDate dateNaissance;

    public PersonneDto() {}
    public PersonneDto(Personne bean) {
        setId(bean.getId());
        setNom(bean.getNom());
        setPrenom(bean.getPrenom());
        setAdresse(bean.getAdresse());
        setVille(bean.getVille());
        setCodePostal(bean.getCodePostal());
        setDateNaissance(bean.getDateNaissance());
    }

    public static List<Personne> toBean(final List<PersonneDto> dtos) {
        final List<Personne> beans = new ArrayList<>();
        dtos.stream().forEach(dto -> beans.add(dto.toBean()));
        return beans;
    }

    public Personne toBean() {
        final Personne bean = new Personne();
        bean.setId(id);
        bean.setNom(nom);
        bean.setPrenom(prenom);
        bean.setAdresse(adresse);
        bean.setVille(ville);
        bean.setCodePostal(codePostal);
        bean.setDateNaissance(dateNaissance);
        return bean;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}

    public String getVille() {return ville;}
    public void setVille(String ville) {this.ville = ville;}

    public String getCodePostal() {return codePostal;}
    public void setCodePostal(String codePostal) {this.codePostal = codePostal;}

    public LocalDate getDateNaissance() {return dateNaissance;}
    public void setDateNaissance(LocalDate dateNaissance) {this.dateNaissance = dateNaissance;}
}