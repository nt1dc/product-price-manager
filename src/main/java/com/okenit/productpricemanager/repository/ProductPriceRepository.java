package com.okenit.productpricemanager.repository;

import com.okenit.productpricemanager.dto.ProductNameAndPriceDto;
import com.okenit.productpricemanager.dto.StatisticByDayDto;
import com.okenit.productpricemanager.dto.StatisticByProductDto;
import com.okenit.productpricemanager.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    @Query(nativeQuery = true)
    List<StatisticByDayDto> frequencyStatisticByDate(Date datetime);


    @Query(nativeQuery = true)
    List<StatisticByProductDto>  frequencyStatisticByProductId(Long id);
//    @Query("select p.datetime, count(p) from ProductPrice p where p.product.id = ?1 order by p.datetime")
//    List<> countProductPricesByProductIdOrderByDatetime(long product_id);

    @Query(nativeQuery = true)
    List<ProductNameAndPriceDto> actualPriceList(Date datetime);
}

