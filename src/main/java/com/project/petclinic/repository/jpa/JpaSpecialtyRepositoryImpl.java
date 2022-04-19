package com.project.petclinic.repository.jpa;

import com.project.petclinic.model.Specialty;
import com.project.petclinic.repository.SpecialtyRepository;
import com.project.petclinic.support.annotation.JpaProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@JpaProfile
public class JpaSpecialtyRepositoryImpl implements SpecialtyRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Specialty findById(int id) throws DataRetrievalFailureException {
        return em.find(Specialty.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Specialty> findAll() throws DataAccessException {
        return em.createQuery("SELECT spec FROM Specialty spec").getResultList();
    }

    @Override
    public void save(Specialty specialty) throws DataAccessException {
        if (specialty.getId() == null) {
            em.persist(specialty);
        } else {
            em.merge(specialty);
        }
    }

    @Override
    public void delete(Specialty specialty) throws DataAccessException {
        em.remove(em.contains(specialty) ? specialty : em.merge(specialty));
        em.createNamedQuery("DELETE FROM vet_specialties WHERE specialty_id = " + specialty.getId()).executeUpdate();
        em.createQuery("DELETE FROM Specialty spec WHERE spec.id = " + specialty.getId());
    }
}
