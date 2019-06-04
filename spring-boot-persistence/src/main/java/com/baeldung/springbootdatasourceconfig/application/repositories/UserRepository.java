package com.baeldung.springbootdatasourceconfig.application.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baeldung.springbootdatasourceconfig.application.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
