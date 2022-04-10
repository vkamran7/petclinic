package com.project.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Models a {@link Vet Vet's} speciality (for example, dentistry)
 *
 * @author Kamran Valizada
 */
@Entity
@Table(name = "specialities")
public class Specialty extends NamedEntity {

}
