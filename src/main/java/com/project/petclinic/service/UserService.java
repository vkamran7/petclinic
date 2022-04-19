package com.project.petclinic.service;

import com.project.petclinic.model.User;
import org.springframework.dao.DataAccessException;

public interface UserService {

    void saveUser(User user) throws DataAccessException;
}
