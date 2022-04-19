package com.project.petclinic.repository.jpa;

import com.project.petclinic.model.User;
import com.project.petclinic.repository.UserRepository;
import com.project.petclinic.support.annotation.JpaProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@JpaProfile
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User user) throws DataAccessException {
        if (em.find(User.class, user.getUsername()) == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }
}
