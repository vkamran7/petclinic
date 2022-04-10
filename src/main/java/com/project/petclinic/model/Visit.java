package com.project.petclinic.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * Simple JavaBean domain object representing a visit
 *
 * @author Kamran Valizada
 */
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "visit_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date date;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @Column(name = "scheduled")
    private Boolean scheduled;

    @Column(name = "ad_hoc")
    private Boolean adHoc;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    /**
     * Creates a new instance of Visit with the current date
     */
    public Visit() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Boolean getScheduled() {
        return scheduled;
    }

    public void setScheduled(Boolean scheduled) {
        this.scheduled = scheduled;
    }

    public Boolean getAdHoc() {
        return adHoc;
    }

    public void setAdHoc(Boolean adHoc) {
        this.adHoc = adHoc;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
