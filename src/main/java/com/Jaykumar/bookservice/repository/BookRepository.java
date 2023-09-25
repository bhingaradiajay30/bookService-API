package com.Jaykumar.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jaykumar.bookservice.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}