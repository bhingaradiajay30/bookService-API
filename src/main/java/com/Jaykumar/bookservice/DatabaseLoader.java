package com.Jaykumar.bookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Jaykumar.bookservice.Entity.Book;
import com.Jaykumar.bookservice.repository.BookRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final BookRepository repository;

    @Autowired
    public DatabaseLoader(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        // Check if database is empty, if so initialize with given data
        if (repository.count() == 0) {
            repository.save(new Book(1L, "To Kill a Mockingbird", "Harper Lee", 1960));
            repository.save(new Book(2L, "1984", "George Orwell", 1949));
        }
    }
}