package com.apiit.onceuponabook.dtos;

import com.apiit.onceuponabook.enums.BookFormat;
import com.apiit.onceuponabook.models.Category;
import com.apiit.onceuponabook.models.OrderBook;
import com.apiit.onceuponabook.models.Rating;
import com.apiit.onceuponabook.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private int id;
    private long isbn;
    private String publisher;
    private Year publicationYear;
    private String author;
    private String title;
    private double price;

    @Enumerated(EnumType.STRING)
    private BookFormat format;

    private String description;
    private String imagePath;
    private int qtyInStock;
    private List<CategoryDTO> categories;
    private List<UserDTO> users;
    private List<OrderBookDTO> orderedBooks;
    private List<RatingDTO> ratings;
}
