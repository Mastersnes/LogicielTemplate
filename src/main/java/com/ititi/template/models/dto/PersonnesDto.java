package com.ititi.template.models.dto;

import com.ititi.template.models.beans.Personne;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Represente une liste de personne
 */
@XmlRootElement(name = "Personnes")
public class PersonnesDto {
    private List<Personne> personnes;

    @XmlElement(name = "Personne")
    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }
}