package com.okenit.productpricemanager.dto;

import lombok.Data;

@Data
public class ProductNameAndPriceDto {
    String name;
    Double price;

    public ProductNameAndPriceDto(){};
    public ProductNameAndPriceDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
