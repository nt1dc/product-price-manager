package com.okenit.productpricemanager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class StatisticByDayDto {
    @ApiModelProperty(value = "Name of product", example = "productName")
    String name;
    @ApiModelProperty(value = "Count of product", example = "7")
    BigInteger count;


}
