package com.okenit.productpricemanager.model;

import com.okenit.productpricemanager.dto.ProductNameAndPriceDto;
import com.okenit.productpricemanager.dto.StatisticByDayDto;
import com.okenit.productpricemanager.dto.StatisticByProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "ProductPrice.actualPriceList",
        query = "select product.name as name , pp.price as price\n" +
                "from product \n" +
                "         join product_price pp on product.id = pp.product_id\n" +
                "         inner join (select pp.product_id, max(pp.datetime) as last\n" +
                "                     from product_price pp\n" +
                "                     where datetime <= date(?1)\n" +
                "                     group by pp.product_id) t on t.product_id=pp.product_id  and t.last=pp.datetime", resultSetMapping = "MappingProductNameAndPriceDto")
@SqlResultSetMapping(name = "MappingProductNameAndPriceDto",
        classes = @ConstructorResult(targetClass = ProductNameAndPriceDto.class,
                columns = {@ColumnResult(name = "name"),
                        @ColumnResult(name = "price")}))


@NamedNativeQuery(name = "ProductPrice.frequencyStatisticByDate", query = "select p.name as name, count(*) as count from product_price pp join product p on p.id = pp.product_id where date_part('day',pp.datetime)= date_part('day',date(?1))  group by p.name", resultSetMapping = "MappingStatisticByDayDto")
@SqlResultSetMapping(name = "MappingStatisticByDayDto",
        classes = @ConstructorResult(targetClass = StatisticByDayDto.class,
                columns = {@ColumnResult(name = "name"),
                        @ColumnResult(name = "count")}))


@NamedNativeQuery(name = "ProductPrice.frequencyStatisticByProductId",
        query = "select CAST(pp.datetime AS DATE) as date, count(*) as frequency " +
                "from product_price pp " +
                "where product_id= ?\n" +
                "group by 1;", resultSetMapping = "MappingStatisticByProductDto")

@SqlResultSetMapping(name = "MappingStatisticByProductDto",
        classes = @ConstructorResult(targetClass = StatisticByProductDto.class,
                columns = {@ColumnResult(name = "date"),
                        @ColumnResult(name = "frequency")}))

public class ProductPrice {
    @Id
    long id;
    double price;
    Date datetime;
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;

    public ProductPrice(long id, double price, Date datetime, Product product) {
        this.id = id;
        this.price = price;
        this.datetime = datetime;
        this.product = product;
    }

}
