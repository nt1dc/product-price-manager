package com.okenit.productpricemanager.dao;

import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ProductWithPriceDao implements Serializable {
    long product_id;
    String product_name;
    long price_id;
    double price;
    Date price_datetime;

    public void setPrice_datetime(String price_datetime) throws ParseException {
        this.price_datetime = new SimpleDateFormat("y-M-d H:m").parse(price_datetime);
    }

}
