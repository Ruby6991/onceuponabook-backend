package com.apiit.onceuponabook.controllers;

import com.apiit.onceuponabook.dtos.OrderDTO;
import com.apiit.onceuponabook.dtos.UserDTO;
import com.apiit.onceuponabook.models.Order;
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

    @PostMapping("/GetOrderList")
    public ResponseEntity<List<OrderDTO>> getOrderList(){
        return orderService.getOrderList();
    }

//    @DeleteMapping("/DeleteUser/{id}")
//    public ResponseEntity<Boolean> DeleteUser(@PathVariable String id){
//        return userService.deleteUser(id);
//    }

    @PutMapping("/UpdateOrder/{id}")
    public ResponseEntity<OrderDTO> UpdateOrder(@PathVariable int id, @RequestBody Order order){
        return orderService.updateOrder(id,order);
    }

}
