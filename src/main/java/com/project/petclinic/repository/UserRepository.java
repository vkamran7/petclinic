package com.project.petclinic.repository;

import com.project.petclinic.model.User;
import org.springframework.dao.DataAccessException;

/**
 * @author Kamran Valizada
 */
public interface UserRepository {

    /**
     * Saving a <code>User</code> to a data store
     * @param user the <code>User</code> to store
     * @throws DataAccessException
     */
    void save(User user) throws DataAccessException;
}
