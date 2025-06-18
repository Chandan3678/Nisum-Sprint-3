package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveAndFind() {
        Customer customer = new Customer("John Doe", "john@example.com", LocalDate.now());
        customerRepository.save(customer);

        List<Customer> found = customerRepository.findByEmailContaining("john");
        assertThat(found).isNotEmpty();
    }

    @Test
    void testFindByRegisteredDateAfter() {
        Customer c1 = new Customer("Alice", "alice@example.com", LocalDate.of(2023, 1, 1));
        Customer c2 = new Customer("Bob", "bob@example.com", LocalDate.of(2025, 1, 1));
        customerRepository.save(c1);
        customerRepository.save(c2);

        List<Customer> recent = customerRepository.findByRegisteredDateAfter(LocalDate.of(2024, 1, 1));
        assertThat(recent).contains(c2).doesNotContain(c1);
    }

    @Test
    void testFindByNameQuery() {
        Customer c = new Customer("Charlie", "charlie@example.com", LocalDate.now());
        customerRepository.save(c);

        List<Customer> found = customerRepository.findByName("Charlie");
        assertThat(found).isNotEmpty();
    }
}