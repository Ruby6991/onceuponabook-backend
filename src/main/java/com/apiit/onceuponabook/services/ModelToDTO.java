package com.apiit.onceuponabook.services;

import com.apiit.onceuponabook.dtos.*;
import com.apiit.onceuponabook.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelToDTO {

    public AddressDTO addressToDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();

//        List<Order> orders = address.getOrders();
//        List<OrderDTO> orderDTOS = new ArrayList<>();
//        for(Order order: orders){
//            orderDTOS.add(orderToDTO(order));
//        }
//        addressDTO.setOrders(orderDTOS);

        addressDTO.setUser(userToDTO(address.getUser()));
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setId(address.getId());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setCity(address.getCity());
        addressDTO.setAddress(address.getAddress());

        return addressDTO;
    }

    public BookDTO bookToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setTitle(book.getTitle());

//        List<User> users = book.getUsers();
//        List<UserDTO> userDTOS = new ArrayList<>();
//        for(User user: users){
//            userDTOS.add(userToDTO(user));
//        }
//        bookDTO.setUsers(userDTOS);

//        List<Rating> ratings = book.getRatings();
//        List<RatingDTO> ratingDTOS = new ArrayList<>();
//        for(Rating rating: ratings){
//            ratingDTOS.add(ratingToDTO(rating));
//        }
//        bookDTO.setRatings(ratingDTOS);

        bookDTO.setImagePath(book.getImagePath());
        bookDTO.setQtyInStock(book.getQtyInStock());

//        List<OrderBook> books = book.getOrderedBooks();
//        List<OrderBookDTO> orderBookDTOS = new ArrayList<>();
//        for(OrderBook orderBook: books){
//            orderBookDTOS.add(orderBookToDTO(orderBook));
//        }
//        bookDTO.setOrderedBooks(orderBookDTOS);

        bookDTO.setPublicationYear(book.getPublicationYear());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setFormat(book.getFormat());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublicationYear(book.getPublicationYear());

        return bookDTO;
    }

    public OrderBookDTO orderBookToDTO(OrderBook orderBook){
        OrderBookDTO orderBookDTO = new OrderBookDTO();
        orderBookDTO.setQuantity(orderBook.getQuantity());
        orderBookDTO.setOrder(orderToDTO(orderBook.getOrder()));
        orderBookDTO.setBook(bookToDTO(orderBook.getBook()));
        return orderBookDTO;
    }

    public OrderDTO orderToDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUser(userToDTO(order.getUser()));
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setPurchasedDate(order.getPurchasedDate());
        orderDTO.setPaymentMethod(order.getPaymentMethod());

//        List<OrderBook> books = order.getOrderedBooks();
//        List<OrderBookDTO> orderBookDTOS = new ArrayList<>();
//        for(OrderBook orderBook: books){
//            orderBookDTOS.add(orderBookToDTO(orderBook));
//        }
//        orderDTO.setOrderedBooks(orderBookDTOS);

        orderDTO.setDeliveryDate(order.getDeliveryDate());
        orderDTO.setAddress(addressToDTO(order.getAddress()));

        return orderDTO;
    }

    public RatingDTO ratingToDTO(Rating rating){
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setUser(userToDTO(rating.getUser()));
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setId(rating.getId());
        ratingDTO.setBook(bookToDTO(rating.getBook()));

        return ratingDTO;
    }

    public UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setPhoneNo(user.getPhoneNo());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(user.getRole());
        userDTO.setPassword(user.getPassword());

//        List<Rating> ratings = user.getRatings();
//        List<RatingDTO> ratingDTOS = new ArrayList<>();
//        for(Rating rating: ratings){
//            ratingDTOS.add(ratingToDTO(rating));
//        }
//        userDTO.setRatings(ratingDTOS);

//        List<Order> orders = user.getOrders();
//        List<OrderDTO> orderDTOS = new ArrayList<>();
//        for(Order order: orders){
//            orderDTOS.add(orderToDTO(order));
//        }
//        userDTO.setOrders(orderDTOS);

//        List<Address> addresses = user.getAddresses();
//        List<AddressDTO> addressDTOS = new ArrayList<>();
//        for(Address address: addresses){
//            addressDTOS.add(addressToDTO(address));
//        }
//        userDTO.setAddresses(addressDTOS);

        List<Book> books = user.getBooks();
        List<BookDTO> bookDTOS = new ArrayList<>();
        for(Book book: books){
            bookDTOS.add(bookToDTO(book));
        }
        userDTO.setBooks(bookDTOS);

        return userDTO;
    }

}
