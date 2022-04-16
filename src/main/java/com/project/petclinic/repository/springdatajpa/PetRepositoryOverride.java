package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Pet;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;

/**
 * @author Kamran Valizada
 */
@SpringDataJpaProfile
public interface PetRepositoryOverride {
    void delete(Pet pet);
}
