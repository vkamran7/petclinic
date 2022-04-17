package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Vet;
import com.project.petclinic.repository.VetRepository;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;
import org.springframework.data.repository.Repository;

@SpringDataJpaProfile
public interface SpringDataVetRepository extends VetRepository, Repository<Vet, Integer> {

}
