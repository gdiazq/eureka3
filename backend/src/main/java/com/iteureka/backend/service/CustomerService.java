package com.iteureka.backend.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.iteureka.backend.model.CustomerEntity;

@Service
public interface CustomerService {

    Iterable <CustomerEntity> findAll();

    Optional <CustomerEntity> findById(Long id);

    CustomerEntity save(CustomerEntity customerEntity);

    void deleteById(Long id);
}

