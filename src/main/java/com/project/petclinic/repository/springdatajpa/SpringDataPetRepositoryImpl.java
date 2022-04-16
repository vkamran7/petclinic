package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Pet;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringDataJpaProfile
public class SpringDataPetRepositoryImpl implements PetRepositoryOverride {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(Pet pet) {
        String petId = pet.getId().toString();
        this.entityManager.createQuery("DELETE FROM Visit visit WHERE visit.pet.id = " + petId).executeUpdate();
        this.entityManager.createQuery("DELETE FROM Pet pet WHERE pet.id = " + petId).executeUpdate();
        if (this.entityManager.contains(pet)) {
            this.entityManager.remove(pet);
        }
    }
}
