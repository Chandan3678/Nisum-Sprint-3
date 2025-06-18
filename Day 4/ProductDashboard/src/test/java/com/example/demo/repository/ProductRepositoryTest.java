package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndFind() {
        Product product = new Product("Laptop", "Gaming laptop", new BigDecimal("1200.00"), 5, "Electronics");
        productRepository.save(product);

        List<Product> found = productRepository.findByNameContainingIgnoreCase("lap");
        assertThat(found).isNotEmpty();
    }

    @Test
    void testFindByCategory() {
        Product p1 = new Product("Phone", "Smartphone", new BigDecimal("600.00"), 10, "Electronics");
        Product p2 = new Product("T-Shirt", "Cotton T-Shirt", new BigDecimal("15.00"), 50, "Clothing");
        productRepository.save(p1);
        productRepository.save(p2);

        List<Product> electronics = productRepository.findByCategory("Electronics");
        assertThat(electronics).contains(p1).doesNotContain(p2);
    }

    @Test
    void testFindByPriceBetween() {
        Product p1 = new Product("Book", "Fiction book", new BigDecimal("20.00"), 100, "Books");
        Product p2 = new Product("Pen", "Ballpoint pen", new BigDecimal("2.00"), 200, "Stationery");
        productRepository.save(p1);
        productRepository.save(p2);

        List<Product> midPrice = productRepository.findByPriceBetween(new BigDecimal("10.00"), new BigDecimal("25.00"));
        assertThat(midPrice).contains(p1).doesNotContain(p2);
    }

    @Test
    void testFindByNameAndCategory() {
        Product product = new Product("Chair", "Wooden chair", new BigDecimal("85.00"), 12, "Furniture");
        productRepository.save(product);

        List<Product> found = productRepository.findByNameAndCategory("chair", "Furniture");
        assertThat(found).isNotEmpty();
    }
}