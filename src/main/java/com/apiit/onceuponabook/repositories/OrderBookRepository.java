package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.Book;
import com.apiit.onceuponabook.models.Order;
import com.apiit.onceuponabook.models.OrderBook;
import com.apiit.onceuponabook.models.OrderBookID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, OrderBookID> {
    //Get a book from a specific order
    Book findByOrderAndBook(Order order,Book book);

    //Get all the books from a specific order
    List<Book> findByOrder(Order order);
}
