package com.iteureka.backeurekafour.repository;

import org.springframework.data.repository.CrudRepository;

import com.iteureka.backeurekafour.model.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
