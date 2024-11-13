package com.iteureka.backeurekafour.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iteureka.backeurekafour.model.CustomerEntity;
import com.iteureka.backeurekafour.service.CustomerService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Iterable<CustomerEntity> getAllCustomer() {
        return customerService.findAll();
    }

}
