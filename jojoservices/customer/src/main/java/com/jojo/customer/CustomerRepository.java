package com.jojo.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<com.jojo.customer.Customer, Integer> {
}
