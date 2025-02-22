package com.apiit.onceuponabook.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderBookID implements Serializable {

    private static final long serialVersionUID=1L;

    private int orderID;
    private int bookID;

}
