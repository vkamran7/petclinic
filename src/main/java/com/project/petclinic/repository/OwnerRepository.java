package com.project.petclinic.repository;

import com.project.petclinic.model.BaseEntity;
import com.project.petclinic.model.Owner;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Repository class for <code>Owner</code> domain objects. All method names are compliant with Spring Data naming
 * conventions which means this interface can easily be extended for Spring Data
 *
 * @author Kamran Valizada
 */
public interface OwnerRepository {

    /**
     * Retrieve <code>Owner</code>s from the data store by last name, returning all owners whose last name <i>starts</i>
     * with the given name
     *
     * @param lastName Value to search for
     * @return a <code>Collection</code> of matching <code>Owner</code>s (or an empty <code>Collection</code> if non found)
     * @throws DataAccessException
     */
    Collection<Owner> findByLastName(String lastName) throws DataAccessException;

    /**
     * Retrieve an <code>Owner</code> from data store by id
     *
     * @param id the id to search for
     * @return the <code>Owner</code> if found
     * @throws DataAccessException
     */
    Owner findById(int id) throws DataAccessException;

    /**
     * Save an <code>Owner</code> to the data store, either inserting or updating
     * @param owner the <code>Owner</code> to save
     * @see BaseEntity#isNew()
     * @throws DataAccessException
     */
    void save(Owner owner) throws DataAccessException;

    /**
     * Retrieve all <code>Owner</code>s from data store.
     * @return a <code>Collection</code> of <code>Owner</code> (or empty <code>Collection</code> if none found
     * @throws DataAccessException
     */
    Collection<Owner> findAll() throws DataAccessException;

    /**
     * Delete an <code>Owner</code> from the data store
     * @param owner the <code>Owner</code> to delete
     * @throws DataAccessException
     */
    void delete(Owner owner) throws DataAccessException;
}
