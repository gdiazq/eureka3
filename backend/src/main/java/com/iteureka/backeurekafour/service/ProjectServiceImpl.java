package com.iteureka.backeurekafour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteureka.backeurekafour.model.CustomerEntity;
import com.iteureka.backeurekafour.model.ProjectEntity;
import com.iteureka.backeurekafour.repository.CustomerRepository;
import com.iteureka.backeurekafour.repository.ProjectRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectEntity> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    @Transactional
    public ProjectEntity save(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

    @Override
    @Transactional
    public void createProjectWithCustomer(ProjectEntity projectEntity) {
        if (projectEntity.getClientes() != null && !projectEntity.getClientes().isEmpty()) {
            for (CustomerEntity customer : projectEntity.getClientes()) {
                CustomerEntity managedCustomer = customerRepository.findById(customer.getId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
                managedCustomer.setProyecto_id(projectEntity);
            }
        }
        projectRepository.save(projectEntity);
    }

    @Override
    @Transactional
    public ProjectEntity put(Long id, ProjectEntity projectEntity) {
        Optional<ProjectEntity> existingProjectOpt = projectRepository.findById(id);
        if (!existingProjectOpt.isPresent()) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        ProjectEntity existingProject = existingProjectOpt.get();
        existingProject.setNombre(projectEntity.getNombre());
        if (projectEntity.getClientes() != null) {
            List<CustomerEntity> updatedCustomers = new ArrayList<>();
            for (CustomerEntity customer : projectEntity.getClientes()) {
                Optional<CustomerEntity> existingCustomerOpt = customerRepository.findById(customer.getId());
                if (existingCustomerOpt.isPresent()) {
                    CustomerEntity existingCustomer = existingCustomerOpt.get();
                    existingCustomer.setNombre(customer.getNombre());
                    existingCustomer.setCasa_matriz(customer.getCasa_matriz());
                    updatedCustomers.add(existingCustomer);
                } else {
                    throw new RuntimeException("Cliente con ID " + customer.getId() + " no encontrado");
                }
            }
            existingProject.setClientes(updatedCustomers);
        }
        return projectRepository.save(existingProject);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

}
