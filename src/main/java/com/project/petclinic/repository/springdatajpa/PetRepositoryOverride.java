package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Pet;
import org.springframework.context.annotation.Profile;

/**
 * @author Kamran Valizada
 */
@Profile("spring-data-jpa")
public interface PetRepositoryOverride {
    void delete(Pet pet);
}
