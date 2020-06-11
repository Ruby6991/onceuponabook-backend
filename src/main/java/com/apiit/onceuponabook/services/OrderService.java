package com.apiit.onceuponabook.services;

import com.apiit.onceuponabook.dtos.OrderBookDTO;
import com.apiit.onceuponabook.dtos.OrderDTO;
import com.apiit.onceuponabook.models.Order;
import com.apiit.onceuponabook.models.OrderBook;
import com.apiit.onceuponabook.models.User;
import com.apiit.onceuponabook.repositories.OrderBookRepository;
import com.apiit.onceuponabook.repositories.OrderRepository;
import com.apiit.onceuponabook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private ModelToDTO modelToDTO;

    public ResponseEntity<Boolean> createOrder(Order newOrder){
        Optional<Order> orderOptional=orderRepo.findById(newOrder.getId());
        if(!orderOptional.isPresent()){
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

    public ResponseEntity<List<OrderDTO>> getOrderList() {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Iterable<Order> orders = orderRepo.findAll();
        for(Order order : orders){
            orderDTOList.add(modelToDTO.orderToDTO(order));
        }
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    public ResponseEntity<OrderDTO> updateOrder(int id, Order newOrder){
        Optional<Order> orderOptional = orderRepo.findById(id);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setAddress(newOrder.getAddress());
            order.setOrderedBooks(newOrder.getOrderedBooks());
            order.setPaymentMethod(newOrder.getPaymentMethod());
            newOrder = orderRepo.save(newOrder);
            return new ResponseEntity<>(modelToDTO.orderToDTO(newOrder), HttpStatus.OK);
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

    public ResponseEntity<List<OrderBookDTO>> getOrderBookByUser(String id){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            List<OrderBookDTO> orderBookDTOList = new ArrayList<>();
            List<OrderBook> orderBooks = orderBookRepo.findByUser(userOptional.get());
            for(OrderBook orderBook: orderBooks){
                orderBookDTOList.add(modelToDTO.orderBookToDTO(orderBook));
            }
            return new ResponseEntity<>(orderBookDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<OrderBookDTO> addOrderBook(OrderBook newOrderBook){
        Optional<OrderBook> orderBookOptional = orderBookRepo.findById(newOrderBook.getOrderBookID());
        if(orderBookOptional.isPresent()){
            newOrderBook = orderBookRepo.save(newOrderBook);
            return new ResponseEntity<>(modelToDTO.orderBookToDTO(newOrderBook), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<OrderBookDTO> updateBookQuantity(OrderBook updateOrderBook){
        Optional<OrderBook> orderBookOptional = orderBookRepo.findById(updateOrderBook.getOrderBookID());
        if(orderBookOptional.isPresent()){
            OrderBook orderBook = orderBookOptional.get();
            orderBook.setQuantity(updateOrderBook.getQuantity());
            orderBookRepo.save(orderBook);
            return new ResponseEntity<>(modelToDTO.orderBookToDTO(orderBook),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Boolean> deleteOrderBook(OrderBook orderBook){
        try{
            orderBookRepo.deleteById(orderBook.getOrderBookID());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EmptyResultDataAccessException erda_ex){

        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

}
