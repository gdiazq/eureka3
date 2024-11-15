package com.iteureka.backeurekafour.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iteureka.backeurekafour.model.CustomerEntity;

@Service
public interface CustomerService {

    Iterable <CustomerEntity> findAll();

    Optional <CustomerEntity> findById(Long id);

}

