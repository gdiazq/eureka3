package com.iteureka.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iteureka.backend.model.CustomerEntity;
import com.iteureka.backend.service.CustomerService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping ("/{id}") 
    public Optional<CustomerEntity> getCustomerById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PostMapping
    public CustomerEntity saveCustomer(@RequestBody CustomerEntity customerEntity) {
        return customerService.save(customerEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteById(id);
    }

}
