package com.project.petclinic.repository;

import com.project.petclinic.model.Visit;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.Collection;
import java.util.List;

/**
 * Interface to deal with <code>Visit</code> entities in a data store. All method names are compliant with
 * Spring Data naming conventions so this interface can easily be extended for Spring Data
 *
 * @author Kamran Valizada
 */
public interface VisitRepository {

    /**
     * Retrieving all <code>Visit</code> from a data store
     * @return a <code>Collection</code> of <code>Visit</code> (or empty <code>Collection</code> if none found)
     * @throws DataAccessException
     */
    Collection<Visit> findAll() throws DataAccessException;

    /**
     * Retrieving all <code>Visit</code> from a data store that matches the pet id
     * @param id the id of a <code>Pet</code>
     * @return a <code>List</code> of a <code>Visit</code>
     * @throws DataAccessException
     */
    List<Visit> findByPetId(int id) throws DataAccessException;

    /**
     * Retrieving a <code>Visit</code> from a data store
     * @param id the id of a <code>Visit</code>
     * @return a single <code>Visit</code> object
     * @throws DataRetrievalFailureException if none found
     */
    Visit findById(int id) throws DataRetrievalFailureException;

    /**
     * Saving a <code>Visit</code> to a data store
     * @param visit the <code>Visit</code> to store
     * @throws DataAccessException
     */
    void save(Visit visit) throws DataAccessException;

    /**
     * Deleting a <code>Visit</code> from a data store
     * @param visit the <code>Visit</code> to delete
     * @throws DataAccessException
     */
    void delete(Visit visit) throws DataAccessException;
}
