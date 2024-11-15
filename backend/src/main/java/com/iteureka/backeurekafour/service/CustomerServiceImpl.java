package com.iteureka.backeurekafour.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteureka.backeurekafour.model.CustomerEntity;
import com.iteureka.backeurekafour.repository.CustomerRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    //GET
    @Override
    @Transactional(readOnly = true)
    public Iterable<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    //GET{id}
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerEntity> findById(Long id) {
        return customerRepository.findById(id);
    }


}