package com.project.petclinic.repository;

import com.project.petclinic.model.PetType;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.Collection;

/**
 * Interface to deal with <code>PetType</code> entities in the data store.
 * All method names are compliant with Spring Data naming conventions so this interface can easily be extended for Spring Data
 *
 * @author Kamran Valizada
 */
public interface PetTypeRepository {

    /**
     * Retrieving a <code>PetType</code> from a data store
     * @param id the id of a <code>PetType</code>
     * @return a single <code>PetType</code> object
     * @throws DataRetrievalFailureException if none found
     */
    PetType findById(int id) throws DataRetrievalFailureException;

    /**
     * Retrieving all <code>PetType</code> from a data store
     * @return a <code>Collection</code> of <code>PetType</code> (or empty <code>Collection</code> if none found)
     * @throws DataAccessException
     */
    Collection<PetType> findAll() throws DataAccessException;

    /**
     * Saving a <code>PetType</code> to a data store
     * @param petType the <code>PetType</code> to store
     * @throws DataAccessException
     */
    void save(PetType petType) throws DataAccessException;

    /**
     * Deleting a <code>PetType</code> from a data store
     * @param petType the <code>PetType</code> to delete
     * @throws DataAccessException
     */
    void delete(PetType petType) throws DataAccessException;
}
