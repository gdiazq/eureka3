package com.iteureka.backeurekafour.service;

import org.springframework.stereotype.Service;

import com.iteureka.backeurekafour.model.CustomerEntity;

@Service
public interface CustomerService {

    Iterable <CustomerEntity> findAll();

}

