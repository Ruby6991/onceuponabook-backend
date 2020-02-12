package com.apiit.onceuponabook.dtos;

import com.apiit.onceuponabook.enums.OrderStatus;
import com.apiit.onceuponabook.enums.PaymentMethod;
import com.apiit.onceuponabook.models.Address;
import com.apiit.onceuponabook.models.OrderBook;
import com.apiit.onceuponabook.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date purchasedDate;

    private Date deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private UserDTO user;
    private AddressDTO address;
    private List<OrderBookDTO> orderedBooks;

}
