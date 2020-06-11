package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findByIsbn(long isbn);
}
