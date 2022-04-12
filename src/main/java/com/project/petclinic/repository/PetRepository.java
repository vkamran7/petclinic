package com.project.petclinic.repository;

import com.project.petclinic.model.Pet;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.Collection;

/**
 * Repository class for <code>Pet</code> domain objects. All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data
 *
 * @author Kamran Valizada
 */
public interface PetRepository {

    /**
     * Retrieve a <code>Pet</code> from data store.
     * @param id the id of a <code>Pet</code>
     * @return a single <code>Pet</code> object
     * @throws DataRetrievalFailureException if none found
     */
    Pet findById(int id) throws DataRetrievalFailureException;

    /**
     * Saving a <code>Pet</code> object to the data store
     * @param pet the <code>Pet</code> object to save
     * @throws DataAccessException
     */
    void save(Pet pet) throws DataAccessException;

    /**
     * Retrieve all <code>Pet</code>s from the data store
     * @return a <code>Collection</code> of a <code>Pet</code>
     * @throws DataAccessException
     */
    Collection<Pet> findAll() throws DataAccessException;

    /**
     * Deleting the <code>Pet</code> object from the data store
     * @param pet the <code>Pet</code> to delete
     * @throws DataAccessException
     */
    void delete(Pet pet) throws DataAccessException;
}
