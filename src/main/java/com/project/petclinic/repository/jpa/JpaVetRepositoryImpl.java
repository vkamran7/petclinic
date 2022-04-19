package com.project.petclinic.repository.jpa;

import com.project.petclinic.model.Vet;
import com.project.petclinic.repository.VetRepository;
import com.project.petclinic.support.annotation.JpaProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@JpaProfile
public class JpaVetRepositoryImpl implements VetRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Vet> findAll() throws DataAccessException {
        return em.createQuery("SELECT vet FROM Vet vet").getResultList();
    }

    @Override
    public Vet findById(int id) throws DataRetrievalFailureException {
        return em.find(Vet.class, id);
    }

    @Override
    public void save(Vet vet) throws DataAccessException {
        if (vet.getId() == null) {
            em.persist(vet);
        } else {
            em.merge(vet);
        }
    }

    @Override
    public void delete(Vet vet) throws DataAccessException {
        em.remove(em.contains(vet) ? vet : em.merge(vet));
    }
}
