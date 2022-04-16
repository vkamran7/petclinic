package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.PetType;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;

/**
 * @author Kamran Valizada
 */
@SpringDataJpaProfile
public interface PetTypeRepositoryOverride {
    void delete(PetType petType);
}
