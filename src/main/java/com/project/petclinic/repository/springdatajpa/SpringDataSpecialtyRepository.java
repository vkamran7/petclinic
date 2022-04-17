package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Specialty;
import com.project.petclinic.repository.SpecialtyRepository;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;
import org.springframework.data.repository.Repository;

@SpringDataJpaProfile
public interface SpringDataSpecialtyRepository extends SpecialtyRepository, Repository<Specialty, Integer>, SpecialtyRepositoryOverride {

}
