package com.apiit.onceuponabook.models;

import com.apiit.onceuponabook.enums.BookFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private long isbn;

    private String publisher;
    private Year publicationYear;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    private BookFormat format;

    private String description;
    private String imagePath;

    @Column(nullable = false)
    private int qtyInStock;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = {@JoinColumn(name = "BOOK_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID",referencedColumnName = "ID")})
    private List<Category> categories;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    @OneToMany(mappedBy = "book")
    private List<OrderBook> orderedBooks;

    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;


}