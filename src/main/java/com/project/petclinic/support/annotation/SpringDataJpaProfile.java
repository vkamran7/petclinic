package com.project.petclinic.support.annotation;

import com.project.petclinic.support.profile.RepositoryProfile;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Profile(RepositoryProfile.SPRING_DATA_JPA)
public @interface SpringDataJpaProfile {
}
