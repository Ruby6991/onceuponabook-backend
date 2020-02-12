package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.Book;
import com.apiit.onceuponabook.models.Rating;
import com.apiit.onceuponabook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    //Get all ratings made for a specific book
    List<Rating> findByBook(Book book);

    //Get all ratings made by a specific user
    List<Rating> findByUser(User user);
}
