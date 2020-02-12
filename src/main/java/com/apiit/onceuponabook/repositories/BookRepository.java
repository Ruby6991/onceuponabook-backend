package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findByIsbn(long isbn);
}