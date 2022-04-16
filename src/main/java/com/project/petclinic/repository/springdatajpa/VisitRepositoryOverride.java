package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Visit;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;

/**
 * @author Kamran Valizada
 */
@SpringDataJpaProfile
public interface VisitRepositoryOverride {

    void delete(Visit visit);
}
