package com.iteureka.backeurekafour.controller;

import com.iteureka.backeurekafour.model.CustomerEntity;
import com.iteureka.backeurekafour.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba para el método que obtiene todos los clientes
    @Test
    void testGetAllCustomer() {
        when(customerService.findAll()).thenReturn(Collections.emptyList());
        Iterable<CustomerEntity> customers = customerController.getAllCustomer();
        assertNotNull(customers);
        verify(customerService, times(1)).findAll();
    }

}