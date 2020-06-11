package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.enums.OrderStatus;
import com.apiit.onceuponabook.models.Order;
import com.apiit.onceuponabook.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    //Get all orders made by a specific user by its status
    List<Order> findByUserAndStatus(User user, OrderStatus orderStatus);

    //Get all orders made by a specific user
    List<Order> findByUser(User user, Sort sort);

    //Get all orders in a specific order status
    List<Order> findByStatus(OrderStatus orderStatus,Sort sort);

}
