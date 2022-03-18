package com.okenit.productpricemanager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ProductNameAndPriceDto {
    @ApiModelProperty(value = "Product name", example = "productName")
    String name;
    @ApiModelProperty(value = "Product price", example = "1800.05")
    Double price;

}
