package com.okenit.productpricemanager;

import com.okenit.productpricemanager.parsers.CsvParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
public class ProductPriceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductPriceManagerApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(CsvParser parser) {
        return args -> {
            parser.parse();
        };
    }
}
