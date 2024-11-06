package com.iteureka.backend.service;

import com.iteureka.backend.model.CustomerEntity;
import com.iteureka.backend.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        customerService.findAll();
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(customerRepository.findById(id)).thenReturn(Optional.of(new CustomerEntity()));
        Optional<CustomerEntity> foundCustomer = customerService.findById(id);
        assertTrue(foundCustomer.isPresent());
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        CustomerEntity customer = new CustomerEntity();
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customer);

        CustomerEntity savedCustomer = customerService.save(customer);
        assertNotNull(savedCustomer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        customerService.deleteById(id);
        verify(customerRepository, times(1)).deleteById(id);
    }
}