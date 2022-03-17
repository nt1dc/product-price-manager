package com.okenit.productpricemanager.api;

import com.okenit.productpricemanager.dto.ProductNameAndPriceDto;
import com.okenit.productpricemanager.dto.StatisticByDayDto;
import com.okenit.productpricemanager.dto.StatisticByProductDto;
import com.okenit.productpricemanager.service.ProductService;
import com.okenit.productpricemanager.utils.DateFormatUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/products")
public class ProductController {
    private final DateFormatUtil dateFormatUtil;
    private final ProductService productService;

    public ProductController(DateFormatUtil dateFormatUtil, ProductService productService) {
        this.dateFormatUtil = dateFormatUtil;
        this.productService = productService;
    }

    @GetMapping()
    ResponseEntity<List<ProductNameAndPriceDto>> getActualPrice(@RequestParam @ApiParam(value = "template of date 'yyyy-MM-dd'", example = "2002-10-09") String date) {
        try {
            Date fromDate = dateFormatUtil.parseFromDate(date);
            List<ProductNameAndPriceDto> productList = productService.getActual(fromDate);
            return ResponseEntity.ok(productList);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().header("error", e.getLocalizedMessage() + " suspect template of date is 'yyyy-MM-dd'").build();
        }
    }


    @GetMapping("/statisic/date")
    ResponseEntity<List<StatisticByDayDto>> getStatisticByDay(@RequestParam @ApiParam(value = "template of date 'yyyy-MM-dd'", example = "2002-10-09") String date) throws ParseException {
        try {
            Date formDate = dateFormatUtil.parseFromDate(date);
            List<StatisticByDayDto> statistic = productService.frequencyStatisticByDate(formDate);
            return ResponseEntity.ok(statistic);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().header("error", e.getLocalizedMessage() + " suspect template of date is 'yyyy-MM-dd'").build();
        }
    }


    @GetMapping("/statistic/count")
    ResponseEntity<Long> getChangesCount() {
        Long count = productService.countOfProducts();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/statistic/product")
    ResponseEntity<List<StatisticByProductDto>> getStatisticByProduct(@RequestParam @ApiParam(value = "id of required product", example = "1", name = "id") Long id) {
        List<StatisticByProductDto> statistic = productService.frequencyStatisticByProductId(id);
        return ResponseEntity.ok(statistic);
    }

}
