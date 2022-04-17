package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Specialty;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringDataJpaProfile
public class SpringDataSpecialtyRepositoryImpl implements SpecialtyRepositoryOverride{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(Specialty specialty) {
        em.remove(em.contains(specialty) ? specialty : em.merge(specialty));
        em.createQuery("DELETE FROM vet_specialties WHERE specialty_id = " + specialty.getId()).executeUpdate();
        em.createQuery("DELETE FROM Specialty specialty WHERE specialty.id = " + specialty.getId()).executeUpdate();
    }
}
