package com.okenit.productpricemanager.dto;

import lombok.Data;

import java.math.BigInteger;

@Data

public class StatisticByDayDto {
    String name;
    BigInteger count;

    private StatisticByDayDto() {
    }

    public StatisticByDayDto(String name, BigInteger count) {
        this.name = name;
        this.count = count;
    }
}
