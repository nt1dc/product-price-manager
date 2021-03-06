package com.okenit.productpricemanager;

import com.okenit.productpricemanager.parsers.CsvParser;
import com.okenit.productpricemanager.utils.SwaggerFileCreator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductPriceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductPriceManagerApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(CsvParser parser, SwaggerFileCreator swaggerFileCreator) {
        return args -> {
            parser.parse();
            swaggerFileCreator.create();
        };
    }
}
