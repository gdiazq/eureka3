package com.iteureka.backeurekafour.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iteureka.backeurekafour.model.ProjectEntity;

@Service
public interface ProjectService {

    Iterable <ProjectEntity> findAll();

    Optional <ProjectEntity> findById(Long id);

    ProjectEntity save(ProjectEntity projectEntity);

    void createProjectWithCustomer(ProjectEntity projectEntity);

    ProjectEntity put(Long id, ProjectEntity projectEntity);

    void deleteById(Long id);

}
