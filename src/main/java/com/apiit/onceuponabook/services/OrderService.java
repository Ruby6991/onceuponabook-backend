package com.apiit.onceuponabook.services;

import com.apiit.onceuponabook.dtos.OrderBookDTO;
import com.apiit.onceuponabook.dtos.OrderDTO;
import com.apiit.onceuponabook.enums.OrderStatus;
import com.apiit.onceuponabook.models.*;
import com.apiit.onceuponabook.repositories.BookRepository;
import com.apiit.onceuponabook.repositories.OrderBookRepository;
import com.apiit.onceuponabook.repositories.OrderRepository;
import com.apiit.onceuponabook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderBookRepository orderBookRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    private ModelToDTO modelToDTO;

    public ResponseEntity<Boolean> createOrder(Order newOrder){
        Optional<User> userOptional=userRepo.findById(newOrder.getUser().getEmail());
        Optional<Order> orderOptional=orderRepo.findById(newOrder.getId());
        if(!orderOptional.isPresent()){
//            List<OrderBook> orderBooks=new ArrayList<>();
            newOrder.setUser(userOptional.get());
            newOrder.setStatus(OrderStatus.Pending);
//            newOrder.setOrderedBooks(orderBooks);
            orderRepo.save(newOrder);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<OrderDTO> getOrder(int id){
        Optional<Order> orderOptional = orderRepo.findById(id);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            return new ResponseEntity<>(modelToDTO.orderToDTO(order),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<OrderDTO> getCurrentOrder(String userId){
        Iterable<Order> orders = orderRepo.findAll();
        for(Order order : orders){
            if(order.getUser().getEmail().equals(userId) && order.getStatus()== OrderStatus.Pending) {
                return new ResponseEntity<>(modelToDTO.orderToDTO(order), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderDTO>> getOrderList() {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Iterable<Order> orders = orderRepo.findAll();
        for(Order order : orders){
            orderDTOList.add(modelToDTO.orderToDTO(order));
        }
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderDTO>> getOrderListByUser(String userId) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Iterable<Order> orders = orderRepo.findAll();
        for(Order order : orders){
            if(order.getUser().getEmail().equals(userId)) {
                orderDTOList.add(modelToDTO.orderToDTO(order));
            }
        }
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    public ResponseEntity<OrderDTO> updateOrderAddBook(int id, Order newOrder){
        Optional<Order> orderOptional = orderRepo.findById(id);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();

            OrderBook orderBook=new OrderBook();
            orderBook.setQuantity(1);
            orderBook.setOrder(order);
            orderBook.setBook(newOrder.getOrderedBooks().get(0).getBook());

            OrderBookID orderBookID=new OrderBookID();
            orderBookID.setBookID(newOrder.getOrderedBooks().get(0).getBook().getId());
            orderBookID.setOrderID(order.getId());

            orderBook.setOrderBookID(orderBookID);
            orderBookRepo.save(orderBook);

            Optional<Book> bookOptional = bookRepo.findById(newOrder.getOrderedBooks().get(0).getBook().getId());
            Book book = bookOptional.get();
            int newQuantity = book.getQtyInStock()-1;
            book.setQtyInStock(newQuantity);
            bookRepo.save(book);

            List<OrderBook> newBooks = order.getOrderedBooks();

            order.setOrderedBooks(newBooks);
            order.setPaymentMethod(newOrder.getPaymentMethod());
            orderRepo.save(order);
            return new ResponseEntity<>(modelToDTO.orderToDTO(order), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<OrderDTO> updateOrderCheckOut(int id, Order newOrder){
        Optional<Order> orderOptional = orderRepo.findById(id);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setPaymentMethod(newOrder.getPaymentMethod());
            order.setTotalAmount(newOrder.getTotalAmount());
            order.setStatus(OrderStatus.Confirmed);
            order.setPurchasedDate(new Date());
            orderRepo.save(order);
            return new ResponseEntity<>(modelToDTO.orderToDTO(order), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<OrderBookDTO>> getOrderBookByOrder(int id){
        Optional<Order> orderOptional = orderRepo.findById(id);
        if(orderOptional.isPresent()){
            List<OrderBookDTO> orderBookDTOList = new ArrayList<>();
            List<OrderBook> orderBooks = orderBookRepo.findByOrder(orderOptional.get());
            for(OrderBook orderBook: orderBooks){
                orderBookDTOList.add(modelToDTO.orderBookToDTO(orderBook));
            }
            return new ResponseEntity<>(orderBookDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Boolean> createOrderBook(OrderBook newOrderBook) {
        Optional<OrderBook> orderBookOptional = orderBookRepo.findById(newOrderBook.getOrderBookID());
        if (!orderBookOptional.isPresent()) {

            Optional<Book> bookOptional = bookRepo.findById(newOrderBook.getBook().getId());
            Book book = bookOptional.get();
            int newQuantity = book.getQtyInStock() - 1;
            book.setQtyInStock(newQuantity);
            bookRepo.save(book);

            newOrderBook.setQuantity(1);
            orderBookRepo.save(newOrderBook);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<OrderBookDTO> updateBookQuantity(String action, OrderBook updateOrderBook){
        OrderBookID orderBookID=new OrderBookID();
        orderBookID.setBookID(updateOrderBook.getBook().getId());
        orderBookID.setOrderID(updateOrderBook.getOrder().getId());
        Optional<OrderBook> orderBookOptional = orderBookRepo.findById(orderBookID);
        if(orderBookOptional.isPresent()){
            OrderBook orderBook = orderBookOptional.get();
            Optional<Book> bookOptional = bookRepo.findById(updateOrderBook.getBook().getId());
            Book book = bookOptional.get();
            if(action.equals("increase")){
                orderBook.setQuantity(orderBook.getQuantity()+1);
                int newQuantity = book.getQtyInStock() - 1;
                book.setQtyInStock(newQuantity);
                bookRepo.save(book);
            }else if(action.equals("decrease")){
                orderBook.setQuantity(orderBook.getQuantity()-1);
                int newQuantity = book.getQtyInStock() + 1;
                book.setQtyInStock(newQuantity);
                bookRepo.save(book);
            }
            orderBookRepo.save(orderBook);
            return new ResponseEntity<>(modelToDTO.orderBookToDTO(orderBook),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Boolean> deleteOrderBook(int orderId, int bookId){

        OrderBookID orderBookID=new OrderBookID();
        orderBookID.setBookID(bookId);
        orderBookID.setOrderID(orderId);
        Optional<OrderBook> orderBookOptional = orderBookRepo.findById(orderBookID);

        Optional<Book> bookOptional = bookRepo.findById(orderBookOptional.get().getBook().getId());
        Book book = bookOptional.get();
        int oldQuantity = book.getQtyInStock();
        int addition=orderBookOptional.get().getQuantity();
        int newQuantity=oldQuantity+addition;
        book.setQtyInStock(newQuantity);
        bookRepo.save(book);

        try{
            orderBookRepo.deleteById(orderBookID);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EmptyResultDataAccessException erda_ex){

        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

}
