package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
    }

    @Test
    void testAddAndGetProduct() throws Exception {
        Product product = new Product("Table", "Dining table", new BigDecimal("150.00"), 3, "Furniture");

        String json = objectMapper.writeValueAsString(product);

        // Add product
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Table"));

        // Get all products
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        // Get product by id
        Long id = productRepository.findAll().get(0).getId();

        mockMvc.perform(get("/api/products/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Table"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product product = new Product("Lamp", "Desk lamp", new BigDecimal("30.00"), 7, "Lighting");
        product = productRepository.save(product);

        product.setPrice(new BigDecimal("25.00"));
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(put("/api/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.00));
    }

    @Test
    void testDeleteProduct() throws Exception {
        Product product = new Product("Bookcase", "Wooden bookcase", new BigDecimal("120.00"), 2, "Furniture");
        product = productRepository.save(product);

        mockMvc.perform(delete("/api/products/" + product.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/" + product.getId()))
                .andExpect(status().isNotFound());
    }
}