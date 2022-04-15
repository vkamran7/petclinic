package com.project.petclinic.repository;

import com.project.petclinic.model.Vet;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.Collection;

/**
 * Interface to deal with <code>Vet</code> entities in a data store. All method names are compliant with
 * Spring Data naming conventions so this interface can easily be extended for Spring Data
 *
 * @author Kamran Valizada
 */
public interface VetRepository {

    /**
     * Retrieving all <code>Vet</code> from a data store
     * @return a <code>Collection</code> of <code>Vet</code> (or empty <code>Collection</code> if none found)
     * @throws DataAccessException
     */
    Collection<Vet> findAll() throws DataAccessException;

    /**
     * Retrieving a <code>Vet</code> from a data store
     * @param id the id of a <code>Vet</code>
     * @return a single <code>Vet</code> object
     * @throws DataRetrievalFailureException if none found
     */
    Vet findById(int id) throws DataRetrievalFailureException;

    /**
     * Saving a <code>Vet</code> to a data store
     * @param vet the <code>Vet</code> to store
     * @throws DataAccessException
     */
    void save(Vet vet) throws DataAccessException;

    /**
     * Deleting a <code>Vet</code> from a data store
     * @param vet the <code>Vet</code> to delete
     * @throws DataAccessException
     */
    void delete(Vet vet) throws DataAccessException;
}
