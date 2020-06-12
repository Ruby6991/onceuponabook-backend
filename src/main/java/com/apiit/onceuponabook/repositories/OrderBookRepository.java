package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBookRepository extends CrudRepository<OrderBook, OrderBookID> {
    //Get all the books from a specific order
    List<OrderBook> findByOrder(Order order);
}
