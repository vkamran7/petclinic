package com.project.petclinic.repository;

import com.project.petclinic.model.Specialty;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.Collection;

/**
 * Interface to deal with <code>Specialty</code> entities in a data store. All method names are compliant with
 * Spring Data naming conventions so this interface can easily be extended for Spring Data
 *
 * @author Kamran Valizada
 */
public interface SpecialtyRepository {

    /**
     * Retrieving a <code>Specialty</code> from a data store
     * @param id the id of a <code>Specialty</code>
     * @return a single <code>Specialty</code> object
     * @throws DataRetrievalFailureException if none found
     */
    Specialty findById(int id) throws DataRetrievalFailureException;

    /**
     * Retrieving all <code>Specialty</code> from a data store
     * @return a <code>Collection</code> of <code>Specialty</code> (or empty <code>Collection</code> if none found)
     * @throws DataAccessException
     */
    Collection<Specialty> findAll() throws DataAccessException;

    /**
     * Saving a <code>Specialty</code> to a data store
     * @param specialty the <code>Specialty</code> to store
     * @throws DataAccessException
     */
    void save(Specialty specialty) throws DataAccessException;

    /**
     * Deleting a <code>Specialty</code> from a data store
     * @param specialty the <code>Specialty</code> to delete
     * @throws DataAccessException
     */
    void delete(Specialty specialty) throws DataAccessException;
}
