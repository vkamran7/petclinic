package com.project.petclinic.repository.jpa;

import com.project.petclinic.model.Visit;
import com.project.petclinic.repository.VisitRepository;
import com.project.petclinic.support.annotation.JpaProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Repository
@JpaProfile
public class JpaVisitRepositoryImpl implements VisitRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Visit> findAll() throws DataAccessException {
        return em.createQuery("SELECT v FROM Visit v").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Visit> findByPetId(int id) throws DataAccessException {
        Query query = em.createQuery("SELECT v FROM Visit v WHERE v.pet.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Visit findById(int id) throws DataRetrievalFailureException {
        return em.find(Visit.class, id);
    }

    @Override
    public void save(Visit visit) throws DataAccessException {
        if (visit.getId() == null) {
            em.persist(visit);
        } else {
            em.merge(visit);
        }
    }

    @Override
    public void delete(Visit visit) throws DataAccessException {
        em.remove(em.contains(visit) ? visit : em.merge(visit));
    }
}
