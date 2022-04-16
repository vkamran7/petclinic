package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Visit;
import org.springframework.context.annotation.Profile;

/**
 * @author Kamran Valizada
 */
@Profile("spring-data-jpa")
public interface VisitRepositoryOverride {

    void delete(Visit visit);
}
