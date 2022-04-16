package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Pet;
import com.project.petclinic.model.PetType;
import com.project.petclinic.repository.PetRepository;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Spring Data JPA specialization of the {@link com.project.petclinic.repository.PetRepository} interface
 *
 * @author Kamran Valizada
 */
@SpringDataJpaProfile
public interface SpringDataPetRepository extends PetRepository, Repository<Pet, Integer>, PetRepositoryOverride {

    @Override
    @Query("SELECT petType FROM PetType petType ORDER BY petType.name")
    List<PetType> findPetTypes() throws DataAccessException;
}
