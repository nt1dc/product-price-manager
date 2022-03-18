package com.okenit.productpricemanager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticByProductDto implements Serializable {
    Date date;
    @ApiModelProperty(example = "3")
    BigInteger frequency;
}
