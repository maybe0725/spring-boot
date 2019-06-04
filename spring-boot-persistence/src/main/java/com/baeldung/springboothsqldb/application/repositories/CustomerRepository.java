package com.baeldung.springboothsqldb.application.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baeldung.springboothsqldb.application.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {}