package com.apiit.onceuponabook.services;

import com.apiit.onceuponabook.dtos.*;
import com.apiit.onceuponabook.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelClassToDTO {

    public BookDTO bookToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(book.getAuthor());

        List<Category> categories = book.getCategories();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        for(Category category : categories){
            categoriesDTO.add(categoryToDTO(category));
        }
        bookDTO.setCategories(categoriesDTO);

        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setFormat(book.getFormat());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setPublicationYear(book.getPublicationYear());
        bookDTO.setQtyInStock(book.getQtyInStock());
        bookDTO.setImagePath(book.getImagePath());

//        List<OrderBook> orderBooks = book.getOrderedBooks();
//        List<OrderBookDTO> orderBookDTOS = new ArrayList<>();
//        for(OrderBook orderBook : orderBooks){
//            orderBookDTOS.add(orderBookToDTO(orderBook));
//        }
//        bookDTO.setOrderedBooks(orderBookDTOS);

        List<Rating> ratings = book.getRatings();
        List<RatingDTO> ratingDTOS = new ArrayList<>();
        for(Rating rating : ratings){
            ratingDTOS.add(ratingToDTO(rating));
        }
        bookDTO.setRatings(ratingDTOS);

//        List<User> users = book.getUsers();
//        List<UserDTO> userDTOS = new ArrayList<>();
//        for(User user : users){
//            userDTOS.add(userToDTO(user));
//        }
//        bookDTO.setUsers(userDTOS);

        return bookDTO;

    }

    public CategoryDTO categoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        return categoryDTO;
    }

    public OrderBookDTO orderBookToDTO(OrderBook orderBook){
        OrderBookDTO orderBookDTO = new OrderBookDTO();
        orderBookDTO.setBook(bookToDTO(orderBook.getBook()));
//        orderBookDTO.setOrder(orderToDTO(orderBook.getOrder()));
        orderBookDTO.setQuantity(orderBook.getQuantity());
        return orderBookDTO;
    }

    public RatingDTO ratingToDTO(Rating rating){
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getId());
//        ratingDTO.setBook(bookToDTO(rating.getBook()));
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setUser(userToDTO(rating.getUser()));
        return  ratingDTO;
    }

    public UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhoneNo(user.getPhoneNo());
        userDTO.setLogin(loginToDTO(user.getLogin()));
        userDTO.setRole(user.getRole());

//        List<Rating> ratings = user.getRatings();
//        List<RatingDTO> ratingDTOS = new ArrayList<>();
//        for(Rating rating : ratings){
//            ratingDTOS.add(ratingToDTO(rating));
//        }
//        userDTO.setRatings(ratingDTOS);

//        List<Address> addresses = user.getAddresses();
//        List<AddressDTO> addressDTOS = new ArrayList<>();
//        for(Address address : addresses){
//            addressDTOS.add(addressToDTO(address));
//        }
//        userDTO.setAddresses(addressDTOS);
//
        List<Book> bookList = user.getBooks();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for(Book book : bookList){
            bookDTOList.add(bookToDTO(book));
        }
        userDTO.setBooks(bookDTOList);
//
//        List<Order> orders = user.getOrders();
//        List<OrderDTO> orderDTOList = new ArrayList<>();
//        for(Order order : orders){
//            orderDTOList.add(orderToDTO(order));
//        }
//        userDTO.setOrders(orderDTOList);

        return userDTO;
    }

    public OrderDTO orderToDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAddress(addressToDTO(order.getAddress()));
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        orderDTO.setId(order.getId());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setPurchasedDate(order.getPurchasedDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setUser(userToDTO(order.getUser()));

        List<OrderBook> orderBooks = order.getOrderedBooks();
        List<OrderBookDTO> orderBookDTOList = new ArrayList<>();
        for(OrderBook orderBook : orderBooks){
            orderBookDTOList.add(orderBookToDTO(orderBook));
        }
        orderDTO.setOrderedBooks(orderBookDTOList);

        return orderDTO;
    }

    public AddressDTO addressToDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress(address.getAddress());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setId(address.getId());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setUser(userToDTO(address.getUser()));

        List<Order> orders = address.getOrders();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(Order order : orders){
            orderDTOList.add(orderToDTO(order));
        }
        addressDTO.setOrders(orderDTOList);

        return addressDTO;
    }

    public LoginDTO loginToDTO(Login login){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(login.getId());
        loginDTO.setLastLogin(login.getLastLogin());
        loginDTO.setPassword(login.getPassword());
        loginDTO.setRole(login.getRole());
        loginDTO.setUser(userToDTO(login.getUser()));

        return loginDTO;

    }

}
