package com.okenit.productpricemanager.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    long id;
    String name;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {

    }
}
