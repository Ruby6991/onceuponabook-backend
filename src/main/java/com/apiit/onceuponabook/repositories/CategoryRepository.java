package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.Book;
import com.apiit.onceuponabook.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    //Find by category title
    List<Book> findByTitle(String title);
}
