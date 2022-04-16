package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.Owner;
import com.project.petclinic.repository.OwnerRepository;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Spring Data JPA specialization of the {@link com.project.petclinic.repository.OwnerRepository} interface
 *
 * @author Kamran Valizada
 */
@SpringDataJpaProfile
public interface SpringDataOwnerRepository extends OwnerRepository, Repository<Owner, Integer> {

    @Override
    @Query("SELECT DISTINCT owner FROM Owner owner LEFT JOIN FETCH owner.pets WHERE owner.lastName LIKE :lastName%")
    Collection<Owner> findByLastName(@Param("lastName") String lastName);

    @Override
    @Query("SELECT owner FROM Owner owner LEFT JOIN FETCH owner.pets WHERE owner.id = :id")
    Owner findById(@Param("id") int id);
}
