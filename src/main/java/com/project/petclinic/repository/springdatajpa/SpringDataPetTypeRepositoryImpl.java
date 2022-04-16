package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Pet;
import com.project.petclinic.model.PetType;
import com.project.petclinic.model.Visit;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringDataJpaProfile
public class SpringDataPetTypeRepositoryImpl implements PetTypeRepositoryOverride {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public void delete(PetType petType) {
        em.remove(em.contains(petType) ? petType : em.merge(petType));
        Integer petTypeId = petType.getId();

        List<Pet> pets = em.createQuery("SELECT pet FROM Pet pet WHERE pet.type.id = " + petTypeId).getResultList();
        for (Pet pet : pets) {
            for (Visit visit : pet.getVisits()) {
                em.createQuery("DELETE FROM Visit visit WHERE visit.id = " + visit.getId()).executeUpdate();
            }
            em.createQuery("DELETE FROM Pet pet WHERE pet.id = " + pet.getId()).executeUpdate();
        }
        em.createQuery("DELETE FROM PetType petType WHERE petType.id = " + petTypeId).executeUpdate();
    }
}
