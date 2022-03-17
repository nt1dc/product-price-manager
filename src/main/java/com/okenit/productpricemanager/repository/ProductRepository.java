package com.okenit.productpricemanager.repository;

import com.okenit.productpricemanager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findTopById(long id);
}
