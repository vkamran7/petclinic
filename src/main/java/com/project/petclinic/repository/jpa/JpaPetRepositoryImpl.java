package com.project.petclinic.repository.jpa;

import com.project.petclinic.model.Pet;
import com.project.petclinic.model.PetType;
import com.project.petclinic.repository.PetRepository;
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
public class JpaPetRepositoryImpl implements PetRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Pet findById(int id) throws DataRetrievalFailureException {
        return em.find(Pet.class, id);
    }

    @Override
    public void save(Pet pet) throws DataAccessException {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            em.merge(pet);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Pet> findAll() throws DataAccessException {
        return em.createQuery("SELECT pet FROM Pet pet").getResultList();
    }

    @Override
    public void delete(Pet pet) throws DataAccessException {
        em.createQuery("DELETE FROM Visit visit WHERE visit.pet.id = " + pet.getId()).executeUpdate();
        em.createQuery("DELETE FROM Pet pet WHERE pet.id = " + pet.getId()).executeUpdate();
        if (em.contains(pet)) {
            em.remove(pet);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PetType> findPetTypes() throws DataAccessException {
        return em.createQuery("SELECT petType FROM PetType petType ORDER BY petType.name").getResultList();
    }
}
