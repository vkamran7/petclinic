package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Visit;
import com.project.petclinic.repository.VisitRepository;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;
import org.springframework.data.repository.Repository;

@SpringDataJpaProfile
public interface SpringDataVisitRepository extends VisitRepository, Repository<Visit, Integer>, VisitRepositoryOverride {
}
