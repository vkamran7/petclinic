package com.project.petclinic.repository.jpa;

import com.project.petclinic.model.Owner;
import com.project.petclinic.repository.OwnerRepository;
import com.project.petclinic.support.annotation.JpaProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
@JpaProfile
public class JpaOwnerRepositoryImpl implements OwnerRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Owner> findByLastName(String lastName) throws DataAccessException {
        Query query = em.createQuery("SELECT DISTINCT owner FROM Owner owner LEFT JOIN FETCH owner.pets WHERE owner.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }

    @Override
    public Owner findById(int id) throws DataAccessException {
        Query query = em.createQuery("SELECT owner FROM Owner owner LEFT JOIN FETCH owner.pets WHERE owner.id = :id");
        query.setParameter("id", id);
        return (Owner) query.getSingleResult();
    }

    @Override
    public void save(Owner owner) throws DataAccessException {
        if (owner.getId() == null) {
            em.persist(owner);
        } else {
            em.merge(owner);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Owner> findAll() throws DataAccessException {
        Query query = em.createQuery("SELECT owner FROM Owner owner");
        return query.getResultList();
    }

    @Override
    public void delete(Owner owner) throws DataAccessException {
        em.remove(em.contains(owner) ? owner : em.merge(owner));
    }
}
