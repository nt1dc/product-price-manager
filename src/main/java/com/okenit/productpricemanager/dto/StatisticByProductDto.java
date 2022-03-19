package com.okenit.productpricemanager.dto;

import com.okenit.productpricemanager.utils.DateFormatUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


@Data
public class StatisticByProductDto implements Serializable {
    @ApiModelProperty(value = "date in format yyyy-MM-dd", example = "2020-03-19")
    String date;
    @ApiModelProperty(example = "3")
    BigInteger frequency;

    public StatisticByProductDto(Date date, BigInteger frequency) {
        this.date = DateFormatUtil.getNormanDate(date);
        this.frequency = frequency;
    }
}
