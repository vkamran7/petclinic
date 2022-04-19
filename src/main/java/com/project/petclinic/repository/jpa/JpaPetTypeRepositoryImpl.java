package com.project.petclinic.repository.jpa;

import com.project.petclinic.model.Pet;
import com.project.petclinic.model.PetType;
import com.project.petclinic.model.Visit;
import com.project.petclinic.repository.PetTypeRepository;
import com.project.petclinic.support.annotation.JpaProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Repository
@JpaProfile
public class JpaPetTypeRepositoryImpl implements PetTypeRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public PetType findById(int id) throws DataRetrievalFailureException {
        return em.find(PetType.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<PetType> findAll() throws DataAccessException {
        return em.createQuery("SELECT petType FROM PetType petType").getResultList();
    }

    @Override
    public void save(PetType petType) throws DataAccessException {
        if (petType.getId() == null) {
            em.persist(petType);
        } else {
            em.merge(petType);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(PetType petType) throws DataAccessException {
        em.remove(em.contains(petType) ? petType : em.merge(petType));

        List<Pet> pets = em.createQuery("SELECT pet FROM Pet pet WHERE pet.type.id = " + petType.getId()).getResultList();
        for (Pet pet : pets) {
            for (Visit visit : pet.getVisits()) {
                em.createQuery("DELETE FROM Visit visit WHERE visit.id = " + visit.getId()).executeUpdate();
            }
            em.createQuery("DELETE FROM Pet pet WHERE pet.id = " + pet.getId()).executeUpdate();
        }
        em.createQuery("DELETE FROM PetType petType WHERE petType.id = " + petType.getId()).executeUpdate();
    }
}
