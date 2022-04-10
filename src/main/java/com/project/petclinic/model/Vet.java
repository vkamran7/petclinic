package com.project.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

/**
 * Simple JavaBean domain object representing a veterinarian
 *
 * @author Kamran Valizada
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

    @JsonIgnore
    protected Set<Specialty> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<>();
        }
        return this.specialties;
    }

    protected void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    @XmlElement
    public List<Specialty> getSpecialties() {
        List<Specialty> sortedSpecialty = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecialty, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecialty);
    }

    @JsonIgnore
    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public void addSpecialty(Specialty specialty) {
        getSpecialtiesInternal().add(specialty);
    }

    public void clearSpecialties() {
        getSpecialtiesInternal().clear();
    }
}
