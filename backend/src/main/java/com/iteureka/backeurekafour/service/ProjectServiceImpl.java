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
        // Si el proyecto incluye una lista de clientes, se actualiza.
        if (projectEntity.getClientes() != null && !projectEntity.getClientes().isEmpty()) {
            // Itera sobre la lista de clientes proporcionada.
            for (CustomerEntity customer : projectEntity.getClientes()) {
                // Busca el cliente existente en el repositorio por su ID.
                CustomerEntity managedCustomer = customerRepository.findById(customer.getId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
                // Asigna el proyecto al cliente.
                managedCustomer.setProyecto_id(projectEntity);
            }
        }
        // Guarda el proyecto en el repositorio.
        projectRepository.save(projectEntity);
    }

    @Override
    @Transactional
    public ProjectEntity put(Long id, ProjectEntity projectEntity) {
        // Busca el proyecto existente en el repositorio por su ID.
        Optional<ProjectEntity> existingProjectOpt = projectRepository.findById(id);
        // Si el proyecto no existe, lanza una excepción.
        if (!existingProjectOpt.isPresent()) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        // Obtiene la entidad del proyecto existente desde el Optional.
        ProjectEntity existingProject = existingProjectOpt.get();
        // Actualiza el nombre del proyecto con el nuevo valor.
        existingProject.setNombre(projectEntity.getNombre());
        // Si el proyecto incluye una lista de clientes, se actualiza.
        if (projectEntity.getClientes() != null) {
            // Crea una lista para almacenar los clientes actualizados.
            List<CustomerEntity> updatedCustomers = new ArrayList<>();
            // Itera sobre la lista de clientes proporcionada.
            for (CustomerEntity customer : projectEntity.getClientes()) {
                // Busca el cliente existente en el repositorio por su ID.
                Optional<CustomerEntity> existingCustomerOpt = customerRepository.findById(customer.getId());
                // Si el cliente existe, actualiza sus valores.
                if (existingCustomerOpt.isPresent()) {
                    CustomerEntity existingCustomer = existingCustomerOpt.get();
                    existingCustomer.setNombre(customer.getNombre());
                    existingCustomer.setCasa_matriz(customer.getCasa_matriz());
                    updatedCustomers.add(existingCustomer);
                } else {
                    // Si el cliente no existe, lanza una excepción.
                    throw new RuntimeException("Cliente con ID " + customer.getId() + " no encontrado");
                }
            }
            // Actualiza la lista de clientes del proyecto con la lista actualizada.
            existingProject.setClientes(updatedCustomers);
        }
        // Guarda el proyecto actualizado en el repositorio.
        return projectRepository.save(existingProject);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

}
