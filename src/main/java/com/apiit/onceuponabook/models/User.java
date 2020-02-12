package com.apiit.onceuponabook.models;

import com.apiit.onceuponabook.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private int phoneNo;

    @Column(nullable = false)
    @Size(min=6)
    private String password;

    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user")
    private Login login;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "wishlist_item",
            joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID",referencedColumnName = "ID")})
    private List<Book> books;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    public User(String email, String fullName, int phoneNo, String password) {
        super();
        this.email = email;
        this.fullName=fullName;
        this.phoneNo=phoneNo;
        this.password=password;
    }

}
