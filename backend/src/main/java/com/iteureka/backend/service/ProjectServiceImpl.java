package com.iteureka.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteureka.backend.model.ProjectEntity;
import com.iteureka.backend.repository.ProjectRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

}
