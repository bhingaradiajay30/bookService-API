package com.Jaykumar.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jaykumar.bookservice.Entity.Book;
import com.Jaykumar.bookservice.repository.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book existingBook = bookOptional.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setYear(updatedBook.getYear());
            Book savedBook = bookRepository.save(existingBook);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//	private List<Book> books = new ArrayList<>(Arrays.asList(
//	        new Book(1L, "To Kill a Mockingbird", "Harper Lee", 1960),
//	        new Book(2L, "1984", "George Orwell", 1949)
//	));
//
//	
//    @GetMapping
//    public List<Book> getAllBooks() {
//        return books;
//    }
//    
//
//    @PostMapping
//    public ResponseEntity<Book> addBook(@RequestBody Book book) {
//        book.setId((long) (books.size() + 1)); // Set the ID to the next available ID
//        books.add(book);
//        return new ResponseEntity<>(book, HttpStatus.CREATED);
//    }
//    
//    @PutMapping("/{id}")
//    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
//        Optional<Book> bookOptional = books.stream()
//                .filter(book -> book.getId().equals(id))
//                .findFirst();
//
//        if (bookOptional.isPresent()) {
//            Book existingBook = bookOptional.get();
//            existingBook.setTitle(updatedBook.getTitle());
//            existingBook.setAuthor(updatedBook.getAuthor());
//            existingBook.setYear(updatedBook.getYear());
//            return new ResponseEntity<>(existingBook, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
//        Optional<Book> bookOptional = books.stream()
//                .filter(book -> book.getId().equals(id))
//                .findFirst();
//
//        if (bookOptional.isPresent()) {
//            books.remove(bookOptional.get());
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}

