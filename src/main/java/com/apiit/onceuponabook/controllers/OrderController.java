package com.apiit.onceuponabook.controllers;

import com.apiit.onceuponabook.dtos.OrderBookDTO;
import com.apiit.onceuponabook.dtos.OrderDTO;
import com.apiit.onceuponabook.dtos.UserDTO;
import com.apiit.onceuponabook.models.Order;
import com.apiit.onceuponabook.models.OrderBook;
import com.apiit.onceuponabook.models.OrderBookID;
import com.apiit.onceuponabook.models.User;
import com.apiit.onceuponabook.services.OrderService;
import com.apiit.onceuponabook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/CreateOrder")
    public ResponseEntity<Boolean> CreateOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PostMapping("/GetOrder")
    public ResponseEntity<OrderDTO> getOrderDetails(@RequestBody Order order){
        return orderService.getOrder(order.getId());
    }

    @PostMapping("/GetCurrentOrder")
    public ResponseEntity<OrderDTO> getCurrentOrderDetails(@RequestBody User user){
        return orderService.getCurrentOrder(user.getEmail());
    }

    @PostMapping("/GetOrderList")
    public ResponseEntity<List<OrderDTO>> getOrderList(){
        return orderService.getOrderList();
    }

    @PostMapping("/GetOrderListByUser")
    public ResponseEntity<List<OrderDTO>> getOrderListByUser(@RequestBody User user){
        return orderService.getOrderListByUser(user.getEmail());
    }

    @PutMapping("/UpdateOrderAddBook/{id}")
    public ResponseEntity<OrderDTO> UpdateOrderAddBook(@PathVariable int id, @RequestBody Order order){
        return orderService.updateOrderAddBook(id,order);
    }

    @PutMapping("/UpdateOrderCheckOut/{id}")
    public ResponseEntity<OrderDTO> UpdateOrderCheckOut(@PathVariable int id, @RequestBody Order order){
        return orderService.updateOrderCheckOut(id,order);
    }

    @PutMapping("/updateBookQuantity/{action}")
    public ResponseEntity<OrderBookDTO> UpdateBookQuantity(@PathVariable String action, @RequestBody OrderBook order){
        return orderService.updateBookQuantity(action,order);
    }

    @PostMapping("/CreateOrderBook")
    public ResponseEntity<Boolean> CreateOrderBook(@RequestBody OrderBook orderBook){
        return orderService.createOrderBook(orderBook);
    }

    @DeleteMapping("/DeleteOrderBook/{orderId}/{bookId}")
    public ResponseEntity<Boolean> DeleteOrderBook(@PathVariable int orderId, @PathVariable int bookId ){
        return orderService.deleteOrderBook(orderId,bookId);
    }
}
