package com.project.petclinic.repository.springdatajpa;

import com.project.petclinic.model.User;
import com.project.petclinic.repository.UserRepository;
import com.project.petclinic.support.annotation.SpringDataJpaProfile;
import org.springframework.data.repository.Repository;

@SpringDataJpaProfile
public interface SpringDataUserRepository extends UserRepository, Repository<User, Integer> {

}
