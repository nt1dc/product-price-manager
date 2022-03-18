package com.okenit.productpricemanager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;

@Data
public class StatisticByDayDto {
    @ApiModelProperty(value = "Name of product", example = "productName")
    String name;
    @ApiModelProperty(value = "Count of product", example = "7")
    BigInteger count;

    private StatisticByDayDto() {}

    public StatisticByDayDto(String name, BigInteger count) {
        this.name = name;
        this.count = count;
    }
}
