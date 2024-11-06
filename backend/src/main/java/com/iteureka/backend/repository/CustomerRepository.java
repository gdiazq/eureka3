package com.iteureka.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.iteureka.backend.model.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
