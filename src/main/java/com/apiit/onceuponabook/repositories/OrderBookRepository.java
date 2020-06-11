package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBookRepository extends CrudRepository<OrderBook, OrderBookID> {
//    //Get a book from a specific order
//    Book findByOrderAndBook(Order order,Book book);

    //Get all the books from a specific order
    List<OrderBook> findByOrder(Order order);

    //Get all the ordered books for a specific user
    List<OrderBook> findByUser(User user);
}
