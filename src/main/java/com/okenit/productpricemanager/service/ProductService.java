package com.okenit.productpricemanager.service;

import com.okenit.productpricemanager.dto.ProductNameAndPriceDto;
import com.okenit.productpricemanager.dto.StatisticByDayDto;
import com.okenit.productpricemanager.dto.StatisticByProductDto;
import com.okenit.productpricemanager.model.Product;
import com.okenit.productpricemanager.model.ProductPrice;
import com.okenit.productpricemanager.repository.ProductPriceRepository;
import com.okenit.productpricemanager.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProductService {
    final ProductPriceRepository priceRepository;
    final ProductRepository productRepository;

    public ProductService(ProductPriceRepository priceRepository, ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }


    public List<ProductNameAndPriceDto> getActual(Date date) {
        return priceRepository.actualPriceList(date);
    }

    public long countOfProducts() {
        return productRepository.count();
    }

    public List<StatisticByProductDto> frequencyStatisticByProductId(Long id) {
        return priceRepository.frequencyStatisticByProductId(id);
    }

    public List<StatisticByDayDto> frequencyStatisticByDate(Date date) {
        return priceRepository.frequencyStatisticByDate(date);
    }


    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void saveProductPrice(ProductPrice price) {
        priceRepository.save(price);
    }


    public void saveProductsAndPrices(List<Product> products, List<ProductPrice> productPrices) {

        log.info("Starting load products to DB");
        try {
            productRepository.saveAll(products);

        } catch (Exception | Error e) {
            log.error("Error in products prices ");
            log.error(e.getLocalizedMessage());
        }

        log.info("Starting load prices to DB");
        try {


            priceRepository.saveAll(productPrices);
        } catch (Exception | Error e) {
            log.error("Error in loading prices ");
            log.error(e.getLocalizedMessage());
        }
        log.info("Loading end");
    }
}
