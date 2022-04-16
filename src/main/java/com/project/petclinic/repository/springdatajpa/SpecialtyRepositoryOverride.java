package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Specialty;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;

/**
 * @author Kamran Valizada
 */
@SpringDataJpaProfile
public interface SpecialtyRepositoryOverride {

    void delete(Specialty specialty);
}
