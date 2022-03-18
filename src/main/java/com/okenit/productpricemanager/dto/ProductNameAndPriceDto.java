package com.okenit.productpricemanager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductNameAndPriceDto {
    @ApiModelProperty(value = "Product name", example = "productName")
    String name;
    @ApiModelProperty(value = "Product price", example = "1800.05")
    Double price;

    public ProductNameAndPriceDto(){};
    public ProductNameAndPriceDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
