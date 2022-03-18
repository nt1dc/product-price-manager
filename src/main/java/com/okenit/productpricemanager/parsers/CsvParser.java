package com.okenit.productpricemanager.parsers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.okenit.productpricemanager.dao.ProductWithPriceDao;
import com.okenit.productpricemanager.entities.Product;
import com.okenit.productpricemanager.entities.ProductPrice;
import com.okenit.productpricemanager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@EnableScheduling
public class CsvParser implements ProductParser {
    private final CsvMapper csvMapper = new CsvMapper();
    private final CsvSchema schema = CsvSchema.emptySchema().withHeader();
    private final ObjectReader oReader = csvMapper.reader(ProductWithPriceDao.class).with(schema);
    private Reader reader;
    @Value("${PATH}")
    private String path;
    private List<ProductPrice> productPrices;
    private List<Product> products;
    private final ProductService service;

    public void readCsv() throws IOException {
        productPrices = new ArrayList<>();
        products = new ArrayList<>();
        log.info("Starting read CSV.. ");
        MappingIterator<ProductWithPriceDao> mi = oReader.readValues(reader);
        List<ProductWithPriceDao> readAll = mi.readAll();
        for (ProductWithPriceDao current : readAll
        ) {
            Product currentProduct = new Product(current.getProduct_id(), current.getProduct_name());
            log.info(currentProduct.toString());
            ProductPrice currentProductPrice = new ProductPrice(current.getPrice_id(), current.getPrice(), current.getPrice_datetime(), currentProduct);
            log.info(currentProductPrice.toString());
            products.add(currentProduct);
            productPrices.add(currentProductPrice);
        }
        reader.close();

        log.info("Reading CSV complete successfully");
        log.info("{} products was readied", products.size());
        log.info("{} prices was readied", productPrices.size());
    }

    private void loadToBD() {
        if (!products.isEmpty()) {
            service.saveAllProducts(products);
            products.clear();
        } else {
            log.info("empty products");
        }
        if (!productPrices.isEmpty()) {
            service.saveAllProductPrices(productPrices);
            productPrices.clear();
        }else {
            log.info("empty prices");
        }
    }


    public CsvParser(ProductService service) {
        this.service = service;
    }

    @Override
    @Scheduled(cron = ("${SCHEDULE}"))
//    @Scheduled(fixedRate = 1000)
    public void parse() {
        try {
            log.info("CSV PATH = " + path);
            reader = new FileReader(path);
            readCsv();
            new File(path).delete();
        } catch (FileNotFoundException e) {
            log.error("CSV file " + path + " not found");
        } catch (IOException e) {
            log.info("ERROR during reading CSV");
        }
        try {
            loadToBD();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }
}


