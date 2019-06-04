package com.baeldung.springboothibernate.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baeldung.springboothibernate.application.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
