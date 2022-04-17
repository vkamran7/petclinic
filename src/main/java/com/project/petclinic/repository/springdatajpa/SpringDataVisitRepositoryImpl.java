package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Visit;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringDataJpaProfile
public class SpringDataVisitRepositoryImpl implements VisitRepositoryOverride {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(Visit visit) {
        em.createQuery("DELETE FROM Visit visit WHERE visit.id = " + visit.getId()).executeUpdate();
        if (em.contains(visit)) {
            em.remove(visit);
        }
    }
}
