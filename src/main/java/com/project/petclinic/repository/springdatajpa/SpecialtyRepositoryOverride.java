package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Specialty;
import org.springframework.context.annotation.Profile;

/**
 * @author Kamran Valizada
 */
@Profile("spring-data-jpa")
public interface SpecialtyRepositoryOverride {

    void delete(Specialty specialty);
}
