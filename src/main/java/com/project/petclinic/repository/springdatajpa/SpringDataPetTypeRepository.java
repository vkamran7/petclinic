package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.PetType;
import com.project.petclinic.repository.PetTypeRepository;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;
import org.springframework.data.repository.Repository;

@SpringDataJpaProfile
public interface SpringDataPetTypeRepository extends PetTypeRepository, Repository<PetType, Integer>, PetTypeRepositoryOverride {
}
